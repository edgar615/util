package com.github.edgar615.util.function;

/**
 * 两个类的转换类.
 *
 * @author Edgar
 * @create 2018-09-05 10:48
 **/
public interface Transformer<S, R> {

  /**
   * 将类型S的对象转化为类型R.
   *
   * @param s 源对象
   * @return 目标对象
   */
  R transform(S s);

}
