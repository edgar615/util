package com.github.edgar615.util.id;

/**
 * 从ID中提取出时间.
 */
public interface TimeExtracter<T> {

  /**
   * 从ID中提取时间.
   *
   * @param id ID
   * @return 时间
   */
  long fetchTime(T id);
}
