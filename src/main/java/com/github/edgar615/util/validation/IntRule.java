package com.github.edgar615.util.validation;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验是否是int.
 * <p>
 * 只校验String类型的值，其他类型默认为合法.
 *
 * @author Edgar  Date 2016/1/6
 */
class IntRule implements Rule {

  /**
   * 正则表达式
   */
  private final String regex = "[0-9]*";

  private IntRule() {
  }

  static Rule create() {
    return new IntRule();
  }

  @Override
  public String message() {
    return "Int Required";
  }

  @Override
  public boolean isValid(Object property) {
    if (property == null) {
      return true;
    }
    if (property instanceof Integer) {
      return true;
    }
    if (property != null && (property instanceof String)) {
      String str = String.class.cast(property);
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher = pattern.matcher(str);
      if (matcher.matches()) {
        try {
          Integer.parseInt(str);
          return true;
        } catch (NumberFormatException e) {
          return false;
        }
      }
    }
    return false;
  }

  @Override
  public Map<String, Object> toMap() {
    return ImmutableMap.of("integer", true);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("IntRule")
            .toString();
  }
}
