package com.edgar.util.statemachine;

/**
 * Created by edgar on 16-4-1.
 */
@Deprecated
public class Transition<T> {

  private T from;

  private T to;

  public T getFrom() {
    return from;
  }

  public void setFrom(T from) {
    this.from = from;
  }

  public T getTo() {
    return to;
  }

  public void setTo(T to) {
    this.to = to;
  }
}
