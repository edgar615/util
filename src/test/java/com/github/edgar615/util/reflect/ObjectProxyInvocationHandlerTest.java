package com.github.edgar615.util.reflect;

import org.junit.Test;

/**
 * Created by Edgar on 2016/2/19.
 *
 * @author Edgar  Date 2016/2/19
 */
public class ObjectProxyInvocationHandlerTest {

  @Test
  public void testCreateProxy() {
    IMyService myService = new MyServiceImpl();
    IMyService proxy = ObjectProxyInvocationHandler.createProxy(IMyService.class, myService);
    proxy.say("hello");
  }
}