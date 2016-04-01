package com.edgar.util.serialization;

import java.io.InputStream;

/**
 * 将流中的数据反序列化为对象.
 *
 * @param <T>
 * @author Edgar
 */
public interface Deserializer<T> {
  T deserialize(InputStream in) throws SerDeException;
}