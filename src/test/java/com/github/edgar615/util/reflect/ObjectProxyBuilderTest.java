/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
