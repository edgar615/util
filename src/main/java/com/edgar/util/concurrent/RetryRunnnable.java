package com.edgar.util.concurrent;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 任务失败后，重试的任务.
 */
public class RetryRunnnable<E extends Exception> implements Runnable {

  private static final Logger LOGGER = LoggerFactory.getLogger(RetryRunnnable.class);

  private final Runnable runnable;

  private final ScheduledExecutorService executorService;

  private final int maxRetries;
  private final Class<E> exceptionClass;
  private final int delay;
  private final TimeUnit unit;
  private int numRetries = 0;

  public RetryRunnnable(Runnable runnable, ScheduledExecutorService executorService, int maxRetries, Class<E> exceptionClass, int delay, TimeUnit unit) {
    this.runnable = Preconditions.checkNotNull(runnable);
    this.executorService = Preconditions.checkNotNull(executorService);
    this.maxRetries = maxRetries;
    this.exceptionClass = Preconditions.checkNotNull(exceptionClass);
    this.delay = delay;
    this.unit = Preconditions.checkNotNull(unit);
  }


  @Override
  public void run() {
    try {
      runnable.run();
    } catch (Exception e) {
      LOGGER.warn("Exception while executing task.", e);
      if (e.getClass().isAssignableFrom(exceptionClass)) {
        numRetries++;
        if (numRetries > maxRetries) {
          LOGGER.debug("Task did not complete after " + maxRetries + " retries, giving up.");
        } else {
          LOGGER.debug("Task was not successful, resubmitting (num retries: " + numRetries + ")");
          retry();
        }
      } else {
        LOGGER.debug("Giving up on task: due to unhandled exception ");
      }
    }

  }

  private void retry() {
    executorService.schedule(this, delay, unit);
  }
}