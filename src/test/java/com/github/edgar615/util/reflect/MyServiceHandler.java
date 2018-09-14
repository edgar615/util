package com.github.edgar615.util.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyServiceHandler implements InvocationHandler {

  private Object target;

  public MyServiceHandler(Object target) {
    this.target = target;
  }

  public Object invoke(Object proxy, Method method, Object[] args)
      throws Throwable {
    System.out.println(proxy.getClass().getName());
    System.out.println(this.target.getClass().getName());
    System.out.println(method.getName());
    System.out.println(args);
    Object result = method.invoke(target, args);
    if ("hello".equals(result)) {
      return "proxy";
    }
    return result;
  }

}
