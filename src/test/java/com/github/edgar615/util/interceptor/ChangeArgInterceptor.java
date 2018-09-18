package com.github.edgar615.util.interceptor;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Edgar
 * @create 2018-09-18 13:37
 **/
@Signature(type = MessageService.class, method = "say", args = {String.class})
public class ChangeArgInterceptor implements SubInterceptor {

  private final AtomicInteger count = new AtomicInteger();
  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    count.incrementAndGet();
    System.out.println("interceptor");
    String message = (String) invocation.args()[0];
    invocation.args()[0] = message.toUpperCase();
    return invocation.proceed();
  }

  public int count() {
    return count.get();
  }
}
