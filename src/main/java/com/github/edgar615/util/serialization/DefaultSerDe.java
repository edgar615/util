/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.edgar615.util.serialization;


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
