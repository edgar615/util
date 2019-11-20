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

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Edgar
 * @create 2018-09-18 13:37
 **/
@Signature(type = MessageService.class, method = "say", args = {})
public class NoArgInterceptor implements Interceptor {

  private final AtomicInteger count = new AtomicInteger();

  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    count.incrementAndGet();
    System.out.println("interceptor");
    return invocation.proceed();
  }

  public int count() {
    return count.get();
  }
}
