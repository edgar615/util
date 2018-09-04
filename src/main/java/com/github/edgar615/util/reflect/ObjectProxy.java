package com.github.edgar615.util.reflect;

/**
 * This interface represents an object proxied. It will most likely be implemented by invocation
 * handlers when using dynamic proxy. The concept of {@link java.lang.reflect.Proxy} does not
 * necessarily represent a proxy over an object. This interface is meant to specify this restriction
 * which is 99% of the cases in which we use dynamic proxies!
 *
 * @author ypujante@linkedin.com
 */
public interface ObjectProxy<T> {

  /**
   * @return the object proxied
   */
  T getProxiedObject();
}