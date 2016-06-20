package com.edgar.util.id;

/**
 * 从ID中提取出时间.
 */
public interface TimeExtracter {

  /**
   * 从ID中提取时间.
   *
   * @param id ID
   * @return 时间
   */
  long fetchTime(long id);
}
