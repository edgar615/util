package com.edgar.util.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *线程的异常打印日志.
 */
public class ErrorLoggingRunnable implements Runnable {
  private static final Logger LOG = LoggerFactory.getLogger(ErrorLoggingRunnable.class);
  private final Runnable delegate;

  public ErrorLoggingRunnable(Runnable delegate) {
    this.delegate = delegate;
  }

  @Override
  public void run() {
    try {
      delegate.run();
    } catch (Throwable t) {
      LOG.error("error", t);
    }
  }
}