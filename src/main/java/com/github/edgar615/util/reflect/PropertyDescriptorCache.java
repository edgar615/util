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

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * PropertyDescriptor的缓存.
 *
 * @param <T> 泛型
 *
 * 来源于de.cronn.reflection.util，做了一些改动，删除了一些我不会用的方法.
 */
class PropertyDescriptorCache<T> {

  private final Class<T> type;

  /**
   * 按照属性名称的缓存
   */
  private final Map<String, PropertyDescriptor> propertyDescriptorsByName = new LinkedHashMap<>();

  /**
   * 按照属性Field的缓存
   */
  private final Map<Field, PropertyDescriptor> propertyDescriptorsByField = new LinkedHashMap<>();

  /**
   * 按照Method的缓存
   */
  private final Map<Method, PropertyDescriptor> propertyDescriptorsByMethod = new LinkedHashMap<>();

  /**
   * Getter的缓存
   */
  private final Map<String, Method> getterByName = new LinkedHashMap<>();

  /**
   * Setter的缓存
   */
  private final Map<String, Method> setterByName = new LinkedHashMap<>();

  /**
   * 按照注解的缓存
   */
  private final Map<Class<? extends Annotation>, Map<PropertyDescriptor, Annotation>> propertyDescriptorsByAnnotation = new LinkedHashMap<>();

  private PropertyDescriptorCache(Class<T> type) {
    this.type = type;

    for (PropertyDescriptor propertyDescriptor : getAllPropertyDescriptors()) {
      propertyDescriptorsByName.put(propertyDescriptor.getName(), propertyDescriptor);

      Method readMethod = propertyDescriptor.getReadMethod();
      if (readMethod != null) {
        propertyDescriptorsByMethod.put(readMethod, propertyDescriptor);
        getterByName.put(propertyDescriptor.getName(), readMethod);
        putAnnotations(propertyDescriptor, readMethod.getAnnotations());
      }

      Method writeMethod = propertyDescriptor.getWriteMethod();
      if (writeMethod != null) {
        propertyDescriptorsByMethod.put(writeMethod, propertyDescriptor);
        setterByName.put(propertyDescriptor.getName(), writeMethod);
        putAnnotations(propertyDescriptor, writeMethod.getAnnotations());
      }
    }

    for (Field field : ReflectUtils.getFields(type, true)) {
      PropertyDescriptor propertyDescriptor = propertyDescriptorsByName.get(field.getName());
      if (propertyDescriptor != null) {
        propertyDescriptorsByField.put(field, propertyDescriptor);
        putAnnotations(propertyDescriptor, field.getAnnotations());
      }
    }
  }

  static <T> PropertyDescriptorCache<T> create(Class<T> type) {
    return new PropertyDescriptorCache<>(type);
  }

  PropertyDescriptor getDescriptorByName(String propertyName) {
    return propertyDescriptorsByName.get(propertyName);
  }

  PropertyDescriptor getDescriptorByMethod(Method method) {
    return propertyDescriptorsByMethod.get(method);
  }

  PropertyDescriptor getDescriptorByField(Field field) {
    return propertyDescriptorsByField.get(field);
  }

  <A extends Annotation> Map<PropertyDescriptor, A> getDescriptorsForAnnotation(
      Class<A> annotationClass) {
    @SuppressWarnings("unchecked")
    Map<PropertyDescriptor, A> descriptors = (Map<PropertyDescriptor, A>) propertyDescriptorsByAnnotation
        .getOrDefault(
            annotationClass, Collections.emptyMap());
    return Collections.unmodifiableMap(descriptors);
  }

  private void putAnnotations(PropertyDescriptor propertyDescriptor, Annotation[] annotations) {
    for (Annotation annotation : annotations) {
      propertyDescriptorsByAnnotation
          .computeIfAbsent(annotation.annotationType(), k -> new LinkedHashMap<>()) //
          .put(propertyDescriptor, annotation);
    }
  }

  Set<Field> getFields() {
    return propertyDescriptorsByField.keySet();
  }

  Set<Method> getMethods() {
    return propertyDescriptorsByMethod.keySet();
  }

  Map<String, PropertyDescriptor> getPropertyDescriptorsByName() {
    return propertyDescriptorsByName;
  }

  Map<Field, PropertyDescriptor> getPropertyDescriptorsByField() {
    return propertyDescriptorsByField;
  }

  Map<Method, PropertyDescriptor> getPropertyDescriptorsByMethod() {
    return propertyDescriptorsByMethod;
  }

  Map<String, Method> getGetterByName() {
    return getterByName;
  }

  Map<String, Method> getSetterByName() {
    return setterByName;
  }

  Map<Class<? extends Annotation>, Map<PropertyDescriptor, Annotation>> getPropertyDescriptorsByAnnotation() {
    return propertyDescriptorsByAnnotation;
  }

  private PropertyDescriptor[] getAllPropertyDescriptors() {
    try {
      PropertyDescriptor[] descriptors = Introspector.getBeanInfo(type).getPropertyDescriptors();
      // defensive copy to prevent modification of beanutils' internals
      descriptors = Arrays.copyOf(descriptors, descriptors.length);
      Arrays.sort(descriptors, Comparator.comparing(PropertyDescriptor::getName));
      return descriptors;
    } catch (IntrospectionException e) {
      throw new ReflectionException(e);
    }
  }

  Collection<PropertyDescriptor> getDescriptors() {
    return propertyDescriptorsByName.values();
  }

}
