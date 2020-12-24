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

package com.github.edgar615.util.uuid;


import com.github.edgar615.util.concurrent.ConcurrentUUIDFactory;
import java.util.UUID;

/**
 * 生成UUID的接口.
 *
 * @author Edgar
 */
@FunctionalInterface
public interface UUIDFactory {

  /**
   * Generates a new version 4 UUID.
   *
   * @return the newly generated UUID
   */
  UUID uuid();

  static UUIDFactory defaultUUIDFactory() {
    return new DefaultUUIDFactory();
  }


  /**
   * 创建ConcurrentUUIDFactory对象.
   *
   * @return ConcurrentUUIDFactory
   */
  static UUIDFactory concurrentUUIDFactory() {
    return ConcurrentUUIDFactory.create();
  }
}
