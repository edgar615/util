package com.edgar.util.lifecycle;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Defines the method of an entity that is shutdownable
 *
 * @author ypujante@linkedin.com
 */
public interface Shutdownable extends Terminable {
  /**
   * This methods sets the entity in shutdown mode. Any method call on this
   * entity after shutdown should be either rejected
   * (<code>IllegalStateException</code>) or discarded. This method should
   * not block and return immediately.
   *
   * @see #waitForShutdown()
   */
  public void shutdown();

  /**
   * Waits for shutdown to be completed. After calling shutdown, there may
   * still be some pending work that needs to be accomplised. This method
   * will block until it is done.
   *
   * @throws InterruptedException  if interrupted while waiting
   * @throws IllegalStateException if shutdown has not been called
   */
  public void waitForShutdown()
          throws InterruptedException, IllegalStateException;

  /**
   * Waits for shutdown to be completed. After calling shutdown, there may
   * still be some pending work that needs to be accomplised. This method
   * will block until it is done but no longer than the timeout.
   *
   * @param timeout how long to wait maximum for the shutdown
   * @param unit    TimeUnit
   * @throws InterruptedException  if interrupted while waiting
   * @throws IllegalStateException if shutdown has not been called
   * @throws TimeoutException      if shutdown still not complete after timeout
   */
  public void waitForShutdown(long timeout, TimeUnit unit)
          throws InterruptedException, IllegalStateException, TimeoutException;
}