package com.edgar.util.id;

/**
 * 从ID中提取出自增序列.
 */
public interface SeqExtracter {

  /**
   * 从ID中提取自增序列.
   *
   * @param id ID
   * @return 自增序列
   */
  long fetchSeq(long id);
}
