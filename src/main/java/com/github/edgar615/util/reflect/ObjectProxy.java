package com.github.edgar615.util.reflect;

/**
 * 表示代理对象的接口.
 */
public interface ObjectProxy<T> {

  /**
   * 返回被代理的对象.
   *
   * @return 被代理的对象
   */
  T getProxiedObject();
}