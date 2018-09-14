package com.github.edgar615.util.reflect;

import java.lang.reflect.InvocationHandler;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Edgar on 2016/2/19.
 *
 * @author Edgar  Date 2016/2/19
 */
public class ObjectProxyBuilderTest {

  @Test
  public void testCreateProxy() {
    IMyService myService = new MyServiceImpl();
    InvocationHandler handler = new MyServiceHandler(myService);
    IMyService proxy = ObjectProxyBuilder.createProxy(handler, IMyService.class);
    String result = proxy.say("hello");
    Assert.assertEquals("proxy", result);

    result = proxy.say("foo");
    Assert.assertEquals("foo", result);
  }

  @Test
  public void testCreateProxy2() {
    IMyService myService = new MyServiceImpl();
    InvocationHandler handler = new MyServiceProxy(myService);
    IMyService proxy = ObjectProxyBuilder.createProxy(handler, IMyService.class);
    String result = proxy.say("hello");
    Assert.assertEquals("proxy", result);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateProxy3() {
    IMyService myService = new MyServiceImpl();
    InvocationHandler handler = new MyServiceHandler(myService);
    List proxy = ObjectProxyBuilder.createProxy(handler, List.class);
    proxy.add("hello");
    Assert.fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateProxy4() {
    IMyService myService = new MyServiceImpl();
    InvocationHandler handler = new MyServiceProxy(myService);
    List proxy = ObjectProxyBuilder.createProxy(handler, List.class);
    proxy.add("hello");
    Assert.fail();
  }
}
