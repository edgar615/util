package com.edgar.util.loadbalancing;

import java.util.HashMap;
import java.util.Map;

/**
 * 带权重的服务节点.
 * <p>
 * weight:服务节点的权重，只有针对按权重选取的策略才会使用这个属性.
 * Created by edgar on 17-5-6.
 */
public class WeightServiceInstance extends ServiceInstance {

  private final int weight;

  private final Map<String, Object> meta = new HashMap<>();

  public WeightServiceInstance(String id, int weight) {
    super(id);
    this.weight = weight;
  }

  public int weight() {
    return weight;
  }

  @Override
  public WeightServiceInstance meta(String key, Object value) {
    meta.put(key, value);
    return this;
  }
}
