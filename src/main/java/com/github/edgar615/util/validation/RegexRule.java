package com.github.edgar615.util.validation;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式校验.
 * <p>
 * 只校验String类型的值，其他类型默认为合法.
 *
 * @author Edgar  Date 2016/1/6
 */
class RegexRule implements Rule {

  /**
   * 正则表达式
   */
  private final String value;

  private RegexRule(String value) {
    this.value = value;
  }

  static Rule create(String value) {
    return new RegexRule(value);
  }

  @Override
  public String message() {
    return "Must match pattern:" + value;
  }

  @Override
  public boolean isValid(Object property) {
    if (property != null && (property instanceof String)) {
      String str = String.class.cast(property);
      Pattern pattern = Pattern.compile(value);
      Matcher matcher = pattern.matcher(str);
      return matcher.matches();
    }
    return true;
  }

  @Override
  public Map<String, Object> toMap() {
    return ImmutableMap.of("regex", value);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("RegexRule")
        .add("value", value)
        .toString();
  }
}
