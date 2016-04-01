package com.edgar.util.id;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Created by edgar on 16-4-2.
 */
public class IdFactoryTest {

  @Test
  public void testIdServer0() {
    IdFactory idFactory = IdFactory.defaultFactory();
    int serverId = 0;
    long id = idFactory.generateId(serverId);

    Assert.assertEquals(serverId, idFactory.calServerId(id));
  }

  @Test
  public void testIdServer10() {
    IdFactory idFactory = IdFactory.defaultFactory();
    int serverId = 10;
    long id = idFactory.generateId(serverId);

    Assert.assertEquals(serverId, idFactory.calServerId(id));
  }

  @Test
  public void testIdServer1024() {
    IdFactory idFactory = IdFactory.defaultFactory();
    int serverId = 1024;
    long id = idFactory.generateId(serverId);

    Assert.assertEquals(0, idFactory.calServerId(id));
  }
}
