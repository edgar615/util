package com.github.edgar615.util.interceptor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 拦击器的方法调用，从Mybatis借鉴而来.
 *
 * @author Edgar
 */
public class Invocation {

  /**
   * 目标对象
   */
  private final Object target;
  /**
   * 方法
   */
  private final Method method;
  /**
   * 参数
   */
  private final Object[] args;

  private Invocation(Object target, Method method, Object[] args) {
    this.target = target;
    this.method = method;
    this.args = args;
  }

  /**
   * 创建Invocation对象
   *
   * @param target 目标对象
   * @param method 方法
   * @param args 参数
   * @return Invocation
   */
  public static Invocation create(Object target, Method method, Object[] args) {
    return new Invocation(target, method, args);
  }

  public Object target() {
    return target;
  }

  public Method method() {
    return method;
  }

  public Object[] args() {
    return args;
  }

  /**
   * 执行方法
   *
   * @return Object
   * @throws InvocationTargetException 反射异常
   * @throws IllegalAccessException 反射异常
   */
  public Object proceed() throws InvocationTargetException, IllegalAccessException {
    return method.invoke(target, args);
  }

}