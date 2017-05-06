package com.edgar.util.loadbalancing;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

/**
 * 服务节点.
 * 这个对象只定义了三个属性：
 * <p>
 * id: 服务节点的id,如果id和meta相同，则认为两个对象是同一个对象.
 * <p>
 * <p>
 * meta: 服务节点的其他属性
 * <p>
 * <p>
 * weight:服务节点的权重，只有针对按权重选取的策略才会使用这个属性.
 * Created by edgar on 17-5-6.
 */
public class ServiceInstance {
  private final String id;

  private final Map<String, Object> meta = new HashMap<>();

  public ServiceInstance(String id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ServiceInstance)) return false;

    ServiceInstance that = (ServiceInstance) o;

    if (!id.equals(that.id)) return false;
    if (!meta.equals(that.meta)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + meta.hashCode();
    return result;
  }

  public String id() {
    return id;
  }

  public Map<String, Object> meta() {
    return ImmutableMap.copyOf(meta);
  }

  public Object meta(String key) {
    return meta.get(key);
  }

  public ServiceInstance meta(String key, Object value) {
    meta.put(key, value);
    return this;
  }
}
