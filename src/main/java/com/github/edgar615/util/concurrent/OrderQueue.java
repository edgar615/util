/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.edgar615.util.concurrent;

import java.util.LinkedList;
import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 这个队列中的任务会被顺序执行.
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

  public boolean running() {
    return running;
  }

  public int size() {
    return tasks.size();
  }

  protected void add(Runnable task) {
    synchronized (tasks) {
      tasks.add(task);
    }
  }
}
