package com.edgar.util.lifecycle;

import com.edgar.util.concurrent.WaitableCounter;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by edgar on 16-1-27.
 */
public class Shutdown implements Shutdownable, Serializable {
  private boolean _shutdown = false;

  private WaitableCounter pendingCallsCount = new WaitableCounter();

  /**
   * Sets the system is shutdown
   */
  @Override
  public synchronized void shutdown() {
    _shutdown = true;
  }


  /**
   * Waits for shutdown to be completed. After calling shutdown, there may still be some pending
   * work that needs to be accomplised. This method will block until it is done.
   *
   * @throws InterruptedException  if interrupted while waiting
   * @throws IllegalStateException if shutdown has not been called
   */
  @Override
  public void waitForShutdown() throws InterruptedException, IllegalStateException {
    if (!_shutdown) throw new IllegalStateException("call shutdown first");
    pendingCallsCount.waitForCounter();
  }

  /**
   * Waits for shutdown to be completed. After calling shutdown, there may still be some pending work
   * that needs to be accomplised. This method will block until it is done but no longer than the
   * timeout.
   *
   * @param timeout how long to wait maximum for the shutdown
   * @param unit    TimeUnit
   * @throws InterruptedException  if interrupted while waiting
   * @throws IllegalStateException if shutdown has not been called
   * @throws TimeoutException      if shutdown still not complete after timeout
   */
  @Override
  public void waitForShutdown(long timeout, TimeUnit unit)
          throws InterruptedException, IllegalStateException, TimeoutException {
    if (!_shutdown) throw new IllegalStateException("call shutdown first");
    pendingCallsCount.waitForCounter(timeout, unit);
  }

  /**
   * Called right before executing a call
   *
   * @throws ShutdownRequestedException
   */
  synchronized void startCall() throws ShutdownRequestedException {
    if (_shutdown)
      throw new ShutdownRequestedException();

    pendingCallsCount.inc();
  }

  /**
   * MUST be called if {@link #startCall()} is called after the call (typically in a
   * <code>finally</code>)
   */
  void endCall() {
    pendingCallsCount.dec();
  }

  /**
   * @return the number of currently pending calls
   */
  public int getPendingCallsCount() {
    return pendingCallsCount.getCounter();
  }
}
