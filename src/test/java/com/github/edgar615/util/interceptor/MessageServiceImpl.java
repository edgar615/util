package com.github.edgar615.util.interceptor;

/**
 * @author Edgar
 * @create 2018-09-18 13:36
 **/
public class MessageServiceImpl implements MessageService {

  @Override
  public void say() {
    System.out.println("say something");
  }

  @Override
  public String say(String message) {
    System.out.println(message);
    return message;
  }
}
