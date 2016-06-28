package com.edgar.util.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 条纹锁，与Guava的Striped类似，内部维护了一组{@link ReentrantLock}用来做并发控制
 * <p>
 * 锁的数量可以是{2, 4, 8, 16, 32, 64}中的任何一个
 */
public class StripedLock {

  /**
   * 委托的锁的数组
   */
  private final ReentrantLock[] locks;

  /**
   * 创建条纹锁，锁的数量可以是{2, 4, 8, 16, 32, 64}中的任何一个。
   *
   * @param storagePower 锁的数量= <code>Math.pow(2, storagePower)</code>，因此该值必须在1-6之间
   */
  private StripedLock(int storagePower) {
    if (!(storagePower >= 1 && storagePower <= 6)) {
      throw new IllegalArgumentException("storage power must be in [1..6]");
    }

    int lockSize = (int) Math.pow(2, storagePower);
    locks = new ReentrantLock[lockSize];
    for (int i = 0; i < locks.length; i++) {
      locks[i] = new ReentrantLock();
    }
  }

  /**
   * 默认的条纹锁，内部有16个 ReentrantLock
   *
   * @return StripedLock
   */
  public static StripedLock create() {
    return new StripedLock(4);
  }

  /**
   * 创建条纹锁，锁的数量可以是{2, 4, 8, 16, 32, 64}中的任何一个。
   *
   * @param storagePower 锁的数量= <code>Math.pow(2, storagePower)</code>，因此该值必须在1-6之间
   * @return StripedLock
   */
  public static StripedLock create(int storagePower) {
    return new StripedLock(storagePower);
  }

  /**
   * 根据id获取一个锁
   *
   * @param id value, from which lock is derived
   */
  public void lock(int id) {
    getLock(id).lock();
  }

  /**
   * 根据id释放一个锁.
   *
   * @param id value, from which lock is derived
   */
  public void unlock(int id) {
    getLock(id).unlock();
  }

  /**
   * Map function between integer and lock from locks array.
   *
   * @param id argument
   * @return lock which is result of function
   */
  private ReentrantLock getLock(int id) {
    return locks[id & (locks.length - 1)];
  }
}