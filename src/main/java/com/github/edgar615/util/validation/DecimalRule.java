package com.github.edgar615.util.validation;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 只能是数字.
 *
 * @author Edgar
 * @create 2018-09-04 15:13
 **/
class DecimalRule implements Rule {

  /**
   * 正则表达式
   */
  private final String regex;

  private final int point;

  private DecimalRule(int point) {
    Preconditions.checkArgument(point > 0);
    this.point = point;
    String pointPattern = "\\d{1," + point + "}";
    this.regex = "[1-9]\\d*\\." + pointPattern + "|0\\." + pointPattern;
  }

  static Rule create(int point) {
    return new DecimalRule(point);
  }

  @Override
  public String message() {
    return "must be numeric and contain " + point + " decimal points";
  }

  @Override
  public boolean isValid(Object property) {
    if (property != null) {
      String str = property.toString();
      Pattern pattern = Pattern.compile(this.regex);
      Matcher matcher = pattern.matcher(str);
      return matcher.matches();
    }
    return true;
  }

  @Override
  public Map<String, Object> toMap() {
    return ImmutableMap.of("decimal", point);
  }
}
