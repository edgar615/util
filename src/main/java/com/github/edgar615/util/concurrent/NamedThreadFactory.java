package com.github.edgar615.util.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义名称的线程工厂.
 */
public class NamedThreadFactory implements ThreadFactory {

  private final String baseName;

  private final AtomicInteger threadNum = new AtomicInteger(0);

  private NamedThreadFactory(String baseName) {
    this.baseName = baseName;
  }

  public static NamedThreadFactory create(String baseName) {
    return new NamedThreadFactory(baseName);
  }

  @Override
  public Thread newThread(Runnable r) {
    Thread thread = Executors.defaultThreadFactory().newThread(r);
    thread.setName(baseName + "-" + threadNum.getAndIncrement());
    return thread;
  }
}
