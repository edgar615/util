package com.edgar.util.validation;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * 字符串固定长度的校验.
 * <p>
 * 只校验String和Number类型的值，其他类型默认为合法.
 *
 * @author Edgar  Date 2016/1/6
 */
class FixLengthRule implements Rule {

  /**
   * 最大长度.
   */
  private final int value;

  private FixLengthRule(int value) {this.value = value;}

  static Rule create(int value) {
    return new FixLengthRule(value);
  }

  @Override
  public String message() {
    return "FixLength:" + value;
  }

  @Override
  public boolean isValid(Object property) {
    if (property != null && (property instanceof String)) {
      String str = String.class.cast(property);
      return str.length() == value;
    }
    if (property != null && (property instanceof Number)) {
      String str = property.toString();
      return str.length() == value;
    }
    return true;
  }

  @Override
  public Map<String, Object> toMap() {
    return ImmutableMap.of("fixLength", value);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("FixLengthRule")
            .add("value", value)
            .toString();
  }
}