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

package com.github.edgar615.util.auth;

import java.util.Map;

/**
 * 用户的接口
 * Created by Administrator on 2017/11/13.
 */
public interface Principal {

  /**
   * 返回用户ID
   * @return 用户ID
   */
  Long getUserId();

  /**
   * 返回用户名
   * @return 用户名
   */
  String getUsername();

  /**
   * 返回用户姓名
   * @return 用户姓名
   */
  String getName();

  Map<String, Object> ext();
}
