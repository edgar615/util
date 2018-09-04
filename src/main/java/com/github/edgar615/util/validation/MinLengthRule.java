package com.github.edgar615.util.validation;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import java.util.Map;

/**
 * 字符串最小长度的校验.
 * <p>
 * 只校验String和Number类型的值，其他类型默认为合法.
 *
 * @author Edgar  Date 2016/1/6
 */
class MinLengthRule implements Rule {

  /**
   * 最小长度.
   */
  private final int value;

  private MinLengthRule(int value) {
    this.value = value;
  }

  static Rule create(int value) {
    return new MinLengthRule(value);
  }

  @Override
  public String message() {
    return "MinLength:" + value;
  }

  @Override
  public boolean isValid(Object property) {
    if (property != null && (property instanceof String)) {
      String str = String.class.cast(property);
      return str.length() >= value;
    }

    if (property != null && (property instanceof Number)) {
      String str = property.toString();
      return str.length() >= value;
    }
    return true;
  }

  @Override
  public Map<String, Object> toMap() {
    return ImmutableMap.of("minLength", value);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("MinLengthRule")
        .add("value", value)
        .toString();
  }
}
