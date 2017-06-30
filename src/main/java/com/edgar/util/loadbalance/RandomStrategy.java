package com.edgar.util.loadbalance;

import java.util.List;
import java.util.Random;

/**
 * 从给定列表中随机选择一个对象.
 * Created by edgar on 17-5-6.
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