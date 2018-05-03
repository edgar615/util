package com.github.edgar615.util.concurrent;

import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * 队列/消息是系统开发中经常使用的解耦利器。但是有一部分业务可能要对同一个用户/设备的消息按顺序处理.
 * 基于这种需求，实现了动态路由队列，队列除了保存消息外，还保存了一个ID注册表，每次出队时，只能取出不在注册表中的消息，同时出队后需要在注册表中注册。
 * 在消息处理完成后，需要调用complete方法来实现清理注册表。
 *
 * 这个是一个简易实现，SequentialBlockingQueue实现了BlockingQueue更多的方法
 * @author Edgar  Date 2018/5/3
 */
public class SequentialQueue<E> {
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

  private E takeElement;

  public SequentialQueue(IdentificationExtractor<E> extractor, int limit) {
    this.extractor = extractor;
    this.limit = limit;
  }

  public SequentialQueue(IdentificationExtractor<E> extractor) {
    this(extractor, Integer.MAX_VALUE);
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

  public synchronized E dequeue() throws InterruptedException {
    //如果队列为空，或者下一个出队元素为null，阻塞出队
    while (elements.isEmpty() || takeElement == null) {
      wait();
    }
    //唤醒等待入队的线程，如果队满，说明可能会有入队线程在等待唤醒(不满不会有入队线程等待唤醒)
    if (this.elements.size() == this.limit) {
      //唤醒入队，入队线程会在出队方法执行完毕并释放锁之后才开始抢占锁
      notifyAll();
    }
    //从队列中删除元素
    E x = takeElement;
    elements.remove(x);
    //将元素加入注册表
    registry.add(extractor.apply(x));
    //重新计算下一个可以出队的元素
    takeElement = next();
    return x;
  }

  public synchronized void enqueue(E task) throws InterruptedException {
    //如果队满，阻塞入队
    while (this.elements.size() == this.limit) {
      wait();
    }
    //唤醒等待出队的线程，如果队空或者下一个出队元素为null，说明可能会有出队线程在等待唤醒
    if (elements.isEmpty() || takeElement == null) {
      //唤醒出队
      notifyAll();
    }
    //入队
    elements.add(task);
    //计算下一个可以出队的元素
    if (!registry.contains(extractor.apply(task))
        && takeElement == null) {
      takeElement = task;
    }
  }

  public synchronized void complete(E task) {
    if (takeElement == null) {
      notifyAll();
    }
    registry.remove(extractor.apply(task));
    takeElement = next();
  }

  public synchronized int size() {
    return elements.size();
  }

}
