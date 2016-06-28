package com.edgar.util.lifecycle;

/**
 * 表明这个类是可以启动的类.
 *
 * @author ypujante@linkedin.com
 */
public interface Startable {
  /**
   * 启动方法，尽量不要阻塞该方法.
   */
  void start();
}