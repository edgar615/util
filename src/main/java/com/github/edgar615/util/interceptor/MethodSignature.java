package com.github.edgar615.util.interceptor;

/**
 * 用来要定义拦截的方法.
 *
 * @author Edgar
 */
public class MethodSignature {

  private final Class<?> type;

  private final String method;

  private final Class<?>[] args;

  public MethodSignature(Class<?> type, String method, Class<?>[] args) {
    this.type = type;
    this.method = method;
    this.args = args;
  }

  /**
   * 类
   *
   * @return 类
   */
 public Class<?> type() {
    return type;
  }

  /**
   * 方法名
   *
   * @return 方法名
   */
  public String method() {
    return method;
  }

  /**
   * 参数类型
   *
   * @return 参数类型
   */
  public Class<?>[] args() {
    return args;
  }
}