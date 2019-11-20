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
 * 禁止属性校验.如果该属性不为null，这表示非法.
 *
 * @author Edgar  Date 2016/4/18
 */
public class ProhibitedRule implements Rule {

  private static final String KEY = "prohibited";

  private static final String TRUE = "true";

  private ProhibitedRule() {
  }

  static Rule create() {
    return new ProhibitedRule();
  }

  @Override
  public String message() {
    return "Prohibited";
  }

  @Override
  public boolean isValid(Object property) {
    if (property == null) {
      return true;
    }
    return false;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("ProhibitedRule")
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
        return new ProhibitedRule();
      }
      if (TRUE.equalsIgnoreCase(keyAndValue.get(1))) {
        return new ProhibitedRule();
      }
      return null;
    }

    @Override
    public List<String> toParsableString(Rule rule) {
      if (rule instanceof ProhibitedRule) {
        return Lists.newArrayList(KEY);
      }
      return Lists.newArrayList();
    }
  }
}
