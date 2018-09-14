package com.github.edgar615.util.reflect;

import com.github.edgar615.util.interceptor.Invocation;

/**
 * @author Edgar
 * @create 2018-09-13 20:41
 **/
public class TestMethodInvokeHandler implements MethodInvokeHandler {

  @Override
  public Object invoke(Invocation invocation) throws Throwable {
    return invocation.proceed();
  }
}
