package com.github.edgar615.util.concurrent;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 队列/消息是系统开发中经常使用的解耦利器。但是有一部分业务可能要对同一个用户/设备的消息按顺序处理.
 * 基于这种需求，实现了动态路由队列，队列除了保存消息外，还保存了一个ID注册表，每次出队时，只能取出不在注册表中的消息，同时出队后需要在注册表中注册。
 * 在消息处理完成后，需要调用complete方法来实现清理注册表。
 *
 * @author Edgar  Date 2018/5/3
 */
public class SequentialBlockingQueue<E> extends AbstractQueue<E> implements BlockingQueue<E> {
  /**
   * 任务列表
   */
  private final LinkedList<E> elements = new LinkedList<>();

  /**
   * 消息注册表，用于确保同一个设备只有一个事件在执行
   */
  private final Set<String> registry = new ConcurrentSkipListSet<>();

  private final IdentificationExtractor<E> extractor;

  private final int limit;

  /**
   * Main lock guarding all access
   */
  private final ReentrantLock lock;

  /**
   * Condition for waiting takes
   */
  private final Condition waitingTake;

  /**
   * Condition for waiting puts
   */
  private final Condition waitingPut;

  private E takeElement;

  public SequentialBlockingQueue(IdentificationExtractor<E> extractor, int limit) {
    this.extractor = extractor;
    this.limit = limit;
    this.lock = new ReentrantLock();
    this.waitingTake = lock.newCondition();
    this.waitingPut = lock.newCondition();
  }

  public SequentialBlockingQueue(IdentificationExtractor<E> extractor) {
    this(extractor, Integer.MAX_VALUE);
  }

  public void complete(E task) {
    final ReentrantLock lock = this.lock;
    lock.lock();
    try {
      if (takeElement == null) {
        waitingTake.signalAll();
      }
      registry.remove(extractor.apply(task));
      takeElement = next();
    } finally {
      lock.unlock();
    }
  }

  @Override
  public int size() {
    final ReentrantLock lock = this.lock;
    lock.lock();
    try {
      return elements.size();
    } finally {
      lock.unlock();
    }
  }

  @Override
  public void put(E e) throws InterruptedException {
    final ReentrantLock lock = this.lock;
    lock.lockInterruptibly();
    try {
      //如果队满，阻塞入队
      while (this.elements.size() == this.limit) {
        waitingPut.await();
      }
      //唤醒等待出队的线程，如果队空或者下一个出队元素为null，说明可能会有出队线程在等待唤醒
      if (elements.isEmpty() || takeElement == null) {
        //唤醒出队
        waitingTake.signalAll();
      }
      //入队
      enqueue(e);
    } finally {
      lock.unlock();
    }

  }

  @Override
  public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
    long nanos = unit.toNanos(timeout);
    final ReentrantLock lock = this.lock;
    lock.lockInterruptibly();
    try {
      //如果队满，阻塞入队
      while (this.elements.size() == this.limit) {
        if (nanos <= 0) {
          return false;
        }
        nanos = waitingPut.awaitNanos(nanos);
      }
      //唤醒等待出队的线程，如果队空或者下一个出队元素为null，说明可能会有出队线程在等待唤醒
      if (elements.isEmpty() || takeElement == null) {
        //唤醒出队
        waitingTake.signalAll();
      }
      enqueue(e);

    } finally {
      lock.unlock();
    }
    return false;
  }

  @Override
  public E take() throws InterruptedException {
    final ReentrantLock lock = this.lock;
    lock.lockInterruptibly();
    try {
      //如果队列为空，或者下一个出队元素为null，阻塞出队
      while (elements.isEmpty() || takeElement == null) {
        waitingTake.await();
      }
      //唤醒等待入队的线程，如果队满，说明可能会有入队线程在等待唤醒(不满不会有入队线程等待唤醒)
      if (this.elements.size() == this.limit) {
        //唤醒入队，入队线程会在出队方法执行完毕并释放锁之后才开始抢占锁
        waitingPut.signalAll();
      }
      return dequeue();
    } finally {
      lock.unlock();
    }
  }

  @Override
  public E poll(long timeout, TimeUnit unit) throws InterruptedException {
    long nanos = unit.toNanos(timeout);
    final ReentrantLock lock = this.lock;
    lock.lockInterruptibly();
    try {
      //如果队列为空，或者下一个出队元素为null，阻塞出队
      while (elements.isEmpty() || takeElement == null) {
        if (nanos < 0) {
          return null;
        }
        nanos = waitingTake.awaitNanos(nanos);
      }
      //唤醒等待入队的线程，如果队满，说明可能会有入队线程在等待唤醒(不满不会有入队线程等待唤醒)
      if (this.elements.size() == this.limit) {
        //唤醒入队，入队线程会在出队方法执行完毕并释放锁之后才开始抢占锁
        waitingPut.signalAll();
      }
      return dequeue();
    } finally {
      lock.unlock();
    }
  }

  @Override
  public int remainingCapacity() {
    final ReentrantLock lock = this.lock;
    lock.lock();
    try {
      return limit - elements.size();
    } finally {
      lock.unlock();
    }
  }

  @Override
  public int drainTo(Collection<? super E> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public int drainTo(Collection<? super E> c, int maxElements) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean offer(E e) {
    final ReentrantLock lock = this.lock;
    lock.lock();
    try {
      if (elements.size() == limit) { return false; } else {
        enqueue(e);
        return true;
      }
    } finally {
      lock.unlock();
    }
  }

  @Override
  public E poll() {
    final ReentrantLock lock = this.lock;
    lock.lock();
    try {
      return (elements.size() == 0) ? null : dequeue();
    } finally {
      lock.unlock();
    }
  }

  @Override
  public E peek() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Iterator<E> iterator() {
    final ReentrantLock lock = this.lock;
    lock.lock();
    try {
      return new LinkedList<>(elements).iterator();
    } finally {
      lock.unlock();
    }
  }

  private void enqueue(E e) {//入队
    elements.add(e);
    //计算下一个可以出队的元素
    if (!registry.contains(extractor.apply(e))
        && takeElement == null) {
      takeElement = e;
    }
  }

  private E dequeue() {//从队列中删除元素
    E x = takeElement;
    elements.remove(x);
    //将元素加入注册表
    registry.add(extractor.apply(x));
    //重新计算下一个可以出队的元素
    takeElement = next();
    return x;
  }

  private E next() {
    E next = null;
    for (E event : elements) {
      String id = extractor.apply(event);
      if (!registry.contains(id)) {
        next = event;
        break;
      }
    }
    return next;
  }

}
