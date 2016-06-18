package com.edgar.util.id;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ID生成类.
 * <p>
 * snowflake是来自于Twitter的一个开源算法
 * 0    00000.....000 00000 00000 000000000000
 * |    |___________| |___| |___| |__________|
 * |         |         |     |         |
 * 1bit     41bit      5bit  5bit     12bit
 * </p>
 * 核心代码就是毫秒级时间41位+机器ID 10位+毫秒内序列12位
 * 第一段:1bit 预留 实际上是作为long的符号位
 * 第二段:41bit 时间标记 记录的是当前时间与元年的时间差
 * 第三段:5bit 数据中心Id标记  记录的是数据中心的Id,5bit最大可以表示32个数据中心,这么多数据中心能保证全球范围内服务可用
 * 第四段:5bit 节点标记  记录的是单个数据中心里面服务节点的Id,同理也是最大可以有32个节点,这个除非是整个数据中心离线,否则可以保证服务永远可用
 * 第五段:12bit 单毫秒内自增序列 每毫秒可以生成4096个ID
 * <p>
 * instagram对他做来一点改造，直接在数据库的实现，通过触发器实现
 * 使用41 bit来存放时间，精确到毫秒.
 * 使用13 bit来存放逻辑分片ID,可以分8192个片。
 * 使用10 bit来存放自增长ID，意味着每台机器，每秒最多可以生成1024个ID
 * <p>
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
     * <p>
     * <b>在分布式环境下，必须保证每个分布式服务使用的serverId不同，否则还是可能生成重复的id</b>
     *
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
    public long nextId() {
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
