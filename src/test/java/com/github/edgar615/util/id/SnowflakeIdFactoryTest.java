package com.github.edgar615.util.id;

import com.google.common.base.Joiner;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by edgar on 16-4-2.
 */
public class SnowflakeIdFactoryTest {

  @Test
  public void testFactorySingleton() throws InterruptedException {
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
          idFactories.add(IdFactory.simpleSnowflake(1));
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
    IdFactory<Long> idFactory = IdFactory.simpleSnowflake(100);
    Long id = idFactory.nextId();
    System.out.println(Long.toBinaryString(id).length());
    TimeExtracter timeExtracter = (TimeExtracter) idFactory;
    System.out.println(timeExtracter.fetchTime(id));
    ShardingExtracter shardingExtracter = (ShardingExtracter) idFactory;
    System.out.println(shardingExtracter.fetchSharding(id));
    SeqExtracter seqExtracter = (SeqExtracter) idFactory;
    System.out.println(seqExtracter.fetchSeq(id));
  }

  @Test
  public void testUniqueId() throws InterruptedException {
    ExecutorService executorService = Executors.newCachedThreadPool();
    int threadNum = 100;
    int loopNum = 500;
    Set<Long> ids = new CopyOnWriteArraySet<>();
    for (int j = 0; j < loopNum; j++) {
      CountDownLatch countDownLatch = new CountDownLatch(1);
      CountDownLatch countDownLatch2 = new CountDownLatch(threadNum);
      for (int i = 0; i < threadNum; i++) {
        IdFactory<Long> idFactory = IdFactory.simpleSnowflake(i);
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
    System.out.println(Joiner.on("\n").join(ids));

  }

}