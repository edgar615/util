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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验是否是bool.
 * <p>
 * 只校验String类型的值，其他类型默认为合法.
 *
 * @author Edgar  Date 2016/1/6
 */
class BoolRule implements Rule {

  private static final String KEY = "bool";
  private static final String TRUE = "true";
  /**
   * 正则表达式
   */
  private final String regex = "(true)|(false)";

  private BoolRule() {
  }

  static Rule create() {
    return new BoolRule();
  }

  @Override
  public String message() {
    return "Bool Required";
  }

  @Override
  public boolean isValid(Object property) {
    if (property == null) {
      return true;
    }
    if (property instanceof Boolean) {
      return true;
    }
    if (property != null && (property instanceof String)) {
      String str = String.class.cast(property);
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher = pattern.matcher(str);
      if (matcher.matches()) {
        try {
          Boolean.parseBoolean(str);
          return true;
        } catch (Exception e) {
          return false;
        }
      }
    }
    return false;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("BoolRule")
        .toString();
  }

  static class Parser implements RuleParser {

    @Override
    public Rule parse(List<String> keyAndValue) {
      String key = keyAndValue.get(0);
      if (!KEY.equals(key)) {
        return null;
      }
      if (keyAndValue.size() == 1) {
        return new BoolRule();
      }
      if (TRUE.equalsIgnoreCase(keyAndValue.get(1))) {
        return new BoolRule();
      }
      return null;
    }

    @Override
    public List<String> toParsableString(Rule rule) {
      if (rule instanceof BoolRule) {
        return Lists.newArrayList(KEY);
      }
      return Lists.newArrayList();
    }
  }
}
