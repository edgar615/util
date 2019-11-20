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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.Test;

/**
 * Created by Edgar on 2016/2/19.
 *
 * @author Edgar  Date 2016/2/19
 */
public class ReflectUtilsTest {

  @Test
  public void testClassLoader() {
    System.out.println(ReflectUtils.getDefaultClassLoader());
  }

  @Test
  public void testComputeSignature() throws NoSuchMethodException {
    System.out.println(ReflectUtils.computeSignature(Object.class.getDeclaredMethod("clone")));
    System.out.println(ReflectUtils.computeSignature(List.class.getMethod("add",
        Object.class)));
  }

  @Test
  public void testExtractAllInterfaces() throws NoSuchMethodException {
    Class[] classes = ReflectUtils.extractAllInterfaces(new ArrayList<>());
    for (Class clazz : classes) {
      System.out.println(clazz);
    }
  }

  @Test
  public void testIsSubClassOrInterfaceOf() throws NoSuchMethodException {
    System.out.println(ReflectUtils.isSubClassOrInterfaceOf(ArrayList.class, Collection.class));
  }
}
