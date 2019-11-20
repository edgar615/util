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
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 只能包含数字.
 *
 * @author Edgar
 * @create 2018-09-04 15:13
 **/
class DigitsRule implements Rule {

  /**
   * 正则表达式
   */
  private static final String PATTERN = "[1-9][0-9]";

  private static final String KEY = "digits";

  private final int length;

  private DigitsRule() {
    this.length = 0;
  }

  private DigitsRule(int length) {
    Preconditions.checkArgument(length > 0);
    this.length = length;
  }

  static Rule create() {
    return new DigitsRule();
  }

  static Rule create(int length) {
    return new DigitsRule(length);
  }

  @Override
  public String message() {
    if (length == 0) {
      return "must be numeric";
    } else {
      return "must be numeric and have the " + length + " digits";
    }
  }

  @Override
  public boolean isValid(Object property) {
    if (property != null) {
      String str = property.toString();
      Pattern pattern = Pattern.compile(pattern());
      Matcher matcher = pattern.matcher(str);
      return matcher.matches();
    }
    return true;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("DigitsRule")
        .add("length", length)
        .toString();
  }

  int length() {
    return length;
  }

  private String pattern() {
    if (length == 0) {
      return PATTERN + "*";
    }
    int len = length - 1;
    return PATTERN + "{" + len + "," + len + "}";
  }

  static class Parser implements RuleParser {

    @Override
    public Rule parse(List<String> keyAndValue) {
      String key = keyAndValue.get(0);
      if (!KEY.equals(key)) {
        return null;
      }
      if (keyAndValue.size() == 1) {
        return new DigitsRule();
      }
      if (keyAndValue.size() > 1) {
        return new DigitsRule(Integer.parseInt(keyAndValue.get(1)));
      }
      return null;
    }

    @Override
    public List<String> toParsableString(Rule rule) {
      if (rule instanceof DigitsRule) {
        int length = ((DigitsRule) rule).length;
        if (length == 0) {
          return Lists.newArrayList(KEY);
        }
        return Lists.newArrayList(KEY, ((DigitsRule) rule).length + "");
      }
      return Lists.newArrayList();
    }
  }
}
