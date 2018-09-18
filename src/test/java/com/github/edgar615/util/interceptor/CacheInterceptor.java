package com.github.edgar615.util.interceptor;

import com.github.edgar615.util.db.Jdbc;

/**
 * @author Edgar
 * @create 2018-09-14 16:33
 **/
@Signature(type = Jdbc.class, method = "deleteById", args = {Class.class, Object.class})
public class CacheInterceptor implements Interceptor {

  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    System.out.println("test");
    return Integer.MAX_VALUE;
  }
}
