package com.edgar.util.statemachine;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;

import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by edgar on 16-4-1.
 */
@Deprecated
public class StateMachine<T> {

  //  private final Closure<Transition<T>> transitionCallback;
  private final boolean throwOnBadTransition;
  private final Lock readLock;
  private final Lock writeLock;
  private String name;
  private Multimap<T, T> stateTransitions;
  private volatile T currentState;

  private StateMachine(String name,
                       T initialState,
                       Multimap<T, T> stateTransitions,
                       boolean throwOnBadTransition) {
    this.name = name;
    this.currentState = initialState;
    this.stateTransitions = stateTransitions;
//    this.transitionCallback = transitionCallback;
    this.throwOnBadTransition = throwOnBadTransition;

    ReadWriteLock stateLock = new ReentrantReadWriteLock(true);
    readLock = stateLock.readLock();
    writeLock = stateLock.writeLock();
  }

  public void checkState(T allowedState) {
    checkState(ImmutableSet.of(allowedState));
  }

  /**
   * 检查当前状态是否属于{@code allowedStates}，如果不是抛出异常.
   *
   * @param allowedStates 允许的状态集合
   * @throws IllegalStateException 如果当前状态不是符合要求的状态
   */
  public void checkState(Set<T> allowedStates) {
    Preconditions.checkNotNull(allowedStates);
    Preconditions.checkArgument(!allowedStates.isEmpty(), "At least one possible state must be provided.");

    readLock.lock();
    try {
      if (!allowedStates.contains(currentState)) {
        throw new IllegalStateException(
                String.format("In state %s, expected to be in %s.", currentState, allowedStates));
      }
    } finally {
      readLock.unlock();
    }
  }

  public void doInState(T expectedState) {

    Preconditions.checkNotNull(expectedState);
//    checkNotNull(work);

    readLock.lock();
    try {
      checkState(expectedState);
//      return work.get();
    } finally {
      readLock.unlock();
    }
  }


  public boolean transition(T nextState) throws IllegalStateTransitionException {
    boolean transitionAllowed = false;

    T currentCopy = currentState;

    writeLock.lock();
    try {
      if (stateTransitions.containsEntry(currentState, nextState)) {
        currentState = nextState;
        transitionAllowed = true;
      } else if (throwOnBadTransition) {
        throw new IllegalStateTransitionException(
                String.format("State transition from %s to %s is not allowed.", currentState,
                        nextState));
      }
    } finally {
      writeLock.unlock();
    }

//    transitionCallback.execute(new Transition<T>(currentCopy, nextState, transitionAllowed));
    return transitionAllowed;
  }

  public static class IllegalStateTransitionException extends IllegalStateException {
    public IllegalStateTransitionException(String msg) {
      super(msg);
    }
  }
}
