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

  private ObjectInterceptedJdkProxy(Object target, Interceptor interceptor) {
    this.target = target;
    this.interceptor = interceptor;
  }

  public static Object create(Object target, Interceptor interceptor) {
    Class[] interfaces = ReflectUtils.extractAllInterfaces(target);
    if (interfaces.length == 0) {
      return target;
    }
    return ObjectProxyBuilder
        .createProxy(new ObjectInterceptedJdkProxy(target, interceptor), interfaces);
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    Invocation invocation = Invocation.create(target, method, args);
    if (checkProxy(invocation)) {
      return interceptor.intercept(invocation);
    }
    return invocation.proceed();
  }

  public boolean checkProxy(Invocation invocation) {
    Signature signature = interceptor.getClass().getAnnotation(Signature.class);
    if (!ReflectUtils.isSubClassOrInterfaceOf(target.getClass(), signature.type())) {
      return false;
    }
    if (invocation.method().isDefault()) {
      return false;
    }
    String methodName = invocation.method().getName();
    if (!methodName.equals(signature.method())) {
      return false;
    }
    if (invocation.args() == null && signature.args().length == 0) {
      return true;
    } else if (signature.args().length != invocation.args().length) {
      return false;
    }
    for (int i = 0; i < signature.args().length; i++) {
      if (!ReflectUtils
          .isSubClassOrInterfaceOf(invocation.args()[i].getClass(), signature.args()[i])) {
        return false;
      }
    }
    return true;
  }

}
