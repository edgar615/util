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

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.assertj.core.api.Assertions;
import org.junit.Test;

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
