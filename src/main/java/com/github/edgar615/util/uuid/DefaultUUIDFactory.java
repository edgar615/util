package com.github.edgar615.util.uuid;

import java.util.UUID;

/**
 * UUID的默认实现，直接使用UUID.randomUUID()实现.
 *
 * @author Edgar  Date 2016/4/6
 */
public class DefaultUUIDFactory implements UUIDFactory {
  @Override
  public UUID uuid() {
    return UUID.randomUUID();
  }
}
