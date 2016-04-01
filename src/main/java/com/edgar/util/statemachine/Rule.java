package com.edgar.util.statemachine;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;

import java.util.Set;
import java.util.function.Consumer;

/**
 * Created by edgar on 16-4-1.
 */
@Deprecated
public class Rule<T> {
  private final T from;
  private final Set<T> to;
  private final Consumer<Transition<T>> callback;

  public Rule(T from) {
    this.from = from;
    this.to = ImmutableSet.of();
    this.callback = new Consumer<Transition<T>>() {
      @Override
      public void accept(Transition<T> tTransition) {

      }
    };
  }

  public Rule(T from, Set<T> to) {
    this.from = from;
    this.to = to;
    this.callback = new Consumer<Transition<T>>() {
      @Override
      public void accept(Transition<T> tTransition) {

      }
    };
  }

  public Rule(T from, Set<T> to, Consumer<Transition<T>> callback) {
    this.from = from;
    this.to = to;
    this.callback = callback;
  }

  /**
   * Rule的辅助类.
   * @param <T>
   */
  public class AllowedTransition<T> {
    private final Rule<T> rule;

    private AllowedTransition(Rule<T> rule) {
      this.rule = rule;
    }

    /**
     * 当前状态与唯一一个允许变迁的状态之间的关联.
     *
     * @param state 允许变迁的状态.
     * @return 新的Rule.
     */
    public Rule<T> to(T state) {
      return new Rule<T>(rule.from, ImmutableSet.<T>of(state), rule.callback);
    }

    /**
     * 当前状态与一组可以变迁的状态的关联.
     *
     * @param state            允许变迁的状态.
     * @param additionalStates 其他允许变迁的状态.
     * @return 新的Rule.
     */
    public Rule<T> to(T state, T... additionalStates) {
      return new Rule<T>(rule.from, ImmutableSet.copyOf(Lists.asList(state, additionalStates)));
    }

      /**
       * 不允许变迁到其他状态.
       *
       * @return 当前Rule.
       */
      public Rule<T> noTransitions() {
        return rule;
      }
  }
}
