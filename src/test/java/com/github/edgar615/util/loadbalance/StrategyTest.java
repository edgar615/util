package com.github.edgar615.util.loadbalance;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;

/**
 * Created by edgar on 17-5-6.
 */
public class StrategyTest {

  List<ServiceInstance> instances = new ArrayList<>();

  @Before
  public void setUp() {
    instances.clear();
    instances.add(new ServiceInstance("a", "test"));
    instances.add(new ServiceInstance("b", "test"));
    instances.add(new ServiceInstance("c", "test"));
  }

  protected List<String> select3000(ProviderStrategy strategy) {
    List<String> selected = new ArrayList<>();
    for (int i = 0; i < 3000; i++) {
      ServiceInstance instance = strategy.get(instances);
      selected.add(instance.id());
    }
    return selected;
  }

}
