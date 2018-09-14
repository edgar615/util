package com.github.edgar615.util.interceptor;

/**
 * 基于动态代理实现的拦截器.
 *
 * InvocationHandler主要做两件事：
 *
 * 1. 判断如何处理方法调用
 *
 * 2. 执行必要的操作并返回结果.
 *
 * 我们可以把InvocationHandler的任务分成两个部分：
 *
 * 1. 决定一个给定的方法做什么
 *
 * 2. 执行第一步的决定。
 *
 * 这个接口用来决定给定方法做什么。
 *
 * @author Edgar
 */
@FunctionalInterface
public interface Interceptor {

  /**
   * 执行拦截器
   *
   * @param invocation 被拦截的方法
   * @return 返回值
   * @throws Throwable 异常
   */
  Object intercept(Invocation invocation) throws Throwable;
}