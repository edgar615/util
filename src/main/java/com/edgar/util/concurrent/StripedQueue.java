package com.edgar.util.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Edgar on 2017/4/14.
 *
 * @author Edgar  Date 2017/4/14
 */
public class StripedQueue {

  private final Executor executor;

  private final ReentrantLock lock = new ReentrantLock(true);

  private ConcurrentMap<Integer, OrderQueue> queues = new ConcurrentHashMap<>();

  public StripedQueue(Executor executor) {this.executor = executor;}

  public void add(int hash, Runnable task) {
    lock.lock();
    try {
      OrderQueue queue = queues.get(hash);
      if (queue == null) {
        queue = new OrderQueue();
        queues.put(hash, queue);
      }
      queue.execute(task, executor);
    } finally {
      lock.unlock();
    }
  }

  public void add(int hash, Runnable task, Runnable callback) {
    add(hash, new CallbackTask(task, callback));
  }
}
