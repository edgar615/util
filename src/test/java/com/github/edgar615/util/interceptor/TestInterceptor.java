package com.github.edgar615.util.interceptor;

/**
 * @author Edgar
 * @create 2018-09-14 16:33
 **/
public class TestInterceptor implements Interceptor {

  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    System.out.println("test");
    return Integer.MAX_VALUE;
  }
}
