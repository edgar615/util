package com.github.edgar615.util.concurrent;

import org.awaitility.Awaitility;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Edgar on 2017/4/14.
 *
 * @author Edgar  Date 2017/4/14
 */
public class StripedQueueTest {

  @Test
  public void testOrderInOneThread() {
    ExecutorService executor = Executors.newFixedThreadPool(1);
    StripedQueue queue = new StripedQueue(executor);
    List<Integer> res = new CopyOnWriteArrayList<>();
    queue.add(1, runnable(100), () -> res.add(1));
    queue.add(2, runnable(110), () -> res.add(2));
    queue.add(1, runnable(120), () -> res.add(3));
    queue.add(2, runnable(50), () -> res.add(4));
    queue.add(2, runnable(50), () -> res.add(5));
    queue.add(1, runnable(100), () -> res.add(6));
    queue.add(1, runnable(100), () -> res.add(7));
    queue.add(1, runnable(100), () -> res.add(8));
    Awaitility.await().until(() -> res.size() == 8);
    System.out.println(res);
    Assert.assertEquals(0, res.indexOf(1));
    Assert.assertEquals(1, res.indexOf(2));
    Assert.assertEquals(2, res.indexOf(3));
    Assert.assertEquals(3, res.indexOf(6));
    Assert.assertEquals(4, res.indexOf(7));
    Assert.assertEquals(5, res.indexOf(8));
    Assert.assertEquals(6, res.indexOf(4));
    Assert.assertEquals(7, res.indexOf(5));
  }

  @Test
  public void testOrderInTwoThread() {
    ExecutorService executor = Executors.newFixedThreadPool(2);
    StripedQueue queue = new StripedQueue(executor);
    List<Integer> res = new CopyOnWriteArrayList<>();
    queue.add(1, runnable(100), () -> res.add(1)); // 100
    queue.add(2, runnable(200), () -> res.add(2)); //200
    queue.add(1, runnable(150), () -> res.add(3)); //250
    queue.add(1, runnable(150), () -> res.add(4)); //400
    queue.add(2, runnable(100), () -> res.add(5)); // 300
    queue.add(1, runnable(150), () -> res.add(6)); //550
    queue.add(1, runnable(150), () -> res.add(7)); //700
    queue.add(2, runnable(120), () -> res.add(8)); //420
    Awaitility.await().until(() -> res.size() == 8);
    System.out.println(res);
    Assert.assertEquals(0, res.indexOf(1));
    Assert.assertEquals(1, res.indexOf(2));
    Assert.assertEquals(2, res.indexOf(3));
    Assert.assertEquals(3, res.indexOf(5));
    Assert.assertEquals(4, res.indexOf(4));
    Assert.assertEquals(5, res.indexOf(8));
    Assert.assertEquals(6, res.indexOf(6));
    Assert.assertEquals(7, res.indexOf(7));
  }

  @Test
  public void testOrderInThreeThread() {
    ExecutorService executor = Executors.newFixedThreadPool(2);
    StripedQueue queue = new StripedQueue(executor);
    List<Integer> res = new CopyOnWriteArrayList<>();
    queue.add(1, runnable(100), () -> res.add(1)); // 100
    queue.add(2, runnable(200), () -> res.add(2)); //200
    queue.add(1, runnable(150), () -> res.add(3)); //250
    queue.add(1, runnable(150), () -> res.add(4)); //400
    queue.add(2, runnable(100), () -> res.add(5)); // 300
    queue.add(1, runnable(150), () -> res.add(6)); //550
    queue.add(1, runnable(150), () -> res.add(7)); //700
    queue.add(2, runnable(120), () -> res.add(8)); //420
    Awaitility.await().until(() -> res.size() == 8);
    System.out.println(res);
    Assert.assertEquals(0, res.indexOf(1));
    Assert.assertEquals(1, res.indexOf(2));
    Assert.assertEquals(2, res.indexOf(3));
    Assert.assertEquals(3, res.indexOf(5));
    Assert.assertEquals(4, res.indexOf(4));
    Assert.assertEquals(5, res.indexOf(8));
    Assert.assertEquals(6, res.indexOf(6));
    Assert.assertEquals(7, res.indexOf(7));
  }

  private Runnable runnable(long sleep) {
    return () -> {
      try {
        System.out.println(sleep);
        TimeUnit.MILLISECONDS.sleep(sleep);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    };
  }
}
