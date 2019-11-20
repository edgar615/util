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
    RetryRunnnable<NullPointerException> retryRunnnable =
        new RetryRunnnable<>(runnable, executorService, 5, NullPointerException.class, 100,
            TimeUnit.MICROSECONDS);
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
    RetryRunnnable<IllegalArgumentException> retryRunnnable =
        new RetryRunnnable<>(runnable, executorService, 5, IllegalArgumentException.class, 100,
            TimeUnit.MICROSECONDS);
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
    RetryRunnnable<IllegalArgumentException> retryRunnnable =
        new RetryRunnnable<>(runnable, executorService, 5, IllegalArgumentException.class, 100,
            TimeUnit.MICROSECONDS);
    retryRunnnable.execute();
    TimeUnit.SECONDS.sleep(3);
    executorService.shutdown();
    Assertions.assertThat(retryNum.get()).isEqualTo(1);
  }
}
