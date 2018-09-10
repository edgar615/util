package com.github.edgar615.util.reflect;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * JavaBean的工具类.
 *
 * @author Edgar
 * @create 2018-09-06 13:53
 **/
public class BeanUtils {

  private static final Map<Class<?>, PropertyDescriptorCache<?>> cache = new ConcurrentHashMap<>();

  private BeanUtils() {
    throw new AssertionError("Not instantiable: " + BeanUtils.class);
  }

  public static Map<String, Object> toMap(Object obj) {
    if (obj == null) {
      return null;
    }
    Map<String, Object> map = new HashMap<>();
    Map<String, Method> getterMethods = getCache(obj.getClass()).getGetterByName();
    for (Map.Entry<String, Method> methods : getterMethods.entrySet()) {
      String key = methods.getKey();
      // 过滤class属性
      if (!key.equals("class")) {
        // 得到property对应的getter方法
        try {
          Method getter = methods.getValue();
          Object value = getter.invoke(obj);
          map.put(key, value);
        } catch (Exception e) {
          throw new ReflectionException(e);
        }
      }
    }
    return map;
  }

  /**
   * 根据属性名称在java bean中查找PropertyDescriptor,不存在的时候返回null.
   *
   * @param beanClass class对象
   * @param propertyName 属性名
   * @return PropertyDescriptor
   */
  public static PropertyDescriptor getPropertyDescriptorByName(Class<?> beanClass,
      String propertyName) {
    PropertyDescriptorCache<?> propertyDescriptorCache = getCache(beanClass);
    return propertyDescriptorCache.getDescriptorByName(propertyName);
  }

  /**
   * 根据属性名称在java bean中查找PropertyDescriptor,不存在的时候返回null抛出IllegalArgumentException.
   *
   * @param beanClass class对象
   * @param propertyName 属性名
   * @return PropertyDescriptor
   */
  public static PropertyDescriptor getPropertyDescriptorByNameOrThrow(Class<?> beanClass,
      String propertyName) {
    PropertyDescriptor propertyDescriptor = getPropertyDescriptorByName(beanClass, propertyName);
    if (propertyDescriptor == null) {
      throw new IllegalArgumentException(String
          .format("Property '%s' not found for '%s'", propertyName, beanClass.getSimpleName()));
    }
    return propertyDescriptor;
  }

  /**
   * 返回java bean中的PropertyDescriptor对象.
   *
   * @param type class对象
   * @return PropertyDescriptor集合
   */
  public static Collection<PropertyDescriptor> getPropertyDescriptors(Class<?> type) {
    PropertyDescriptorCache<?> propertyDescriptorCache = getCache(type);
    return propertyDescriptorCache.getDescriptors();
  }

  public static <A extends Annotation> Map<PropertyDescriptor, A> getPropertyDescriptorsWithAnnotation(
      Class<?> type,
      Class<A> annotationClass) {
    PropertyDescriptorCache<?> propertyDescriptorCache = getCache(type);
    return propertyDescriptorCache.getDescriptorsForAnnotation(annotationClass);
  }

  /**
   * 判断某个属性是否同时有getter/setter方法.
   *
   * @param descriptor PropertyDescriptor
   * @return 同时存在返回true
   */
  public static boolean isFullyAccessible(PropertyDescriptor descriptor) {
    return isReadable(descriptor) && isWritable(descriptor);
  }

  /**
   * 判断某个属性是否同时有getter/setter方法.
   *
   * @param beanClass class对象
   * @param propertyName 属性名
   * @return 同时存在返回true
   */
  public static boolean isFullyAccessible(Class<?> beanClass,
      String propertyName) {
    return isReadable(beanClass, propertyName) && isWritable(beanClass, propertyName);
  }

  /**
   * 判断某个属性是否有setter方法.
   *
   * @param descriptor PropertyDescriptor
   * @return 存在返回true
   */
  public static boolean isWritable(PropertyDescriptor descriptor) {
    return descriptor.getWriteMethod() != null;
  }


  /**
   * 判断某个属性是否有setter方法.
   *
   * @param beanClass class对象
   * @param propertyName 属性名
   * @return 存在返回true
   */
  public static boolean isWritable(Class<?> beanClass,
      String propertyName) {
    PropertyDescriptor descriptor = getPropertyDescriptorByName(beanClass, propertyName);
    if (descriptor == null) {
      return false;
    }
    return descriptor.getWriteMethod() != null;
  }

  /**
   * 判断某个属性是否有getter方法.
   *
   * @param descriptor PropertyDescriptor
   * @return 存在返回true
   */
  public static boolean isReadable(PropertyDescriptor descriptor) {
    return descriptor.getReadMethod() != null;
  }

  /**
   * 判断某个属性是否有getter方法.
   *
   * @param beanClass class对象
   * @param propertyName 属性名
   * @return 存在返回true
   */
  public static boolean isReadable(Class<?> beanClass,
      String propertyName) {
    PropertyDescriptor descriptor = getPropertyDescriptorByName(beanClass, propertyName);
    if (descriptor == null) {
      return false;
    }
    return descriptor.getReadMethod() != null;
  }

  /**
   * 判断Java bean中是否存在某个属性.
   *
   * @param beanClass class对象
   * @param propertyName 属性名
   * @return 存在返回true
   */
  public static boolean hasProperty(Class<?> beanClass, String propertyName) {
    return getPropertyDescriptorByName(beanClass, propertyName) != null;
  }

  /**
   * 根据属性名称在Java bean中查找Field对象.
   *
   * @param beanClass class对象
   * @param propertyName 属性名
   * @return Field对象
   * @throws NoSuchFieldException 如果为找到，抛出异常
   */
  public static Field getFieldWithName(Class<?> beanClass, String propertyName)
      throws NoSuchFieldException {
    try {
      return beanClass.getDeclaredField(propertyName);
    } catch (NoSuchFieldException e) {
      Class<?> superclass = beanClass.getSuperclass();
      if (!superclass.equals(Object.class)) {
        return getFieldWithName(superclass, propertyName);
      }
      throw e;
    }
  }

  /**
   * 返回Java bean的Fields对象，包括父对象的.
   *
   * @param beanClass class对象
   * @return Field的集合
   */
  public static Set<Field> getFields(Class<?> beanClass) {
    return getCache(beanClass).getFields();
  }

  /**
   * 返回Java bean的Method对象，包括父对象的.
   *
   * @param beanClass class对象
   * @return Method的集合
   */
  public static Set<Method> getMethods(Class<?> beanClass) {
    return getCache(beanClass).getMethods();
  }

  /**
   * 根据属性名称在Java bean中查找setter方法.
   *
   * @param beanClass class对象
   * @param propertyName 属性名
   * @return Method，不存在的时候回返回null
   */
  public static Method writeMethod(Class<?> beanClass, String propertyName) {
    PropertyDescriptor descriptor = getPropertyDescriptorByName(beanClass, propertyName);
    if (descriptor == null) {
      return null;
    }
    return descriptor.getWriteMethod();
  }

  /**
   * 根据属性名称在Java bean中查找getter方法.
   *
   * @param beanClass class对象
   * @param propertyName 属性名
   * @return Method 不存在的时候回返回null
   */
  public static Method readMethod(Class<?> beanClass, String propertyName) {
    PropertyDescriptor descriptor = getPropertyDescriptorByName(beanClass, propertyName);
    if (descriptor == null) {
      return null;
    }
    return descriptor.getReadMethod();
  }

  @SuppressWarnings("unchecked")
  private static <T> PropertyDescriptorCache<T> getCache(Class<T> type) {
    return (PropertyDescriptorCache<T>) cache
        .computeIfAbsent(type, PropertyDescriptorCache::create);
  }

}
