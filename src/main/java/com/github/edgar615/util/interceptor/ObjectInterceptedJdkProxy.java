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

package com.github.edgar615.util.interceptor;

import com.github.edgar615.util.reflect.ObjectProxyBuilder;
import com.github.edgar615.util.reflect.ReflectUtils;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 拦截器的JDK代理类.拦截器的核心类.
 *
 * @author Edgar
 * @create 2018-09-14 14:17
 **/
public class ObjectInterceptedJdkProxy implements InvocationHandler {

  private final Object target;

  private final Interceptor interceptor;

  private ObjectInterceptedJdkProxy(Object target, Interceptor interceptor) {
    this.target = target;
    this.interceptor = interceptor;
  }

  public static Object create(Object target, Interceptor interceptor) {
    Class[] interfaces = ReflectUtils.extractAllInterfaces(target);
    if (interfaces.length == 0) {
      return target;
    }
    return ObjectProxyBuilder
        .createProxy(new ObjectInterceptedJdkProxy(target, interceptor), interfaces);
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    Invocation invocation = Invocation.create(target, method, args);
    if (checkProxy(invocation)) {
      return interceptor.intercept(invocation);
    }
    return invocation.proceed();
  }

  public boolean checkProxy(Invocation invocation) {
    Signature signature = interceptor.getClass().getAnnotation(Signature.class);
    if (!ReflectUtils.isSubClassOrInterfaceOf(target.getClass(), signature.type())) {
      return false;
    }
    if (invocation.method().isDefault()) {
      return false;
    }
    String methodName = invocation.method().getName();
    if (!methodName.equals(signature.method())) {
      return false;
    }
    if (invocation.args() == null && signature.args().length == 0) {
      return true;
    } else if (signature.args().length != invocation.args().length) {
      return false;
    }
    for (int i = 0; i < signature.args().length; i++) {
      Object invocationArg = invocation.args()[i];
      if (invocationArg != null && !ReflectUtils
          .isSubClassOrInterfaceOf(invocationArg.getClass(), signature.args()[i])) {
        return false;
      }
    }
    return true;
  }

}
