package com.github.edgar615.util.exception;

import org.junit.Test;

/**
 * Created by Administrator on 2015/11/18.
 */
public class SystemExceptionTest {

  @Test(expected = SystemException.class)
  public void testEx() {
    throwEx();
  }

  @Test(expected = SystemException.class)
  public void testExWithProperties() {
    throwExWithProperties();
  }

  @Test(expected = SystemException.class)
  public void testExWithThrowable() {
    throwExWithThrowable();
  }

  @Test(expected = SystemException.class)
  public void testExWithSystemEx() {
    throwExWithSystemEx();
  }

  @Test(expected = SystemException.class)
  public void testExWithSystemEx2() {
    throwExWithSystemEx2();
  }

  public void throwEx() {
    throw SystemException.create(DefaultErrorCode.NULL);
  }

  public void throwExWithProperties() {
    throw SystemException.create(DefaultErrorCode.NULL).set("foo", "bar");
  }

  public void throwExWithThrowable() {
    throw SystemException.wrap(DefaultErrorCode.NULL, new RuntimeException("no record")).set("foo",
                                                                                             "bar");
  }

  public void throwExWithSystemEx() {
    throw SystemException
            .wrap(DefaultErrorCode.NULL, SystemException.create(DefaultErrorCode.INVALID_TOKEN))
            .set("foo",
                 "bar");
  }

  public void throwExWithSystemEx2() {
    throw SystemException.wrap(DefaultErrorCode.NULL, SystemException.create(DefaultErrorCode.NULL))
            .set("foo",
                 "bar");
  }
}
