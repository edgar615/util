package com.github.edgar615.util.id;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by edgar on 16-4-2.
 */
public class DefaultIdFactoryTest {

  @Test
  public void testFactorySingleton() throws InterruptedException {
    System.out.println(System.currentTimeMillis() << 22);
    System.out.println(System.currentTimeMillis() << 23);
    System.out.println(-1 ^ (-1 << 12));
    System.out.println(23 & 4194303);
    ExecutorService executorService = Executors.newCachedThreadPool();
    Set<IdFactory> idFactories = new CopyOnWriteArraySet<>();
    int count = 100;
    CountDownLatch countDownLatch = new CountDownLatch(1);
    CountDownLatch countDownLatch2 = new CountDownLatch(count);
    for (int i = 0; i < count; i++) {
      executorService.execute(new Runnable() {
        @Override
        public void run() {
          try {
            countDownLatch.await();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          idFactories.add(IdFactory.defaultFactory());
          countDownLatch2.countDown();
        }
      });
    }
    countDownLatch.countDown();
    countDownLatch2.await();
    Assert.assertEquals(1, idFactories.size());
  }

  @Test
  public void fetchTime() {
    IdFactory<Long> idFactory = IdFactory.defaultFactory();
    long id = idFactory.nextId();
    TimeExtracter timeExtracter = (TimeExtracter) idFactory;
    System.out.println(timeExtracter.fetchTime(id));
    SeqExtracter seqExtracter = (SeqExtracter) idFactory;
    System.out.println(seqExtracter.fetchSeq(id));
  }

  @Test
  public void testUniqueId() throws InterruptedException {
    IdFactory<Long> idFactory = IdFactory.defaultFactory();
    ExecutorService executorService = Executors.newCachedThreadPool();
    int threadNum = 100;
    int loopNum = 500;
    Set<Long> ids = new CopyOnWriteArraySet<>();
    for (int j = 0; j < loopNum; j++) {
      CountDownLatch countDownLatch = new CountDownLatch(1);
      CountDownLatch countDownLatch2 = new CountDownLatch(threadNum);
      for (int i = 0; i < threadNum; i++) {
        executorService.execute(new Runnable() {
          @Override
          public void run() {
            try {
              countDownLatch.await();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            ids.add(idFactory.nextId());
            countDownLatch2.countDown();
          }
        });
      }
      countDownLatch.countDown();
      countDownLatch2.await();
    }

    Assert.assertEquals(threadNum * loopNum, ids.size());

  }

}
