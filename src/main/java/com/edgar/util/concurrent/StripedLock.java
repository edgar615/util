package com.edgar.util.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Striped locks holder, contains array of {@link ReentrantLock}, on
 * which lock/unlock
 * operations are performed. Purpose of this is to decrease lock contention.
 * <p>Number of locks it can hold is bounded: it can be from set {2, 4, 8, 16, 32, 64}.</p>
 */
public class StripedLock {
  private final ReentrantLock[] locks;

  /**
   * Default ctor, creates 16 locks.
   */
  public StripedLock() {
    this(4);
  }

  /**
   * Creates array of locks, size of array may be any from set {2, 4, 8, 16, 32, 64}
   *
   * @param storagePower size of array will be equal to <code>Math.pow(2, storagePower)</code>
   */
  public StripedLock(int storagePower) {
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
   * Locks lock associated with given id.
   *
   * @param id value, from which lock is derived
   */
  public void lock(int id) {
    getLock(id).lock();
  }

  /**
   * Unlocks lock associated with given id.
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