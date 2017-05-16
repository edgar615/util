package com.edgar.util.search;

public enum Op {

  /**
   * is null
   */
  IS_NULL,
  /**
   * is not null
   */
  IS_NOT_NULL,
  /**
   * =
   */
  EQ,
  /**
   * <>
   */
  NE,
  /**
   * >
   */
  GT,
  /**
   * >=
   */
  GOE,
  /**
   * 小于
   */
  LT,
  /**
   * <=
   */
  LOE,

  /**
   * start with
   */
  SW,

  /**
   * end with
   */
  EW,

  /**
   * contain
   */
  CN,

  /**
   * in
   */
  IN,
  /**
   * not in
   */
  NOT_IN,
  /**
   * between
   */
  BETWEEN;

}