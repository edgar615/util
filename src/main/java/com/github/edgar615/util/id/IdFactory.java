package com.github.edgar615.util.id;

/**
 * 主键生成的工厂类.
 * Created by edgar on 16-4-2.
 *
 * @author Edgar
 */
public interface IdFactory<T> {

    /**
     * 主键生成方法
     *
     * @return 主键
     */
    T nextId();

    /**
     * 创建一个默认的IdFactory.
     *
     * @return IdFactory
     */
    static IdFactory<Long> defaultFactory() {
        return DefaultIdFactory.instance();
    }

    /**
     * 根据serverId创建一个IdFacory,每个serverId只会创建一个IdFactory
     *
     * @param serverId 服务器ID
     * @return IdFactory
     */
    static IdFactory<Long> simpleSnowflake(int serverId) {
        return SimpleSnowflakeIdFactory.create(serverId);
    }

    /**
     * 创建一个Boundary Flake的IdFactory
     *
     * @return IdFactory
     */
    static IdFactory<String> boundaryflake() {
        return BoundaryFlakeIdFactory.instance();
    }

    /**
     * 循环直到下一个毫秒
     *
     * @param lastTime 原毫秒数
     * @return
     */
    default long tilNextMillis(long lastTime) {
        long timestamp = currentTime();
        while (timestamp <= lastTime) {
            timestamp = currentTime();
        }
        return timestamp;
    }

    default long currentTime() {
        return System.currentTimeMillis();
    }

}
