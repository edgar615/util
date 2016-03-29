package com.edgar.util.base;

import com.edgar.util.base.Consumer2;
import org.junit.Test;

/**
 * Created by Edgar on 2016/3/29.
 *
 * @author Edgar  Date 2016/3/29
 */
public class Consumer2Test {

  @Test
  public void testConsumer2() {
    Consumer2<String, String> consumer2 = new Consumer2<String, String>() {
      @Override
      public void accept(String s, String s2) {
        System.out.println(s + s2);
      }
    };
    consumer2.accept("hello ", "world!");
  }
}
