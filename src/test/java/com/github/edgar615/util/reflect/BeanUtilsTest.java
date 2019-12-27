/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.edgar615.util.reflect;

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
    BeanUtils.getPropertyDescriptors(SomeBean.class).stream()
        .forEach(d -> System.out.println(d.getName()));
    System.out.println(BeanUtils.getPropertyDescriptorByName(SomeBean.class, "id"));
    System.out.println(BeanUtils.hasProperty(SomeBean.class, "id"));
    System.out.println(ReflectUtils.getFields(SomeBean.class, false));
    System.out.println(BeanUtils.getFieldWithName(SomeBean.class, "id"));
    System.out.println(BeanUtils.getFields(SomeBean.class));
    System.out.println(BeanUtils.getMethods(SomeBean.class));
    System.out
        .println(BeanUtils.getPropertyDescriptorsWithAnnotation(Device.class, PrimaryKey.class));
  }
}
