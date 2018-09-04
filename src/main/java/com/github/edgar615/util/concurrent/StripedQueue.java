package com.github.edgar615.util.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 同一个逻辑分片的任务按顺序执行.
 *
 * @author Edgar  Date 2017/4/14
 */
public class StripedQueue {

  private final Executor executor;

  private final ReentrantLock lock = new ReentrantLock(true);

  private ConcurrentMap<Integer, OrderQueue> running = new ConcurrentHashMap<>();

  private ConcurrentMap<Integer, OrderQueue> waiting = new ConcurrentHashMap<>();

  public StripedQueue(Executor executor) {
    this.executor = executor;
  }

  public void add(int hash, Runnable task) {
    lock.lock();
    try {
      OrderQueue queue = running.get(hash);
      if (queue == null) {
        queue = new OrderQueue();
        running.put(hash, queue);
      } else if (queue.running()) {
        addWaiting(hash, task);
      }
      if (!queue.running()) {
        queue.add(task);
        //将交换running和wating队列的方法放在running的最后，在running执行完之后就会执行交换
        queue.execute(callback(hash), executor);
      }
    } finally {
      lock.unlock();
    }
  }

  public void add(int hash, Runnable task, Runnable callback) {
    add(hash, new CallbackTask(task, callback));
  }

  private Runnable callback(int hash) {
    return () -> {
      exchange(hash);
    };
  }

  private void exchange(int hash) {
    lock.lock();
    try {
      OrderQueue wQueue = waiting.get(hash);
      OrderQueue rQueue = running.get(hash);
      if (wQueue != null && wQueue.size() > 0) {
        running.put(hash, wQueue);
        waiting.put(hash, rQueue);
        //选择一个没有执行的
        wQueue.execute(callback(hash), executor);
      }
    } finally {
      lock.unlock();
    }
  }

  private void addWaiting(int hash, Runnable task) {
    lock.lock();
    try {
      OrderQueue queue = waiting.get(hash);
      if (queue == null) {
        queue = new OrderQueue();
        waiting.put(hash, queue);
      }
      queue.add(task);
    } finally {
      lock.unlock();
    }
  }
}
