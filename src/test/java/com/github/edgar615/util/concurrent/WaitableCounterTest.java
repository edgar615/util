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
import org.junit.Test;

/**
 * Created by edgar on 16-1-27.
 */
public class WaitableCounterTest {

  @Test
  public void testWait() throws InterruptedException {
    WaitableCounter waitableCounter = new WaitableCounter(10);
    Runnable inc = () -> {
      for (int i = 0; i < 10; i++) {
        try {
          TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        waitableCounter.inc();
      }
    };
    Runnable dec = () -> {
      for (int i = 0; i < 20; i++) {
        try {
          TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        waitableCounter.dec();
      }
    };
    Thread t1 = new Thread(inc);
    Thread t2 = new Thread(dec);
    long start = System.currentTimeMillis();
    t1.start();
    t2.start();
    waitableCounter.waitForCounter();
    System.out.print(System.currentTimeMillis() - start);
  }

  @Test
  public void testWaitTime() throws InterruptedException, TimeoutException {
    WaitableCounter waitableCounter = new WaitableCounter(10);
    Runnable inc = () -> {
      for (int i = 0; i < 10; i++) {
        try {
          TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        waitableCounter.inc();
      }
    };
    Runnable dec = () -> {
      for (int i = 0; i < 20; i++) {
        try {
          TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        waitableCounter.dec();
      }
    };
    Thread t1 = new Thread(inc);
    Thread t2 = new Thread(dec);
    long start = System.currentTimeMillis();
    t1.start();
    t2.start();
    waitableCounter.waitForCounter(500, TimeUnit.MILLISECONDS);
    System.out.print(System.currentTimeMillis() - start);
  }
}
