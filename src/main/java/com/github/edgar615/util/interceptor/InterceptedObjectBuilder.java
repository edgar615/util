package com.github.edgar615.util.interceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * 拦截器的构建类.
 *
 * @author Edgar
 * @create 2018-09-15 10:13
 **/
public class InterceptedObjectBuilder {

  private final List<Interceptor> interceptors = new ArrayList<>();

  public void addInterceptor(Interceptor interceptor) {
    Signature signature = interceptor.getClass().getAnnotation(Signature.class);
    System.out.println(signature);
    interceptors.add(interceptor);
  }

}
