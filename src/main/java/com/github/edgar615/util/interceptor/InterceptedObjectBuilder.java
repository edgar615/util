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

import com.github.edgar615.util.reflect.ReflectUtils;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 拦截器的构建类.
 *
 * @author Edgar
 * @create 2018-09-15 10:13
 **/
public class InterceptedObjectBuilder {

  private final Set<Interceptor> interceptors = new HashSet<>();

  private InterceptedObjectBuilder() {
  }

  public static InterceptedObjectBuilder create() {
    return new InterceptedObjectBuilder();
  }

  public InterceptedObjectBuilder addInterceptor(Interceptor interceptor) {
    this.interceptors.add(interceptor);
    return this;
  }

  public InterceptedObjectBuilder addInterceptors(List<Interceptor> interceptors) {
    this.interceptors.addAll(interceptors);
    return this;
  }

  public Object bind(Object target) {
    Object proxy = target;
    for (Interceptor interceptor : interceptors) {
      proxy = proxy(proxy, interceptor);
    }
    return proxy;
  }

  private Object proxy(Object target, Interceptor interceptor) {
    Signature signature = interceptor.getClass().getAnnotation(Signature.class);
    if (signature == null) {
      return target;
    }
    if (!ReflectUtils.isSubClassOrInterfaceOf(target.getClass(), signature.type())) {
      return target;
    }
    return ObjectInterceptedJdkProxy.create(target, interceptor);
  }

}
