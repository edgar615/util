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

//  /**
//   * 创建一个IdFactory
//   * @param serverId 分片id
//   * @return IdFactory
//   */
//  static IdFactory defaultFactory(int serverId) {
//    return DefaultIdFactory.create(serverId);
//  }

  /**
   * 创建一个默认的IdFactory.
   *
   * @return IdFactory
   */
  static IdFactory defaultFactory() {
    return DefaultIdFactory.instance();
  }

}
