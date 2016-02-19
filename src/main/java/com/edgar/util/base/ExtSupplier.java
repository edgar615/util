package com.edgar.util.base;

public interface ExtSupplier<T, E extends Throwable> {
  T get() throws E;
}