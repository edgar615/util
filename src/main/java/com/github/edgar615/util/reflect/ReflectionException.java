package com.github.edgar615.util.reflect;

/**
 * 反射异常.
 *
 * @author Edgar
 * @create 2018-09-06 12:43
 **/
public class ReflectionException extends RuntimeException {

  public ReflectionException() {
  }

  public ReflectionException(String message) {
    super(message);
  }

  public ReflectionException(String message, Throwable cause) {
    super(message, cause);
  }

  public ReflectionException(Throwable cause) {
    super(cause);
  }
}
