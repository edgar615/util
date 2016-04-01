package com.edgar.util.id;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 主键生成类.
 */
public class DefaultIdFactory implements IdFactory {

  private static final DefaultIdFactory INSTANCE = new DefaultIdFactory();
  private final AtomicLong seq = new AtomicLong(1);

  public static DefaultIdFactory instance() {
    return INSTANCE;
  }

  /**
   * 参考instagram的实现
   * 使用41 bit来存放时间，精确到毫秒，可以使用41年。
   * 使用10 bit来存放逻辑分片ID,可以分1024个片。
   * 使用13 bit来存放自增长ID，意味着每台机器，每毫秒最多可以生成8192个ID
   * <p>
   * <b>分片和自增id的位数可以根据服务调整，因为我们不会有太多的分片，所以只用了10位</b>
   *
   * @param serverId 服务器id
   * @return
   */
  @Override
  public long generateId(int serverId) {
    serverId = serverId % 1024;
    long time = System.currentTimeMillis() / 1000;
    long seqId = seq.getAndIncrement();
    long id = time << (64 - 41);
    id |= serverId << (64 - 41 - 10);
    id |= (seqId % 8192);
    return id;
  }

  /**
   * 从主键中提取时间.
   * 将ID左移23位，提取出时间
   *
   * @param id 主键
   * @return 时间
   */
  private long calTime(long id) {
    return id >> (64 - 41);
  }

  /**
   * 从主键中提取serverId.
   * 原ID与毫秒数左移异或得到分片ID和自增主键,然后右移10位得到分片ID
   *
   * @param id 主键
   * @return 自增ID
   */
  @Override
  public long calServerId(long id) {
    return (id ^ (calTime(id) << 23)) >> 13;
  }

  /**
   * 从主键中提取自增ID.
   *
   * @param id 主键
   * @return 自增ID
   */
  private long calSeqId(long id) {
    return (id ^ (calTime(id) << 23)) ^ (calServerId(id) << 13);
  }
}
