package com.github.edgar615.util.interceptor;

/**
 * 表示拦截器对象的接口.
 *
 * @author Edgar
 */
public interface ObjectIntercepted<T> {

  /**
   * 返回被拦截的对象.
   *
   * @return 被拦截的对象
   */
  T getInterceptedObject();
}