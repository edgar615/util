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

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * This class is a utility class that just encapsulates a counter on which you can wait until it
 * reaches 0.
 *
 * @author ypujante@linkedin.com
 */
public class WaitableCounter {

  private int counter;

  public WaitableCounter(int counter) {
    this.counter = counter;
  }

  public WaitableCounter() {
    this(0);
  }

  public synchronized void dec() {
    counter--;

    if (counter == 0) {
      notifyAll();
    }
  }

  public synchronized void inc() {
    counter++;
  }

  public synchronized void waitForCounter() throws InterruptedException {
    while (counter > 0) {
      wait();
    }
  }

  public synchronized void waitForCounter(long time, TimeUnit unit)
      throws InterruptedException, TimeoutException {
    while (counter > 0) {
      awaitUntil(time, unit);
    }
  }

  private void awaitUntil(long time, TimeUnit unit)
      throws InterruptedException, TimeoutException {
    long startMills = System.currentTimeMillis();
    long endTime = startMills + unit.toMillis(time);
    if (endTime <= 0) {
      wait();
    } else {
      long now = System.currentTimeMillis();
      if (now >= endTime) {
        throw new TimeoutException("timeout reached while waiting on the lock: "
            + this);
      }
      wait(endTime - now);
    }
  }

  public synchronized int getCounter() {
    return counter;
  }
}
