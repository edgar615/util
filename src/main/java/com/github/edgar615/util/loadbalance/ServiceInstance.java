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

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by edgar on 17-5-9.
 */
public class ServiceInstance {

  /**
   * 节点ID
   */
  private final String id;

  /**
   * 服务名称
   */
  private final String name;

  /**
   * 权重
   */
  private AtomicInteger weight;

  /**
   * 有效权重
   */
  private AtomicInteger effectiveWeight;

  public ServiceInstance(String id, String name, int weight) {
    this.id = id;
    this.name = name;
    this.weight = new AtomicInteger(weight);
    this.effectiveWeight = new AtomicInteger(0);
  }

  public ServiceInstance(String id, String name) {
    this(id, name, 60);
  }

  public int weight() {
    return weight.get();
  }

  public int effectiveWeight() {
    return effectiveWeight.get();
  }

  public String name() {
    return name;
  }

  public String id() {
    return id;
  }

  @Override
  public String toString() {
    return "ServiceInstance{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", weight=" + weight +
        ", effectiveWeight=" + effectiveWeight +
        '}';
  }

  ServiceInstance incWeight(int num) {
    weight.accumulateAndGet(num, (left, right) -> Math.min(left + right, 100));
    return this;
  }

  ServiceInstance decWeight(int num) {
    weight.accumulateAndGet(num, (left, right) -> Math.max(left - right, 0));
    return this;
  }

  ServiceInstance incEffectiveWeight(int num) {
    effectiveWeight.accumulateAndGet(num, (left, right) -> left + right);
    return this;
  }

  ServiceInstance decEffectiveWeight(int num) {
    effectiveWeight.accumulateAndGet(num, (left, right) -> left - right);
    return this;
  }

}
