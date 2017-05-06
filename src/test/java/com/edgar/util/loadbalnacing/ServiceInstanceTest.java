package com.edgar.util.loadbalnacing;

import com.edgar.util.loadbalancing.ServiceInstance;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by edgar on 17-5-6.
 */
public class ServiceInstanceTest {

  @Test
  public void testSame() {
    String id = UUID.randomUUID().toString();
    ServiceInstance instance1 = new ServiceInstance(id);
    ServiceInstance instance2 = new ServiceInstance(id);

    List<ServiceInstance> list = new ArrayList<>();
    list.add(instance1);
    list.add(instance2);
    Assert.assertNotSame(instance1, instance2);
    Assert.assertEquals(instance1, instance2);

    instance1.meta("foo", 1);
    instance2.meta("foo", 1);

    Assert.assertNotSame(instance1, instance2);
    Assert.assertEquals(instance1, instance2);

    instance2.meta("foo", 2);
    Assert.assertNotSame(instance1, instance2);
    Assert.assertNotEquals(instance1, instance2);

  }
}
