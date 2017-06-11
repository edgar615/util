package com.edgar.util.loadbalance;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 主从模式.
 * 一旦一个节点被选定，这个节点就会被作为一个主节点，其他节点作为备份节点，以后每次选择节点都会直接选择主节点．
 * <p>
 * 注意：如果主节点已经不在给定对列表中，那么会使用主节点的选择策略重新选择一个节点作为主节点
 * <p>
 * Created by edgar on 17-5-6.
 */
class StickyStrategy implements ProviderStrategy {

  private final ProviderStrategy masterStrategy;

  private final AtomicReference<ServiceInstance> ourInstance = new AtomicReference<>(null);

  private final AtomicInteger instanceNumber = new AtomicInteger(-1);

  public StickyStrategy(ProviderStrategy masterStrategy) {
    this.masterStrategy = masterStrategy;
  }

  @Override
  public ServiceInstance get(List<ServiceInstance> instances) {

    ServiceInstance localOurInstance = ourInstance.get();
    if (localOurInstance != null) {
      long count = instances.stream()
              .filter(i -> i.id().equals(localOurInstance.id()))
              .count();
      if (count == 0) {
        ourInstance.compareAndSet(localOurInstance, null);
      }
    }

    if (ourInstance.get() == null) {
      ServiceInstance instance = masterStrategy.get(instances);
      if (ourInstance.compareAndSet(null, instance)) {
        instanceNumber.incrementAndGet();
      }
    }
    return ourInstance.get();
  }
}
