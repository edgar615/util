package com.github.edgar615.util.reflect;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
