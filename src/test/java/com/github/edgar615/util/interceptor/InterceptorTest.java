package com.github.edgar615.util.interceptor;

import com.github.edgar615.util.db.Device;
import com.github.edgar615.util.db.Jdbc;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Edgar
 * @create 2018-09-14 16:33
 **/
public class InterceptorTest {

  @Test
  public void test() {
    Jdbc jdbc = new MockJdbc();
    Interceptor interceptor = new LogInterceptor();
    Jdbc proxy = (Jdbc) ObjectInterceptedJdkProxy.create(jdbc, interceptor);
    int result = proxy.deleteById(Device.class, 1);
    Assert.assertEquals(result, 0);
  }

  @Test
  public void testChain() {
    Jdbc jdbc = new MockJdbc();
    Interceptor interceptor = new LogInterceptor();
    InterceptorChain chain = new InterceptorChain();
    chain.addInterceptor(interceptor);
    chain.addInterceptor(new TestInterceptor());
    Jdbc proxy = bind(chain, jdbc);
    int result = proxy.deleteById(Device.class, 1);
    Assert.assertEquals(result, Integer.MAX_VALUE);

  }

  public Jdbc bind(InterceptorChain chain, Jdbc jdbc) {
    Jdbc proxy = jdbc;
    for (Interceptor interceptor : chain.getInterceptors()) {
      proxy = (Jdbc) ObjectInterceptedJdkProxy.create(proxy, interceptor);
    }
    return proxy;
  }
}
