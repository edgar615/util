package com.edgar.util.loadbalnacing;

import com.edgar.util.loadbalancing.ServiceInstance;
import com.edgar.util.loadbalancing.ServiceProviderStrategy;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edgar on 17-5-6.
 */
public class StrategyTest {
  List<ServiceInstance> instances = new ArrayList<>();

  @Before
  public void setUp() {
    instances.clear();
    instances.add(new ServiceInstance("a"));
    instances.add(new ServiceInstance("b"));
    instances.add(new ServiceInstance("c"));
  }

  protected List<String> select100(ServiceProviderStrategy strategy) {
    List<String> selected = new ArrayList<>();
    for (int i = 0; i < 3000; i++) {
      ServiceInstance instance = strategy.get(instances);
      selected.add(instance.id());
    }
    return selected;
  }
}
