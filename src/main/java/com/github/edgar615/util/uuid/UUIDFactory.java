package com.github.edgar615.util.uuid;


import com.github.edgar615.util.concurrent.ConcurrentUUIDFactory;
import java.util.UUID;

/**
 * 生成UUID的接口.
 *
 * @author Edgar
 */
@FunctionalInterface
public interface UUIDFactory {

  /**
   * Generates a new version 4 UUID.
   *
   * @return the newly generated UUID
   */
  UUID uuid();

  static UUIDFactory defaultUUIDFactory() {
    return new DefaultUUIDFactory();
  }


  /**
   * 创建ConcurrentUUIDFactory对象.
   *
   * @return ConcurrentUUIDFactory
   */
  static UUIDFactory concurrentUUIDFactory() {
    return ConcurrentUUIDFactory.create();
  }
}