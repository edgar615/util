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
 * 日期格式yyyy-MM-dd HH:mm:ss.
 * <p>
 * 只校验String类型的值，其他类型默认为非法.
 *
 * @author Edgar  Date 2016/1/6
 */
class DateTimeRule implements Rule {

  /**
   * 正则表达式
   */
  private static final Pattern PATTERN = Pattern
      .compile("\\d{4}-\\d{1,2}-\\d{1,2}T0[0-9]:[0-5][0-9]:[0-5][0-9]" +
          "|\\d{4}-\\d{1,2}-\\d{1,2} 1[0-9]:[0-5][0-9]:[0-5][0-9]" +
          "|\\d{4}-\\d{1,2}-\\d{1,2} 2[0-3]:[0-5][0-9]:[0-5][0-9]");

  private static final String KEY = "datetime";

  private static final String TRUE = "true";

  private DateTimeRule() {
  }

  static Rule create() {
    return new DateTimeRule();
  }

  @Override
  public String message() {
    return "Must match pattern: 'yyyy-MM-dd HH:mm:ss'";
  }

  @Override
  public boolean isValid(Object property) {
    if (property != null && (property instanceof String)) {
      String str = String.class.cast(property);
      Matcher matcher = PATTERN.matcher(str);
      return matcher.matches();
    }
    return true;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("DateTimeRule")
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
        return new DateTimeRule();
      }
      if (TRUE.equalsIgnoreCase(keyAndValue.get(1))) {
        return new DateTimeRule();
      }
      return null;
    }

    @Override
    public List<String> toParsableString(Rule rule) {
      if (rule instanceof DateTimeRule) {
        return Lists.newArrayList(KEY);
      }
      return Lists.newArrayList();
    }
  }
}
