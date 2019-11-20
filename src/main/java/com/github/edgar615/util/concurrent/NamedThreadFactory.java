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

package com.github.edgar615.util.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义名称的线程工厂.
 */
public class NamedThreadFactory implements ThreadFactory {

  private final String baseName;

  private final AtomicInteger threadNum = new AtomicInteger(0);

  private NamedThreadFactory(String baseName) {
    this.baseName = baseName;
  }

  public static NamedThreadFactory create(String baseName) {
    return new NamedThreadFactory(baseName);
  }

  @Override
  public Thread newThread(Runnable r) {
    Thread thread = Executors.defaultThreadFactory().newThread(r);
    thread.setName(baseName + "-" + threadNum.getAndIncrement());
    return thread;
  }
}
