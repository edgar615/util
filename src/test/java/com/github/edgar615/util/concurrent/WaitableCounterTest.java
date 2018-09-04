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
