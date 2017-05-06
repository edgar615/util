package com.edgar.util.loadbalancing;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 轮询从列表中选择.
 *
 * @author Edgar  Date 2016/8/5
 */
class RoundRobinStrategy implements ServiceProviderStrategy {

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