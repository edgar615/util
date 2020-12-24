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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 拦击器的方法调用，从Mybatis借鉴而来.
 *
 * @author Edgar
 */
public class Invocation {

  /**
   * 目标对象
   */
  private final Object target;
  /**
   * 方法
   */
  private final Method method;
  /**
   * 参数
   */
  private final Object[] args;

  private Invocation(Object target, Method method, Object[] args) {
    this.target = target;
    this.method = method;
    this.args = args;
  }

  /**
   * 创建Invocation对象
   *
   * @param target 目标对象
   * @param method 方法
   * @param args 参数
   * @return Invocation
   */
  public static Invocation create(Object target, Method method, Object[] args) {
    return new Invocation(target, method, args);
  }

  public Object target() {
    return target;
  }

  public Method method() {
    return method;
  }

  public Object[] args() {
    return args;
  }

  /**
   * 执行方法
   *
   * @return Object
   * @throws Throwable 反射异常
   */
  public Object proceed() throws Throwable {
    try {
      return method.invoke(target, args);
    } catch (InvocationTargetException e) {
      if (e.getCause() != null) {
        throw e.getCause();
      } else {
        throw e;
      }
    }

  }

}
