package com.github.edgar615.util.validation;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 只能包含字母、数字.
 *
 * @author Edgar
 * @create 2018-09-04 15:13
 **/
class AlphaNumberRule implements Rule {

  /**
   * 正则表达式
   */
  private static final Pattern PATTERN = Pattern.compile("[0-9A-Za-z]*");

  private AlphaNumberRule() {
  }

  static Rule create() {
    return new AlphaNumberRule();
  }

  @Override
  public String message() {
    return "only contain alphabetic characters or numbers";
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
    return ImmutableMap.of("alphaNumber", true);
  }
}
