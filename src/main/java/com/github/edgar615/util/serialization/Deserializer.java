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

/**
 * 将流中的数据反序列化为对象.
 *
 * @author Edgar
 */
public interface Deserializer<T> {

  /**
   * 反序列化方法
   *
   * @param in 输入流
   * @return 反序列化的对象
   */
  T deserialize(InputStream in) throws SerDeException;
}
