package com.edgar.util.base;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import java.util.Collection;
import java.util.Iterator;

/**
 * Guava Preconditions的扩展.
 * <p>
 * 此类参考了twitter的实现.
 *
 * @author Edgar
 */
public class MorePreconditions {
  private MorePreconditions() {
    throw new AssertionError("Not instantiable: " + MorePreconditions.class);
  }

  /**
   * 检查输入的字符串是否为null或者空.
   *
   * @param string 字符串
   */
  public static void checkNotNullOrEmpty(final String string) {
    Preconditions.checkArgument(!Strings.isNullOrEmpty(string),
                                "The validated string can not be null or empty");
  }

  /**
   * 检查输入的字符串是否为null或者空.
   *
   * @param string  字符串
   * @param message 错误消息
   */
  public static void checkNotNullOrEmpty(final String string, final String message) {
    Preconditions.checkArgument(!Strings.isNullOrEmpty(string), message);
  }

  /**
   * 检查输入的集合是否为{@code null}，或者为空.
   *
   * @param collection 集合
   * @param <E>        集合的泛型
   */
  public static <E> void checkNotEmpty(Collection<E> collection) {
    checkNotEmpty(collection, "The validated collection can not be null or empty");
  }

  /**
   * 检查输入的集合是否为{@code null}，或者为空.
   *
   * @param collection 集合
   * @param message    错误消息
   * @param <E>        集合的泛型
   */
  public static <E> void checkNotEmpty(Collection<E> collection, String message) {
    Preconditions.checkNotNull(collection, message);
    Preconditions.checkArgument(!collection.isEmpty(), message);
  }

  /**
   * 检查输入的集合是否包含{@code null}.
   *
   * @param iterable 集合
   * @param <E>集合泛型
   */
  public static <E extends Iterable<?>> void checkNoNullElements(final E iterable) {
    checkNoNullElements(iterable, "The validated collection contains null element at index: %s");
  }

  /**
   * 检查输入的集合是否包含{@code null}.
   *
   * @param iterable 集合
   * @param message  错误消息
   * @param <E>集合泛型
   */
  public static <E extends Iterable<?>> void checkNoNullElements(final E iterable,
                                                                 final String message) {
    Preconditions.checkNotNull(iterable);
    int i = 0;
    for (final Iterator<?> it = iterable.iterator(); it.hasNext(); i++) {
      Object val = it.next();
      Preconditions.checkNotNull(val, message, i);
    }
  }

  /**
   * 检查输入的集合与索引是否合法.
   *
   * @param collection 集合
   * @param index      索引
   * @param <E>        集合泛型
   * @return
   */
  public static <E> void checkIndex(final Collection<E> collection,
                                    final int index) {
    Preconditions.checkNotNull(collection);
    if (index < 0 || index >= collection.size()) {
      throw new IndexOutOfBoundsException(
              String.format("The validated collection index is invalid: %d", index));
    }
  }


  /**
   * 检查输入的集合与索引是否合法.
   *
   * @param collection 集合
   * @param index      索引
   * @param message    错误消息
   * @param <E>        集合泛型
   * @return
   */
  public static <E> void checkIndex(final Collection<E> collection,
                                    final int index, final String message) {
    Preconditions.checkNotNull(collection);
    if (index < 0 || index >= collection.size()) {
      throw new IndexOutOfBoundsException(String.format(message, index));
    }
  }


  /**
   * 检查输入的整数参数是否在某个范围内.
   *
   * @param argument 待检查的值.
   * @param minimum  最小值.
   * @param maximum  最大值.
   * @return 如果输入的参数合法则返回这个值.
   * @throws IllegalArgumentException 参数不在[minmum,maximum]的范围内.
   */
  public static int checkArgumentRange(int argument, int minimum, int maximum) {
    Preconditions.checkArgument(minimum <= argument, "%s must >=" + minimum, argument);
    Preconditions.checkArgument(argument <= maximum, "%s must <=" + maximum, argument);
    return argument;
  }

  /**
   * 检查输入的整数参数是否在某个范围内.
   *
   * @param argument 待检查的值.
   * @param minimum  最小值.
   * @param maximum  最大值.
   * @param message  错误信息，%s表示待检查的值.
   * @return 如果输入的参数合法则返回这个值.
   * @throws IllegalArgumentException 参数不在[minmum,maximum]的范围内.
   */
  public static int checkArgumentRange(int argument, int minimum, int maximum,
                                       String message) {
    Preconditions.checkArgument(minimum <= argument, message, argument);
    Preconditions.checkArgument(argument <= maximum, message, argument);
    return argument;
  }

  /**
   * 检查输入的浮点数参数是否在某个范围内.
   *
   * @param argument 待检查的值.
   * @param minimum  最小值.
   * @param maximum  最大值.
   * @return 如果输入的参数合法则返回这个值.
   * @throws IllegalArgumentException 参数不在[minmum,maximum]的范围内.
   */
  public static double checkArgumentRange(double argument, double minimum, double maximum) {
    Preconditions.checkArgument(minimum <= argument, "%s must >=" + minimum, argument);
    Preconditions.checkArgument(argument <= maximum, "%s must <=" + maximum, argument);
    return argument;
  }

  /**
   * 检查输入的浮点数参数是否在某个范围内.
   *
   * @param argument 待检查的值.
   * @param minimum  最小值.
   * @param maximum  最大值.
   * @param message  错误信息，%s表示待检查的值.
   * @return 如果输入的参数合法则返回这个值.
   * @throws IllegalArgumentException 参数不在[minmum,maximum]的范围内.
   */
  public static double checkArgumentRange(double argument, double minimum, double maximum,
                                          String message) {
    Preconditions.checkArgument(minimum <= argument, message, argument);
    Preconditions.checkArgument(argument <= maximum, message, argument);
    return argument;
  }


  /**
   * 检查所有的参数都为真.
   *
   * @param message   错误信息.
   * @param arguments 需要检查的参数.
   * @return 如果所有的参数为真，返回true.
   * @throws IllegalArgumentException 只要有一个参数为假.
   */
  public static boolean checkAllArguments(String message,
                                          Boolean... arguments) {
    for (Boolean argument : arguments) {
      if (!argument) {
        throw new IllegalArgumentException(message);
      }
    }
    return true;
  }


  /**
   * 检查至少有一个参数为真.
   *
   * @param message   错误信息.
   * @param arguments 需要检查的参数.
   * @return 如果有一个参数为真，返回true.
   * @throws IllegalArgumentException 如果没有参数为真.
   */
  public static boolean checkAnyArguments(String message,
                                          Boolean... arguments) {
    for (Boolean argument : arguments) {
      if (argument) {
        return true;
      }
    }
    throw new IllegalArgumentException(message);
  }

  /**
   * 检查对象是否是某个类的子类.
   *
   * @param type 类
   * @param obj  对象
   */
  public static void checkInstanceOf(final Class<?> type, final Object obj) {
    if (type.isInstance(obj) == false) {
      throw new IllegalArgumentException(
              String.format("Expected type: %s, actual: %s",
                            type.getName(),
                            obj == null ? "null" : obj.getClass().getName()));
    }
  }

  /**
   * 检查对象是否是某个类的子类.
   *
   * @param type    类
   * @param obj     对象
   * @param message 错误消息
   */
  public static void checkInstanceOf(final Class<?> type, final Object obj, final String message) {
    if (type.isInstance(obj) == false) {
      throw new IllegalArgumentException(message);
    }
  }
}
