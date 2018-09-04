package com.github.edgar615.util.lifecycle;

import java.util.Map;

/**
 * 表明这个类是可以配置的类.
 *
 * @author ypujante@linkedin.com
 */
public interface Configurable {

  /**
   * 读取Map中的配置属性
   *
   * @param config 配置对象
   */
  void configure(Map config);
}