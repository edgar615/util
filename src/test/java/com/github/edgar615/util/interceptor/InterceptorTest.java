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
    Jdbc proxy = (Jdbc) InterceptedObjectBuilder.create().addInterceptor(interceptor)
        .addInterceptor(new CacheInterceptor())
        .bind(jdbc);
    int result = proxy.deleteById(Device.class, 1);
    Assert.assertEquals(result, Integer.MAX_VALUE);

  }

  @Test
  public void testNoArgVoid() {
    MessageService messageService = new MessageServiceImpl();
    Interceptor interceptor = new NoArgInterceptor();
    MessageService proxy = (MessageService) InterceptedObjectBuilder.create()
        .addInterceptor(interceptor)
        .bind(messageService);
    proxy.say();
    Assert.assertEquals(1, ((NoArgInterceptor) interceptor).count());
  }

  @Test
  public void testChangeArgs() {
    MessageService messageService = new MessageServiceImpl();
    Interceptor interceptor = new ChangeArgInterceptor();
    MessageService proxy = (MessageService) InterceptedObjectBuilder.create()
        .addInterceptor(interceptor)
        .bind(messageService);
    String result = proxy.say("hello");
    Assert.assertEquals(1, ((ChangeArgInterceptor) interceptor).count());
    Assert.assertEquals("HELLO", result);
  }
}
