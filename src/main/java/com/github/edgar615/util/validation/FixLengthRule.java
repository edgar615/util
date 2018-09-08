package com.github.edgar615.util.validation;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import java.util.List;

/**
 * 字符串固定长度的校验.
 * <p>
 * 只校验String和Number类型的值，其他类型默认为合法.
 *
 * @author Edgar  Date 2016/1/6
 */
class FixLengthRule implements Rule {

  private static final String KEY = "fixLength";

  /**
   * 最大长度.
   */
  private final int value;

  private FixLengthRule(int value) {
    this.value = value;
  }

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
  public String toString() {
    return MoreObjects.toStringHelper("FixLengthRule")
        .add("value", value)
        .toString();
  }

  int value() {
    return value;
  }

  static class Parser implements RuleParser {

    @Override
    public Rule parse(List<String> keyAndValue) {
      String key = keyAndValue.get(0);
      if (!KEY.equals(key)) {
        return null;
      }
      if (keyAndValue.size() > 1) {
        return new FixLengthRule(Integer.parseInt(keyAndValue.get(1)));
      }
      return null;
    }

    @Override
    public List<String> toParsableString(Rule rule) {
      if (rule instanceof FixLengthRule) {
        return Lists.newArrayList(KEY, ((FixLengthRule) rule).value + "");
      }
      return Lists.newArrayList();
    }
  }
}
