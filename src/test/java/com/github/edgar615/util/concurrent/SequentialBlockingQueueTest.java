package com.github.edgar615.util.concurrent;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.awaitility.Awaitility;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Edgar on 2018/5/3.
 *
 * @author Edgar  Date 2018/5/3
 */
public class SequentialBlockingQueueTest {

  private ExecutorService executorService;

  private ExecutorService producer;

  private SequentialBlockingQueue<TestElement> queue;

  private List<TestElement> result;

  @Before
  public void setUp() {
    executorService = Executors.newFixedThreadPool(5, NamedThreadFactory.create("test"));
    producer = Executors.newCachedThreadPool(NamedThreadFactory.create("producer"));
    result = new CopyOnWriteArrayList<>();
    queue = new SequentialBlockingQueue<>(e -> e.getId() + "", 3);
    for (int i = 0; i < 5; i++) {
      executorService.submit(() -> {
        while (!Thread.currentThread().isInterrupted()) {
          try {
            TestElement event = queue.take();
            TimeUnit.MILLISECONDS.sleep(100);
            queue.complete(event);
            result.add(event);
            System.out.println(Thread.currentThread() + ":" + event.getId());
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }
        }
      });
    }
  }

  @After
  public void tearDown() {
    executorService.shutdown();
    producer.shutdown();
  }

  @Test
  public void test() throws InterruptedException {
    List<Integer> input = new CopyOnWriteArrayList<>();
    for (int i = 0; i < 10; i++) {
      producer.submit(() -> {
        for (int j = 0; j < 10; j++) {
          try {
            input.add(j);
            queue.put(new TestElement(j, j));
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      });
    }
    TimeUnit.SECONDS.sleep(5);
//    System.out.println(result.size());
    Awaitility.await().until(() -> result.size() == 100);
    for (int i = 0; i < 10; i++) {
      final int finalI = i;
      long excepted = input.stream().filter(r -> r.equals(finalI)).count();
      long accepted = result.stream().filter(r -> r.getId() == finalI).count();
      System.out.println("e:" + excepted + ", " + "a:" + accepted);
      Assert.assertEquals(excepted, accepted);
    }
  }

  @Test
  public void testOrder() throws InterruptedException {
    for (int j = 0; j < 10; j++) {
      try {
        queue.put(new TestElement(1, j));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    TimeUnit.SECONDS.sleep(1);
    Awaitility.await().until(() -> result.size() == 10);
    for (int j = 0; j < 10; j++) {
      Assert.assertEquals(j, result.get(j).getOrder());
    }
  }

  private static class TestElement {

    private final int id;

    private final int order;

    private TestElement(int id, int order) {
      this.id = id;
      this.order = order;
    }

    public int getId() {
      return id;
    }

    public int getOrder() {
      return order;
    }
  }
}
