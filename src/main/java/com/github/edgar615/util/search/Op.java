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

package com.github.edgar615.util.search;

public enum Op {

  /**
   * is null
   */
  IS_NULL,
  /**
   * is not null
   */
  IS_NOT_NULL,
  /**
   * =
   */
  EQ,
  /**
   * <>
   */
  NE,
  /**
   * >
   */
  GT,
  /**
   * >=
   */
  GE,
  /**
   * 小于
   */
  LT,
  /**
   * <=
   */
  LE,

  /**
   * start with
   */
  SW,

  /**
   * end with
   */
  EW,

  /**
   * contain
   */
  CN,

  /**
   * in
   */
  IN,
  /**
   * not in
   */
  NOT_IN,
  /**
   * between
   */
  BETWEEN,

  /**
   * REGEXP
   */
  REGEXP;

}
