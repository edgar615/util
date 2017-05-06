package com.edgar.util.loadbalnacing;

import com.edgar.util.loadbalancing.ServiceInstance;
import com.edgar.util.loadbalancing.ServiceProviderStrategy;
import com.edgar.util.loadbalancing.WeightServiceInstance;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by edgar on 17-5-6.
 */
public class WeightRoundRobinStrategyTest {

  List<ServiceInstance> instances = new ArrayList<>();

  @Before
  public void setUp() {
    instances.clear();
    instances.add(new WeightServiceInstance("a", 5));
    instances.add(new WeightServiceInstance("b", 1));
    instances.add(new WeightServiceInstance("c", 1));
  }


  @Test
  public void testWeight() {
    ServiceProviderStrategy selectStrategy = ServiceProviderStrategy.weightRoundRobin();
    List<String> selected = select(selectStrategy);
    Assert.assertEquals(3, new HashSet<>(selected).size());
    long aSize = selected.stream()
        .filter(i -> "a".equals(i))
        .count();
    long bSize = selected.stream()
        .filter(i -> "b".equals(i))
        .count();
    long cSize = selected.stream()
        .filter(i -> "c".equals(i))
        .count();
    Assert.assertEquals(aSize, 5000);
    Assert.assertEquals(bSize, 1000);
    Assert.assertEquals(cSize, 1000);
  }

  private  List<String> select(ServiceProviderStrategy strategy) {
    List<String> selected = new ArrayList<>();
    for (int i = 0; i < 7000; i++) {
      ServiceInstance instance = strategy.get(instances);
      selected.add(instance.id());
    }
    return selected;
  }
}