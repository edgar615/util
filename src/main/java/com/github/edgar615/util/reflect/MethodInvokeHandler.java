package com.github.edgar615.util.reflect;

import com.github.edgar615.util.interceptor.Invocation;

/**
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
public interface MethodInvokeHandler {

  Object invoke(Invocation invocation) throws Throwable;
}