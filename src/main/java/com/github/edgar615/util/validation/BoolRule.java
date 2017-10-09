package com.github.edgar615.util.validation;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;

import java.util.Map;
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
  public Map<String, Object> toMap() {
    return ImmutableMap.of("bool", true);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("BoolRule")
            .toString();
  }
}
