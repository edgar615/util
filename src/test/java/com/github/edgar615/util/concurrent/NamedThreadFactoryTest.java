package com.github.edgar615.util.concurrent;

import java.util.concurrent.ThreadFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by edgar on 16-4-1.
 */
public class NamedThreadFactoryTest {

  @Test
  public void testThreadName() {
    ThreadFactory threadFactory = NamedThreadFactory.create("test");
    Thread thread = threadFactory.newThread(new Runnable() {
      @Override
      public void run() {

      }
    });
    Assert.assertEquals("test-0", thread.getName());

    thread = threadFactory.newThread(new Runnable() {
      @Override
      public void run() {

      }
    });
    Assert.assertEquals("test-1", thread.getName());
  }
}
