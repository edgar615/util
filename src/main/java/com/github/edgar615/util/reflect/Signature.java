package com.github.edgar615.util.reflect;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *用来要定义拦截的方法，从Mybatis借鉴而来.
 *
 * 实际上现在还没有实现基于注解的拦截.先保留后续扩展
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Signature {
  Class<?> type();

  String method();

  Class<?>[] args() default {};
}