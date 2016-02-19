package com.edgar.util.concurrent;

public interface ExtCallable<V, E extends Throwable> {
  V call() throws E;
}