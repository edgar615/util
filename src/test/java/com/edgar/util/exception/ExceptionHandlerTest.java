package com.edgar.util.exception;

import org.junit.Test;

/**
 * Created by Edgar on 2016/3/31.
 *
 * @author Edgar  Date 2016/3/31
 */
public class ExceptionHandlerTest {

  @Test
  public void testHandler() {
    ExceptionHandler<IllegalArgumentException> handler = new ExceptionHandler<IllegalArgumentException>() {
      @Override
      public <S extends Exception> IllegalArgumentException handle(S e) {
        return ExceptionUtils.wrap(e, IllegalArgumentException.class);
      }
    };

    System.out.println(handler.handle(new NullPointerException()));
  }
}
