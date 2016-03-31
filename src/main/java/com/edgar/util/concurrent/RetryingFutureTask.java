package com.edgar.util.concurrent;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

/**
 * 重试任务.
 * 如果任务返回false,将重试
 */
public class RetryingFutureTask extends FutureTask<Boolean> {

  private static final Logger LOGGER = LoggerFactory.getLogger(RetryingFutureTask.class);

  private final ExecutorService executorService;

  private final int maxRetries;

  private int numRetries = 0;

  private final Callable<Boolean> callable;

  public RetryingFutureTask(ExecutorService executorService, Callable<Boolean> callable, int maxRetries) {
    super(callable);
    this.callable = Preconditions.checkNotNull(callable);
    this.executorService = Preconditions.checkNotNull(executorService);
    this.maxRetries = maxRetries;
  }

  private void retry() {
    executorService.execute(this);
  }

  @Override
  public void run() {
    boolean success = false;
    try {
      success = callable.call();
    } catch (Exception e) {
      LOGGER.warn("Exception while executing task.", e);
    }

    if (!success) {
      numRetries++;
      if (numRetries > maxRetries) {
        LOGGER.warn("Task did not complete after " + maxRetries + " retries, giving up.");
        set(false);
      } else {
        LOGGER.info("Task was not successful, resubmitting (num retries: " + numRetries + ")");
        retry();
      }
    } else {
      set(true);
    }
  }
}
