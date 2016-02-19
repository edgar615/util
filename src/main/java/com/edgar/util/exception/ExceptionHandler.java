package com.edgar.util.exception;

public interface ExceptionHandler<T extends Exception> {
  public <S extends Exception> T handle(S e);
}