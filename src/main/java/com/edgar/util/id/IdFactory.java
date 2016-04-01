package com.edgar.util.id;

/**
 * 主键生成的工厂类.
 * Created by edgar on 16-4-2.
 *
 * @author Edgar
 */
public interface IdFactory {

  static IdFactory defaultFactory() {
    return DefaultIdFactory.instance();
  }

  /**
   * 主键生成方法
   *
   * @param serverId 服务器id
   * @return 主键
   */
  long generateId(int serverId);

  /**
   * 从主键中提取服务器ID
   *
   * @param id 主键
   * @return 服务器id
   */
  long calServerId(long id);
}
