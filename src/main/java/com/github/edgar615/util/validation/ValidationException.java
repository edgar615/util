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

package com.github.edgar615.util.validation;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * Created by Edgar on 2016/4/13.
 *
 * @author Edgar  Date 2016/4/13
 */
public class ValidationException extends RuntimeException {

  private final Multimap<String, String> errorDetail = ArrayListMultimap.create();

  public ValidationException() {
  }

  public ValidationException(String message) {
    super(message);
  }

  public ValidationException(Multimap<String, String> errorDetail) {
    super();
    this.errorDetail.putAll(errorDetail);
  }

  public ValidationException(String message, Multimap<String, String> errorDetail) {
    super(message);
    this.errorDetail.putAll(errorDetail);
  }

  /**
   * 增加校验异常值.
   *
   * @param name 属性名，不允许为null
   * @param value 属性值，不允许为null
   * @return 返回对象本身，这样可以使用链式操作<code>putError("foo", "bar").putError("name", "Edgar)</code>
   */
  public ValidationException putError(String name, String value) {
    errorDetail.put(name, value);
    return this;
  }

  public ValidationException putErrors(Multimap<String, String> errors) {
    errorDetail.putAll(errors);
    return this;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder("ValidationException");
    stringBuilder.append(getMessage());
    return stringBuilder.toString();

  }

  @Override
  public String getMessage() {
    StringBuilder stringBuilder = new StringBuilder();
    if (super.getMessage() != null) {
      stringBuilder.append(": " + super.getMessage());
    }
    if (!errorDetail.isEmpty()) {
      stringBuilder.append("\nDetails: ");
      stringBuilder.append("\n--------------------------------------------------------------");
      errorDetail.asMap().forEach((key, value) -> {
        stringBuilder
            .append("\n")
            .append(key)
            .append(":")
            .append(value);

      });
      stringBuilder.append("\n--------------------------------------------------------------");
    }
    return stringBuilder.toString();
  }

  public Multimap<String, String> getErrorDetail() {
    return errorDetail;
  }

}
