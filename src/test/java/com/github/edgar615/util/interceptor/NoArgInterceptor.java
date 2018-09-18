package com.github.edgar615.util.interceptor;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Edgar
 * @create 2018-09-18 13:37
 **/
@Signature(type = MessageService.class, method = "say", args = {})
public class NoArgInterceptor implements Interceptor {

  private final AtomicInteger count = new AtomicInteger();

  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    count.incrementAndGet();
    System.out.println("interceptor");
    return invocation.proceed();
  }

  public int count() {
    return count.get();
  }
}
