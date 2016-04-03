package com.edgar.util.id;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

/**
 * Created by edgar on 16-4-2.
 */
public class IdFactoryTest {

  @Test
  public void testFactorySingleton() throws InterruptedException {
    ExecutorService executorService = Executors.newCachedThreadPool();
    Set<IdFactory> idFactories = new CopyOnWriteArraySet<>();
    CountDownLatch countDownLatch = new CountDownLatch(1);
    CountDownLatch countDownLatch2 = new CountDownLatch(10);
    for (int i = 0; i < 100; i ++) {
      executorService.execute(new Runnable() {
        @Override
        public void run() {
          try {
            countDownLatch.await();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          idFactories.add(IdFactory.defaultFactory(1));
          countDownLatch2.countDown();
        }
      });
    }
    countDownLatch.countDown();
    countDownLatch2.await();
    System.out.println(idFactories);
    Assert.assertEquals(1, idFactories.size());
  }

  @Test
  public void testIdServer0() {
    IdFactory idFactory = IdFactory.defaultFactory();
    long id = idFactory.generateId();

    Assert.assertEquals(0, IdExtracter.class.cast(idFactory).fetchServerId(id));
  }

  @Test
  public void testIdServer10() {
    int serverId = 10;
    IdFactory idFactory = IdFactory.defaultFactory(serverId);
    long id = idFactory.generateId();

    Assert.assertEquals(serverId, IdExtracter.class.cast(idFactory).fetchServerId(id));
  }

  @Test
  public void testIdServer1024() {
    int serverId = 1024;
    IdFactory idFactory = IdFactory.defaultFactory(serverId);
    long id = idFactory.generateId();

    Assert.assertEquals(0, IdExtracter.class.cast(idFactory).fetchServerId(id));
  }

}
