package com.github.edgar615.util.validation;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 只能包含字母字符.
 *
 * @author Edgar
 * @create 2018-09-04 15:13
 **/
class AlphaRule implements Rule {

  /**
   * 正则表达式
   */
  private static final Pattern PATTERN = Pattern.compile("[a-zA-Z]*");

  private AlphaRule() {
  }

  static Rule create() {
    return new AlphaRule();
  }

  @Override
  public String message() {
    return "only contain alphabetic characters";
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
  public Map<String, Object> toMap() {
    return ImmutableMap.of("alpha", true);
  }
}
