package com.github.edgar615.util.reflect;

public class MyServiceImpl implements IMyService {

  public String say(String message) {
    System.out.println(message);
    return message;
  }

}
