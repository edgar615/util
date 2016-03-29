package com.edgar.util.base;

@FunctionalInterface
public interface Consumer2<T1, T2> {

  void accept(T1 t1, T2 t2);

}