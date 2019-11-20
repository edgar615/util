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

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import java.util.List;

/**
 * 参数值等于某个值的校验.
 * <p>
 * 参数值必须等于某个值.
 *
 * @author Edgar  Date 2016/1/6
 */
class EqualsRule implements Rule {

  private static final String KEY = "equals";

  /**
   * 比较的值.
   */
  private final String value;

  private EqualsRule(String value) {
    this.value = value;
  }

  static Rule create(String value) {
    return new EqualsRule(value);
  }

  @Override
  public String message() {
    return "Equals:" + value;
  }

  @Override
  public boolean isValid(Object property) {
    if (property == null) {
      return true;
    }
    return value.equalsIgnoreCase(property.toString());
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("EqualsRule")
        .add("value", value)
        .toString();
  }

  String value() {
    return value;
  }

  static class Parser implements RuleParser {

    @Override
    public Rule parse(List<String> keyAndValue) {
      String key = keyAndValue.get(0);
      if (!KEY.equals(key)) {
        return null;
      }
      if (keyAndValue.size() > 1) {
        return new EqualsRule(keyAndValue.get(1));
      }
      return null;
    }

    @Override
    public List<String> toParsableString(Rule rule) {
      if (rule instanceof EqualsRule) {
        return Lists.newArrayList(KEY, ((EqualsRule) rule).value);
      }
      return Lists.newArrayList();
    }
  }
}
