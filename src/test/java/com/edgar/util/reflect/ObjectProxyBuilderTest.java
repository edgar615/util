package com.edgar.util.reflect;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.util.List;

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
    proxy.say("hello");
  }

  @Test
  public void testCreateProxy2() {
    IMyService myService = new MyServiceImpl();
    InvocationHandler handler = new MyServiceProxy(myService);
    IMyService proxy = ObjectProxyBuilder.createProxy(handler, IMyService.class);
    proxy.say("hello");
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
