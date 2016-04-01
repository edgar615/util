package com.edgar.util.serialization;

/**
 * 序列化，反序列化的异常.
 *
 * @author Edgar.
 */
public class SerDeException extends RuntimeException {
  public SerDeException() {
  }

  public SerDeException(String message) {
    super(message);
  }

  public SerDeException(String message, Throwable cause) {
    super(message, cause);
  }

  public SerDeException(Throwable cause) {
    super(cause);
  }

  public SerDeException(
          String message,
          Throwable cause,
          boolean enableSuppression,
          boolean writableStackTrace
  ) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}