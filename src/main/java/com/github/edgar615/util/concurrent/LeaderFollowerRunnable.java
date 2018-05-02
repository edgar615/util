package com.github.edgar615.util.concurrent;

public abstract class LeaderFollowerRunnable implements Runnable {

  private final LeaderFollowerPool pool;

  protected LeaderFollowerRunnable(LeaderFollowerPool pool) {
    this.pool = pool;
  }

  public abstract void handle();

  @Override
  public void run() {
    while (!Thread.currentThread().isInterrupted()){
      try {
        pool.waitToBeLeader();
        //take 事件
        pool.promoteNewLeader();
        handle();
      } catch (InterruptedException e) {
        e.printStackTrace();
        Thread.interrupted();
      }
    }
  }
}
