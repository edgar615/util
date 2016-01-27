package com.edgar.util.lifecycle;

/**
 * Created by edgar on 16-1-27.
 */
public class ShutdownRequestedException extends RuntimeException {

  public ShutdownRequestedException() {
  }

  public ShutdownRequestedException(String message) {
    super(message);
  }

  public ShutdownRequestedException(String message, Throwable cause) {
    super(message, cause);
  }

  public ShutdownRequestedException(Throwable cause) {
    super(cause);
  }

  public ShutdownRequestedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
