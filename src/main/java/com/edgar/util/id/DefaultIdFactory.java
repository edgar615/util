package com.edgar.util.id;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 主键生成类.
 */
public class DefaultIdFactory implements IdFactory, IdExtracter {
  private static final ConcurrentMap<Integer, DefaultIdFactory> FACTORY_HOLDER = new ConcurrentHashMap<>();

  /**
   * 自增序列
   */
  private static final AtomicLong seq = new AtomicLong(1);

  /**
   * 服务器id
   */
  private final int serverId;

  private DefaultIdFactory(int serverId) {
    this.serverId = serverId % 1024;
  }

  /**
   * 使用服务器id创建一个IdFactory,每个服务器id始终只有一个实例，避免同一个服务器id生成重复的id.
   *
   * <b>在分布式环境下，必须保证每个分布式服务使用的serverId不同，否则还是可能生成重复的id</b>
   * @param serverId 服务器id
   * @return IdFactory
   */
  public static DefaultIdFactory create(int serverId) {
    DefaultIdFactory idFactory = FACTORY_HOLDER.get(serverId);
    if (idFactory != null) {
      return idFactory;
    }
    idFactory = FACTORY_HOLDER.putIfAbsent(serverId, new DefaultIdFactory(serverId));
    if (idFactory != null) {
      return idFactory;
    } else {
      return FACTORY_HOLDER.get(serverId);
    }
  }

  /**
   * 参考twitter, instagram的实现
   * 使用41 bit来存放时间，精确到毫秒，可以使用41年。
   * 使用10 bit来存放逻辑分片ID,可以分1024个片。
   * 使用13 bit来存放自增长ID，意味着每台机器，每毫秒最多可以生成8192个ID
   * <p>
   * <b>分片和自增id的位数可以根据服务调整，因为我们不会有太多的分片，所以只用了10位</b>
   *
   * @return
   */
  @Override
  public long generateId() {
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
  @Override
  public long fetchTime(long id) {
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
  public long fetchServerId(long id) {
    return (id ^ (fetchTime(id) << 23)) >> 13;
  }

  /**
   * 从主键中提取自增ID.
   *
   * @param id 主键
   * @return 自增ID
   */
  @Override
  public long fetchSeqId(long id) {
    return (id ^ (fetchTime(id) << 23)) ^ (fetchServerId(id) << 13);
  }
}
