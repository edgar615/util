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

/**
 * 查找用户信息
 */
public interface PrincipalFinder {

  /**
   * 根据身份标识查询用户信息
   * @param identifer 身份标识
   * @return 用户信息
   */
  Principal find(String identifer);
}
