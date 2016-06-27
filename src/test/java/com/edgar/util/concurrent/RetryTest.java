package com.edgar.util.concurrent;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by edgar on 16-3-31.
 */
public class RetryTest {


    @Test
    public void testRetryRunnable() throws InterruptedException, ExecutionException {

        AtomicInteger retryNum = new AtomicInteger();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                retryNum.incrementAndGet();
                System.out.println("do runnable");
                throw new NullPointerException();
            }
        };
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        RetryRunnnable<NullPointerException> retryRunnnable = new RetryRunnnable<>(runnable, executorService, 5, NullPointerException.class, 100, TimeUnit.MICROSECONDS);
        retryRunnnable.execute();
        TimeUnit.SECONDS.sleep(3);
        executorService.shutdown();
        Assertions.assertThat(retryNum.get()).isEqualTo(6);
    }

    @Test
    public void testIgnoreRetry() throws InterruptedException, ExecutionException {

        AtomicInteger retryNum = new AtomicInteger();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                retryNum.incrementAndGet();
                System.out.println("do runnable");
                throw new NullPointerException();
            }
        };
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        RetryRunnnable<IllegalArgumentException> retryRunnnable = new RetryRunnnable<>(runnable, executorService, 5, IllegalArgumentException.class, 100, TimeUnit.MICROSECONDS);
        retryRunnnable.execute();
        TimeUnit.SECONDS.sleep(3);
        executorService.shutdown();
        Assertions.assertThat(retryNum.get()).isEqualTo(1);
    }

    @Test
    public void testNoRetry() throws InterruptedException, ExecutionException {
        AtomicInteger retryNum = new AtomicInteger();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                retryNum.incrementAndGet();
                System.out.println("do runnable");
            }
        };
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        RetryRunnnable<IllegalArgumentException> retryRunnnable = new RetryRunnnable<>(runnable, executorService, 5, IllegalArgumentException.class, 100, TimeUnit.MICROSECONDS);
        retryRunnnable.execute();
        TimeUnit.SECONDS.sleep(3);
        executorService.shutdown();
        Assertions.assertThat(retryNum.get()).isEqualTo(1);
    }
}