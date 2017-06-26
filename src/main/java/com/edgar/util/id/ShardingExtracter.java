package com.edgar.util.id;

/**
 * 从ID提取分片ID.
 *
 * @author Edgar  Date 2016/6/20
 */
public interface ShardingExtracter<T> {

  /**
   * 从ID中提取分片ID
   *
   * @param id ID
   * @return 分片ID
   */
  long fetchSharding(T id);
}
