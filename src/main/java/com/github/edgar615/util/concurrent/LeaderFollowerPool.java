package com.github.edgar615.util.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class LeaderFollowerPool {

  private final ExecutorService workerExecutor;

  private static final Semaphore SEMAPHORE = new Semaphore(1);

  public LeaderFollowerPool(int poolSzie) {
    this.workerExecutor = Executors.newFixedThreadPool(poolSzie,
            NamedThreadFactory.create("leader-follower-pool"));
    for (int i = 0; i < poolSzie; i ++) {
      workerExecutor.execute(new LeaderFollowerRunnable(this) {
        @Override
        public void handle() {

        }
      });
    }
  }

  public void waitToBeLeader() throws InterruptedException {
    SEMAPHORE.acquire();
  }

  public synchronized void promoteNewLeader() {
    SEMAPHORE.release();
  }
}
