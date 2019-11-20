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
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 动态代理的实现.
 */
public class ObjectProxyBuilder {

  /**
   * 创建一个代理对象.
   *
   * @param handler InvocationHandler
   * @param itface 需要代理的接口
   * @return 代理对象.
   */
  @SuppressWarnings("unchecked")
  public static <T> T createProxy(InvocationHandler handler, Class<T> itface) {
    if (handler instanceof ObjectProxy) {
      ObjectProxy proxy = (ObjectProxy) handler;
      if (!ReflectUtils.isSubClassOrInterfaceOf(proxy.getProxiedObject().getClass(), itface)) {
        throw new IllegalArgumentException(
            proxy.getProxiedObject().getClass() + " does not extend " + itface);
      }
    }
    return (T) Proxy.newProxyInstance(itface.getClassLoader(),
        new Class<?>[]{itface}, handler);
  }

  /**
   * 创建一个代理对象.
   *
   * @param handler InvocationHandler
   * @param itfaces 需要代理的接口数组
   * @return 代理对象.
   */
  public static Object createProxy(InvocationHandler handler, Class<?>... itfaces) {
    if (handler instanceof ObjectProxy) {
      ObjectProxy proxy = (ObjectProxy) handler;
      Class<?> proxyClass = proxy.getProxiedObject().getClass();
      for (Class<?> itface : itfaces) {
        if (!ReflectUtils.isSubClassOrInterfaceOf(proxyClass, itface)) {
          throw new IllegalArgumentException(proxyClass + " does not extend " + itface);
        }
      }
    }
    List<ClassLoader> classLoaders = new ArrayList<ClassLoader>();
    for (Class<?> itface : itfaces) {
      classLoaders.add(itface.getClassLoader());
    }
    ClassLoader classLoader = ClassLoaderChain.createChain(classLoaders);
    return Proxy.newProxyInstance(classLoader,
        itfaces,
        handler);
  }

  /**
   * @see #createProxy(InvocationHandler, Class[])
   */
  public static Object createProxy(InvocationHandler handler, Collection<Class<?>> interfaces) {
    return createProxy(handler, interfaces.toArray(new Class[interfaces.size()]));
  }
}
