package com.edgar.util.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Very basic implementation of the proxy which simply delegates all calls to the proxied object.
 *
 * @author ypujante@linkedin.com
 */
public class ObjectProxyInvocationHandler<T> implements ObjectProxy<T>, InvocationHandler {
  private final T _proxiedObject;

  /**
   * Constructor
   */
  public ObjectProxyInvocationHandler(T proxiedObject) {
    _proxiedObject = proxiedObject;
  }

  /**
   * This is a convenient call when you want to proxy an object with the unique given
   * interface. Note that you can use the more general call {@link Proxy#newProxyInstance
   * (ClassLoader, Class[], InvocationHandler)}
   * if you want to do something different.
   *
   * @param interfaceToProxy the interface to proxy
   * @param objectToProxy    the object to proxy (note that it must implement interfaceToProxy!)
   * @return the proxied object
   */
  @SuppressWarnings("unchecked")
  public static <T> T createProxy(Class<T> interfaceToProxy, T objectToProxy) {
// since there is only one interface in the array, we know that the proxy must implement it!
    return (T) Proxy.newProxyInstance(interfaceToProxy.getClassLoader(),
                                      new Class<?>[]{interfaceToProxy},
                                      new ObjectProxyInvocationHandler(objectToProxy));
  }

  /**
   * @return the object proxied
   */
  @Override
  public T getProxiedObject() {
    return _proxiedObject;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    try {
      return method.invoke(_proxiedObject, args);
    } catch (InvocationTargetException e) {
      throw e.getTargetException();
    }
  }
}