package com.edgar.util.exception;

/**
 * 异常的处理机制.
 *
 * @param <T> 异常.
 * @author Edgar
 */
public interface ExceptionHandler<T extends Exception> {
  <S extends Exception> T handle(S e);
}