package com.github.edgar615.util.exception;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by Edgar on 2016/3/31.
 *
 * @author Edgar  Date 2016/3/31
 */
public class ExceptionUtilsTest {

  @Test
  public void wrapNullToIllegal() {
    NullPointerException exception = new NullPointerException("foo");
    Assertions.assertThat(ExceptionUtils.wrap(exception, IllegalArgumentException.class))
        .isInstanceOf(IllegalArgumentException.class);
    System.out.println(ExceptionUtils.wrap(exception, IllegalArgumentException.class));
  }

  @Test
  public void wrapNullToNull() {
    NullPointerException exception = new NullPointerException();
    Assertions.assertThat(ExceptionUtils.wrap(exception, NullPointerException.class))
        .isInstanceOf(NullPointerException.class);
    System.out.println(ExceptionUtils.wrap(exception, NullPointerException.class));
  }
}
