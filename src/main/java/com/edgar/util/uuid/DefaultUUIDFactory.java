package com.edgar.util.uuid;

import java.util.UUID;

/**
 * UUID的默认实现，直接使用UUID.randomUUID()实现.
 *
 * @author Edgar  Date 2016/4/6
 */
public class DefaultUUIDFactory implements UUIDFactory {
  @Override
  public UUID generateRandomUuid() {
    return UUID.randomUUID();
  }
}
