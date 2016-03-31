package com.edgar.util.concurrent;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by edgar on 16-3-31.
 */
public class RetryTest {


  @Test
  public void testRetryRunnable() throws InterruptedException, ExecutionException {

    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        System.out.println("do runnable");
        throw new NullPointerException();
      }
    };
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    RetryRunnnable<NullPointerException> retryRunnnable = new RetryRunnnable<>(runnable, executorService, 5, NullPointerException.class, 100, TimeUnit.MICROSECONDS);
    executorService.execute(retryRunnnable);
    TimeUnit.SECONDS.sleep(3);
    executorService.shutdown();
  }

  @Test
  public void testIgnoreRetry() throws InterruptedException, ExecutionException {

    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        System.out.println("do runnable");
        throw new NullPointerException();
      }
    };
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    RetryRunnnable<IllegalArgumentException> retryRunnnable = new RetryRunnnable<>(runnable, executorService, 5, IllegalArgumentException.class, 100, TimeUnit.MICROSECONDS);
    executorService.execute(retryRunnnable);
    TimeUnit.SECONDS.sleep(3);
    executorService.shutdown();
  }

  @Test
  public void testNoRetry() throws InterruptedException, ExecutionException {

    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        System.out.println("do runnable");
      }
    };
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    RetryRunnnable<IllegalArgumentException> retryRunnnable = new RetryRunnnable<>(runnable, executorService, 5, IllegalArgumentException.class, 100, TimeUnit.MICROSECONDS);
    executorService.execute(retryRunnnable);
    TimeUnit.SECONDS.sleep(3);
    executorService.shutdown();
  }
}