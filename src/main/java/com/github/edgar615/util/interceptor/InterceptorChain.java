package com.github.edgar615.util.interceptor;

import com.github.edgar615.util.db.Jdbc;
import com.github.edgar615.util.reflect.Signature;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;

/**
 * 拦截器的责任链.
 *
 * @author Edgar
 */
public class InterceptorChain {

  private final List<Interceptor> interceptors = new ArrayList<>();

  public void addInterceptor(Interceptor interceptor) {
    Signature signature = interceptor.getClass().getAnnotation(Signature.class);
    System.out.println(signature);
    interceptors.add(interceptor);
  }

  public List<Interceptor> getInterceptors() {
    return ImmutableList.copyOf(interceptors);
  }

}