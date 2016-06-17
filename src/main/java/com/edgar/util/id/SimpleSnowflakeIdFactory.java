package com.edgar.util.id;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ID生成类.
 * 参考twitter, instagram的实现，但是并没有使用workerId
 * 使用41 bit来存放时间，精确到毫秒，可以使用41年。
 * 使用10 bit来存放逻辑分片ID,可以分1024个片。<b>这里其实应该有一个中心服务器来统一分配分片ID</b>
 * 使用13 bit来存放自增长ID，意味着每台机器，每秒最多可以生成8192个ID
 * <p>
 * <b>分片和自增id的位数可以根据服务调整，因为我们不会有太多的分片，所以只用了10位</b>
 *
 * 在分布式环境下，多个服务需要保证serverId的唯一性，否则各个服务可能会生成重复的主键.
 *
 * @author Edgar
 */
public class SimpleSnowflakeIdFactory implements IdFactory, IdExtracter {


  private static final ConcurrentMap<Integer, SimpleSnowflakeIdFactory> FACTORY_HOLDER = new ConcurrentHashMap<>();

  /**
   * 自增序列
   */
  private static final AtomicLong seq = new AtomicLong(1);

  /**
   * 服务器id
   */
  private final int serverId;

  private SimpleSnowflakeIdFactory(int serverId) {
    this.serverId = serverId % 1024;
  }

  /**
   * 使用服务器id创建一个IdFactory,每个服务器id始终只有一个实例，避免同一个服务器id生成重复的id.
   *
   * <b>在分布式环境下，必须保证每个分布式服务使用的serverId不同，否则还是可能生成重复的id</b>
   * @param serverId 服务器id
   * @return IdFactory
   */
  public static SimpleSnowflakeIdFactory create(int serverId) {
    SimpleSnowflakeIdFactory idFactory = FACTORY_HOLDER.get(serverId);
    if (idFactory != null) {
      return idFactory;
    }
    idFactory = FACTORY_HOLDER.putIfAbsent(serverId, new SimpleSnowflakeIdFactory(serverId));
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
    long time = System.currentTimeMillis();
    long seqId = seq.getAndIncrement();
    long id = time << 22;
    id |= serverId << 12;
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
    return id >> 22;
  }

  /**
   * 从主键中提取serverId.
   * 原ID与毫秒数左移异或得到分片ID和自增主键,然后右移10位得到分片ID
   *
   * @param id 主键
   * @return 自增ID
   */
  public long fetchServerId(long id) {
    return (id ^ (fetchTime(id) << 22)) >> 12;
  }

  /**
   * 从主键中提取自增ID.
   *
   * @param id 主键
   * @return 自增ID
   */
  @Override
  public long fetchSeqId(long id) {
    return (id ^ (fetchTime(id) << 22)) ^ (fetchServerId(id) << 12);
  }
}
