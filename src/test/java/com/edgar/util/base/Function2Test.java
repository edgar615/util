package com.edgar.util.base;

import com.edgar.util.base.Function2;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Function;

/**
 * Created by Edgar on 2016/3/29.
 *
 * @author Edgar  Date 2016/3/29
 */
public class Function2Test {

  @Test
  public void testCountLength() {
    Function2<String, String, Integer> function2 = new Function2<String, String, Integer>() {
      @Override
      public Integer apply(String s, String s2) {
        return s.length() + s2.length();
      }
    };
    Assert.assertEquals(12, function2.apply("hello ", "world!"), 0);
  }

  @Test
  public void testAndThen() {
    Function2<String, String, Integer> function2 = new Function2<String, String, Integer>() {
      @Override
      public Integer apply(String s, String s2) {
        return s.length() + s2.length();
      }
    };
    Function<Integer, String> andThenFun = new Function<Integer, String>() {
      @Override
      public String apply(Integer input) {
        return "count:" + input;
      }
    };
    Assert.assertEquals("count:12", function2.andThen(andThenFun).apply("hello ", "world!"));
  }
}
