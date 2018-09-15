package com.github.edgar615.util.interceptor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *用来要定义拦截的方法，从Mybatis借鉴而来.
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Signature {

  /**
   * 类
   * @return 类
   */
  Class<?> type();

  /**
   * 方法名
   *
   * @return 方法名
   */
  String method();

  /**
   * 参数类型
   *
   * @return 参数类型
   */
  Class<?>[] args() default {};
}