package com.github.edgar615.util.reflect;

import com.github.edgar615.util.db.Device;
import com.github.edgar615.util.db.PrimaryKey;
import org.junit.Test;

/**
 * PropertyDescriptorCache的单元测测试.
 *
 * @author Edgar
 * @create 2018-09-06 12:45
 **/
public class BeanUtilsTest {

  @Test
  public void testName() throws NoSuchFieldException {
    BeanUtils.getPropertyDescriptors(SomeBean.class).stream().forEach(d -> System.out.println(d.getName()));
    System.out.println(BeanUtils.getPropertyDescriptorByName(SomeBean.class, "id"));
    System.out.println(BeanUtils.hasProperty(SomeBean.class, "id"));
    System.out.println(ReflectUtils.getFields(SomeBean.class, false));
    System.out.println(BeanUtils.getFieldWithName(SomeBean.class, "id"));
    System.out.println(BeanUtils.getFields(SomeBean.class));
    System.out.println(BeanUtils.getMethods(SomeBean.class));
    System.out.println(BeanUtils.getPropertyDescriptorsWithAnnotation(Device.class, PrimaryKey.class));
  }
}
