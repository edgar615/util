package com.github.edgar615.util.validation;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ISO 8601的日期格式.
 * <p>
 * 只校验String类型的值，其他类型默认为非法.
 *
 * @author Edgar  Date 2016/1/6
 */
class ISO8601DateRule implements Rule {

  /**
   * 正则表达式
   */
  private static final Pattern PATTERN = Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2}");

  private ISO8601DateRule() {
  }

  static Rule create() {
    return new ISO8601DateRule();
  }

  @Override
  public String message() {
    return "Must match pattern: yyyy-MM-dd";
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
    return ImmutableMap.of("iso8601Date", true);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("ISO8601DateRule")
        .add("iso8601Date", true)
        .toString();
  }
}
