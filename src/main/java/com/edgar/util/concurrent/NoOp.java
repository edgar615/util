package com.edgar.util.concurrent;

/**
 * 线程不会做任何操作.
 *
 * @author Edgar
 */
public class NoOp implements Runnable {
  public static final Runnable INSTANCE = new NoOp();

  private NoOp() {
  }

  @Override
  public void run() {
  }
}