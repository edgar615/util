package com.github.edgar615.util.concurrent;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by edgar on 16-3-31.
 */
public class RetryFutureTest {


  @Test
  public void testRetryRunnable() throws InterruptedException, ExecutionException {

    AtomicInteger retryNum = new AtomicInteger();
    Callable<Integer> callable = new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        retryNum.incrementAndGet();
        System.out.println("do runnable");
        throw new NullPointerException();
      }
    };
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    RetryFutureTask<Integer, NullPointerException> retryRunnnable =
            new RetryFutureTask<>(callable, executorService, 5, NullPointerException.class, 100,
                                  TimeUnit.MICROSECONDS);
    retryRunnnable.execute();
    TimeUnit.SECONDS.sleep(3);
    executorService.shutdown();
    Assertions.assertThat(retryNum.get()).isEqualTo(6);
    Assertions.assertThat(retryRunnnable.get()).isNull();
  }

  @Test
  public void testIgnoreRetry() throws InterruptedException, ExecutionException {

    AtomicInteger retryNum = new AtomicInteger();
    Callable<Integer> callable = new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        retryNum.incrementAndGet();
        System.out.println("do runnable");
        throw new NullPointerException();
      }
    };
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    RetryFutureTask<Integer, IllegalArgumentException> retryRunnnable =
            new RetryFutureTask<>(callable, executorService, 5, IllegalArgumentException.class, 100,
                                  TimeUnit.MICROSECONDS);
    retryRunnnable.execute();
    TimeUnit.SECONDS.sleep(3);
    executorService.shutdown();
    Assertions.assertThat(retryNum.get()).isEqualTo(1);
    Assertions.assertThat(retryRunnnable.get()).isNull();
  }

  @Test
  public void testNoRetry() throws InterruptedException, ExecutionException {
    AtomicInteger retryNum = new AtomicInteger();
    Callable<Integer> callable = new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        retryNum.incrementAndGet();
        System.out.println("do runnable");
        return 10;
      }
    };
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    RetryFutureTask<Integer, IllegalArgumentException> retryRunnnable =
            new RetryFutureTask<>(callable, executorService, 5, IllegalArgumentException.class, 100,
                                  TimeUnit.MICROSECONDS);
    retryRunnnable.execute();
    TimeUnit.SECONDS.sleep(3);
    executorService.shutdown();
    Assertions.assertThat(retryNum.get()).isEqualTo(1);
    Assertions.assertThat(retryRunnnable.get()).isEqualTo(10);
  }
}