package com.edgar.util.id;

/**
 * 从ID提取服务器ID.
 *
 * @author Edgar  Date 2016/6/20
 */
public interface ServerExtracter<T> {

  /**
   * 从ID中提取服务器ID
   *
   * @param id ID
   * @return 服务器ID
   */
  long fetchServer(T id);
}
