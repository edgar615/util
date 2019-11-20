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

package com.github.edgar615.util.loadbalance;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 从给定列表中轮询选择一个节点.
 * <p>
 * 由于给定的节点列表会变化，并不是严格意义上的轮询算法.
 *
 * @author Edgar  Date 2016/8/5
 */
class RoundRobinStrategy implements ProviderStrategy {

  private final AtomicInteger integer = new AtomicInteger(0);

  @Override
  public ServiceInstance get(List<ServiceInstance> records) {
    if (records == null || records.isEmpty()) {
      return null;
    }
    int index = Math.abs(integer.getAndIncrement());
    return records.get(index % records.size());
  }

}
