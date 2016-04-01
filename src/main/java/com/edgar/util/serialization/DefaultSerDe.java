package com.edgar.util.serialization;


import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * 使用ObjectInputStream和ObjectOutputStream序列化和反序列化.
 *
 * @author Edgar
 */
public class DefaultSerDe<T> implements SerDe {
  @Override
  public T deserialize(InputStream in) throws SerDeException {
    try {
      ObjectInputStream ois = new ObjectInputStream(in);
      return (T) ois.readObject();
    } catch (Exception e) {
      throw new SerDeException(e);
    }
  }

  @Override
  public void serialize(Object value, OutputStream out) throws SerDeException {
    try {
      ObjectOutputStream oos = new ObjectOutputStream(out);
      oos.writeObject(value);
    } catch (Exception e) {
      throw new SerDeException(e);
    }
  }
}
