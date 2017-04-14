package com.edgar.util.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.concurrent.Executor;

/**
 * Created by Edgar on 2017/4/14.
 *
 * @author Edgar  Date 2017/4/14
 */
public class OrderQueue {

  private static final Logger LOGGER = LoggerFactory.getLogger(OrderQueue.class);

  private final Runnable runner;

  private final LinkedList<Runnable> tasks = new LinkedList<>();

  private boolean running;

  public OrderQueue() {
    this.runner = () -> {
      for (; ; ) {
        final Runnable task;
        synchronized (tasks) {
          task = tasks.poll();
          if (task == null) {
            running = false;
            return;
          }
        }
        try {
          task.run();
        } catch (Throwable t) {
          LOGGER.error("Caught unexpected Throwable", t);
        }
      }
    };
  }

  public void execute(Runnable task, Executor executor) {
    synchronized (tasks) {
      tasks.add(task);
      if (!running) {
        running = true;
        executor.execute(runner);
      }
    }
  }

}
