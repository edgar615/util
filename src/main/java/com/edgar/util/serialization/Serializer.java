package com.edgar.util.serialization;

import java.io.OutputStream;

/**
 * 将对象序列化到流.
 * @author Edgar
 */
public interface Serializer<T> {
  void serialize(T value, OutputStream out) throws SerDeException;
}