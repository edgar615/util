package com.github.edgar615.util.validation;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import java.util.List;

/**
 * 校验是否是double.
 * <p>
 * 只校验String类型的值，其他类型默认为合法.
 *
 * @author Edgar  Date 2016/1/6
 */
class DoubleRule implements Rule {

  private static final String KEY = "double";

  private static final String TRUE = "true";

  private DoubleRule() {
  }

  static Rule create() {
    return new DoubleRule();
  }

  @Override
  public String message() {
    return "Double Required";
  }

  @Override
  public boolean isValid(Object property) {
    if (property == null) {
      return true;
    }
    if (property instanceof Double) {
      return true;
    }
    if (property instanceof Float) {
      return true;
    }
    if (property instanceof Integer) {
      return true;
    }
    if (property instanceof Long) {
      return true;
    }
    if (property instanceof Short) {
      return true;
    }
    if (property instanceof Byte) {
      return true;
    }
    if (property != null && (property instanceof String)) {
      String str = String.class.cast(property);
      try {
        Double.parseDouble(str);
        return true;
      } catch (NumberFormatException e) {
        return false;
      }
    }
    return false;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("DoubleRule")
        .toString();
  }

  static class Parser implements RuleParser {

    @Override
    public Rule parse(List<String> keyAndValue) {
      String key = keyAndValue.get(0);
      if (!KEY.equals(key)) {
        return null;
      }
      if (keyAndValue.size() == 1) {
        return new DoubleRule();
      }
      if (TRUE.equalsIgnoreCase(keyAndValue.get(1))) {
        return new DoubleRule();
      }
      return null;
    }

    @Override
    public List<String> toParsableString(Rule rule) {
      if (rule instanceof DoubleRule) {
        return Lists.newArrayList(KEY);
      }
      return Lists.newArrayList();
    }
  }
}
