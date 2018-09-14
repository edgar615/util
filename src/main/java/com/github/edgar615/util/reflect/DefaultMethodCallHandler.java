//package com.github.edgar615.util.reflect;
//
//import java.lang.invoke.MethodHandle;
//import java.lang.invoke.MethodHandles;
//import java.lang.invoke.MethodHandles.Lookup;
//import java.lang.reflect.Constructor;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ConcurrentMap;
//
//final class DefaultMethodCallHandler {
//
//  private DefaultMethodCallHandler() {
//  }
//
//  private static final ConcurrentMap<Method, MethodInvokeHandler> cache = new ConcurrentHashMap<>();
//
//  /**
//   * MethodHandle有三种调用形式：
//   *
//   * 1. invokeExact:调用此方法与直接调用底层方法一样，需要做到参数类型精确匹配；
//   *
//   * 2. invoke:参数类型松散匹配，通过asType自动适配；
//   *
//   * 3. invokeWithArguments:直接通过方法参数来调用。 其实现是先通过genericMethodType方法得到MethodType，
//   * 再通过MethodHandle的asType转换后得到一个新的MethodHandle，最后通过新MethodHandle的invokeExact方法来完成调用。
//   */
//  public static MethodInvokeHandler forMethod(Method method) {
//    return cache.computeIfAbsent(method, m -> {
//      MethodHandle handle = getMethodHandle(m);
//      return (proxy, args) -> handle.bindTo(proxy).invokeWithArguments(args);
//    });
//  }
//
//  /**
//   * 根据方法找到MethodHandle.
//   * @param method
//   * @return
//   */
//  private static MethodHandle getMethodHandle(Method method) {
//    Class<?> declaringClass = method.getDeclaringClass();
//
//    try {
//      Constructor<Lookup> constructor = MethodHandles.Lookup.class
//          .getDeclaredConstructor(Class.class, int.class);
//      constructor.setAccessible(true);
//
//      return constructor.newInstance(declaringClass, MethodHandles.Lookup.PRIVATE)
//          .unreflectSpecial(method, declaringClass);
//    } catch (IllegalAccessException | NoSuchMethodException |
//        InstantiationException | InvocationTargetException e) {
//      throw new ReflectionException(e);
//    }
//  }
//}