package com.github.edgar615.util.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理类的通用父类.
 *
 * @author ypujante@linkedin.com
 */
public class ObjectProxyInvocationHandler<T> implements ObjectProxy<T>, InvocationHandler {

  /**
   * 被代理的对象
   */
  private final T proxiedObject;

  private ObjectProxyInvocationHandler(T proxiedObject) {
    this.proxiedObject = proxiedObject;
  }

  public static <T> ObjectProxyInvocationHandler create(T proxiedObject) {
    return new ObjectProxyInvocationHandler(proxiedObject);
  }

  /**
   * This is a convenient call when you want to proxy an object with the unique given interface.
   * Note that you can use the more general call {@link Proxy#newProxyInstance (ClassLoader,
   * Class[], InvocationHandler)} if you want to do something different.
   *
   * @param interfaceToProxy the interface to proxy
   * @param objectToProxy the object to proxy (note that it must implement interfaceToProxy!)
   * @return the proxied object
   */
  @SuppressWarnings("unchecked")
  public static <T> T createProxy(Class<T> interfaceToProxy, T objectToProxy) {
// since there is only one interface in the array, we know that the proxy must implement it!
    return (T) Proxy.newProxyInstance(interfaceToProxy.getClassLoader(),
        new Class<?>[]{interfaceToProxy},
        new ObjectProxyInvocationHandler(objectToProxy));
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    try {
      return method.invoke(proxiedObject, args);
    } catch (InvocationTargetException e) {
      throw e.getTargetException();
    }
  }

  /**
   * 返回被代理的对象.
   *
   * @return 被代理的对象
   */
  @Override
  public T getProxiedObject() {
    return proxiedObject;
  }
}