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
import java.util.Random;

/**
 * 从给定列表中随机选择一个对象. Created by edgar on 17-5-6.
 */
class RandomStrategy implements ProviderStrategy {

  private final Random random = new Random();

  @Override
  public ServiceInstance get(List<ServiceInstance> instances) {
    if (instances == null || instances.isEmpty()) {
      return null;
    }
    int index = random.nextInt(instances.size());
    return instances.get(index);
  }

}
