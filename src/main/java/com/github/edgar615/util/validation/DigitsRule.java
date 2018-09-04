package com.github.edgar615.util.validation;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
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

  private String pattern() {
    if (length == 0) {
      return PATTERN + "*";
    }
    int len = length - 1;
    return PATTERN + "{" + len + "," + len +"}";
  }

  @Override
  public Map<String, Object> toMap() {
    return ImmutableMap.of("digits", length);
  }
}
