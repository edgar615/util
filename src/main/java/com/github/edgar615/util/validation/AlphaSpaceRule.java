package com.github.edgar615.util.validation;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 只能包含字母、k.
 *
 * @author Edgar
 * @create 2018-09-04 15:13
 **/
class AlphaSpaceRule implements Rule {

  /**
   * 正则表达式
   */
  private static final Pattern PATTERN = Pattern.compile("[A-Za-z\\s]*");

  private AlphaSpaceRule() {
  }

  static Rule create() {
    return new AlphaSpaceRule();
  }

  @Override
  public String message() {
    return "only contain alphabetic characters or spaces";
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
    return ImmutableMap.of("alphaSpace", true);
  }
}
