package com.edgar.util.id;

/**
 * Created by edgar on 16-4-3.
 */
public interface IdExtracter {

  /**
   * 从主键中提取时间.
   * 将ID左移23位，提取出时间
   *
   * @param id 主键
   * @return 时间
   */
  long fetchTime(long id);

  /**
   * 从主键中提取serverId.
   * 原ID与毫秒数左移异或得到分片ID和自增主键,然后右移10位得到分片ID
   *
   * @param id 主键
   * @return 自增ID
   */
  long fetchServerId(long id);

  /**
   * 从主键中提取自增ID.
   *
   * @param id 主键
   * @return 自增ID
   */
  long fetchSeqId(long id);
}
