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

package com.github.edgar615.util.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * 异常编码.
 *
 * @author Edgar
 * @version 1.0
 */
public interface ErrorCode {

  default Map<String, Object> asMap() {
    Map<String, Object> map = new HashMap<>();
    map.put("message", getMessage());
    map.put("code", getNumber());
    return map;
  }

  /**
   * 返回异常的编码.
   *
   * @return 编码值
   */
  int getNumber();

  /**
   * 返回异常的描述.
   *
   * @return 描述
   */
  String getMessage();
}
