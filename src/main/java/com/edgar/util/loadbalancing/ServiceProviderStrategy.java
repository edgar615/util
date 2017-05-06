package com.edgar.util.loadbalancing;

import java.util.List;

/**
 * 从一组节点中选择一个节点的策略.
 * <p>
 * 主要是用来实现服务发现的负载均衡
 *
 * @author Edgar
 */
public interface ServiceProviderStrategy {
  /**
   * 从给定一组对象中，返回一个对象.
   *
   * @param instances the instance list
   * @return the instance to use
   */
  ServiceInstance get(List<ServiceInstance> instances);

  static ServiceProviderStrategy random() {
    return new RandomStrategy();
  }

  static ServiceProviderStrategy weightRoundRobin() {
    return new WeightRoundbinStrategy();
  }

  static ServiceProviderStrategy roundRobin() {
    return new RoundRobinStrategy();
  }

  static ServiceProviderStrategy sticky(ServiceProviderStrategy masterStrategy) {
    return new StickyStrategy(masterStrategy);
  }
}
