package com.edgar.util.id;

/**
 * 主键生成的工厂类.
 * Created by edgar on 16-4-2.
 *
 * @author Edgar
 */
public interface IdFactory {

  /**
   * 主键生成方法
   *
   * @return 主键
   */
  long nextId();

  /**
   * 创建一个默认的IdFactory.
   *
   * @return IdFactory
   */
  static IdFactory defaultFactory() {
    return DefaultIdFactory.instance();
  }

  static IdFactory simpleSnowflake(int serverId) {
    return SimpleSnowflakeIdFactory.create(serverId);
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
