package com.github.edgar615.util.interceptor;

import com.github.edgar615.util.db.Jdbc;
import com.github.edgar615.util.reflect.Signature;

/**
 * @author Edgar
 * @create 2018-09-14 16:33
 **/
@Signature(type = Jdbc.class, method = "deleteById")
public class LogInterceptor implements Interceptor {

  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    System.out.println("log");
    return invocation.proceed();
  }
}
