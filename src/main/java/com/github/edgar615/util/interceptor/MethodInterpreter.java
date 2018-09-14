package com.github.edgar615.util.interceptor;

import com.github.edgar615.util.reflect.MethodInvokeHandler;
import java.lang.reflect.Method;

/**
 * 这个接口用来定义那些方法需要拦截.
 *
 * @author Edgar
 */
@FunctionalInterface
public interface MethodInterpreter {

//  static MethodInterpreter binding(Object target) {
//    return binding(target, method -> {
//      throw new IllegalStateException(String.format(
//          "Target class %s does not support method %s",
//          target.getClass(), method));
//    });
//  }
//
//  static MethodInterpreter binding(Object target, MethodInterpreter unboundInterpreter) {
//    return method -> {
//      if (method.getDeclaringClass().isAssignableFrom(target.getClass())) {
//        return (proxy, args) -> method.invoke(target, args);
//      }
//
//      return unboundInterpreter.interpret(method);
//    };
//  }
//
//  /**
//   * 如果方法是default则使用DefaultMethodCallHandler，否则使用nonDefaultInterpreter
//   */
//  static MethodInterpreter handlingDefaultMethods(MethodInterpreter nonDefaultInterpreter) {
//    return method -> method.isDefault() ? DefaultMethodCallHandler.forMethod(method)
//        : nonDefaultInterpreter.interpret(method);
//  }
//
//  static MethodInterpreter caching(MethodInterpreter interpreter) {
//    ConcurrentMap<Method, MethodInvokeHandler> cache = new ConcurrentHashMap<>();
//    return method -> cache.computeIfAbsent(method, interpreter::interpret);
//  }

  /**
   * 根据方法找到合适的MethodCallHandler.
   *
   * @param method 方法
   * @return MethodInvokeHandler
   */
  MethodInvokeHandler interpret(Method method);
}