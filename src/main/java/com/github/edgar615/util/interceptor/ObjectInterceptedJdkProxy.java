package com.github.edgar615.util.interceptor;

import com.github.edgar615.util.reflect.ObjectProxyBuilder;
import com.github.edgar615.util.reflect.ReflectUtils;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 拦截器的JDK代理类.拦截器的核心类.
 *
 * @author Edgar
 * @create 2018-09-14 14:17
 **/
public class ObjectInterceptedJdkProxy implements InvocationHandler {

  private final Object target;

  private final Interceptor interceptor;

  public static Object create(Object target, Interceptor interceptor) {
    Class[] interfaces = ReflectUtils.extractAllInterfaces(target);
    if (interfaces.length == 0) {
      return target;
    }
    return ObjectProxyBuilder
        .createProxy(new ObjectInterceptedJdkProxy(target, interceptor), interfaces);
  }

  private ObjectInterceptedJdkProxy(Object target, Interceptor interceptor) {
    this.target = target;
    this.interceptor = interceptor;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    //TODO 根据方法查找是否有对应的拦截器，有的话通过CHAIN组装，没有的话直接执行，同时使用CACHE，可以稍微提升一点性能.
    System.out.println(method.getName());
    System.out.println(args.length);
    Invocation invocation = Invocation.create(target, method, args);
    return interceptor.intercept(invocation);
  }

}
