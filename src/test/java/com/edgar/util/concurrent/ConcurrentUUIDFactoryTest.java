package com.edgar.util.concurrent;

import com.edgar.util.uuid.UUIDFactory;
import org.junit.Test;

/**
 * Created by Edgar on 2016/2/19.
 *
 * @author Edgar  Date 2016/2/19
 */
public class ConcurrentUUIDFactoryTest {

  @Test
  public void testUUID() {
      System.out.println(UUIDFactory.concurrentUUIDFactory().uuid());
  }
}
