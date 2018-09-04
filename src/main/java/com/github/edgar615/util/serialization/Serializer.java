package com.github.edgar615.util.serialization;

import java.io.OutputStream;

/**
 * 将对象序列化到流.
 *
 * @author Edgar
 */
public interface Serializer<T> {

  /**
   * 将对象序列化为输出流
   *
   * @param value 对象
   * @param out 输出流
   */
  void serialize(T value, OutputStream out) throws SerDeException;
}