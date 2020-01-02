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

/**
 * 不使用枚举的自定义错误码.
 *
 * @author Edgar
 */
public class CustomErrorCode implements ErrorCode {

  private final int number;
  private final String message;

  protected CustomErrorCode(int number, String message) {
    this.number = number;
    this.message = message;
  }

  public static CustomErrorCode create(int number, String message) {
    return new CustomErrorCode(number, message);
  }

  @Override
  public String toString() {
    return "CustomErrorCode{" +
        "number=" + number +
        ", message='" + message + '\'' +
        '}';
  }

  @Override
  public int getNumber() {
    return number;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
