package com.github.edgar615.util.validation;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import java.util.List;

/**
 * 校验是否是byte.
 * <p>
 * 只校验String类型的值，其他类型默认为合法.
 *
 * @author Edgar  Date 2016/1/6
 */
class ByteRule implements Rule {

  private static final String KEY = "byte";

  private static final String TRUE = "true";

  private ByteRule() {
  }

  static Rule create() {
    return new ByteRule();
  }

  @Override
  public String message() {
    return "Byte Required";
  }

  @Override
  public boolean isValid(Object property) {
    if (property == null) {
      return true;
    }
    if (property instanceof Byte) {
      return true;
    }
    if (property instanceof Short) {
      Short shortVal = (Short) property;
      return shortVal >= Byte.MIN_VALUE && shortVal <= Byte.MAX_VALUE;
    }
    if (property instanceof Integer) {
      Integer intVal = (Integer) property;
      return intVal >= Byte.MIN_VALUE && intVal <= Byte.MAX_VALUE;
    }
    if (property instanceof Long) {
      Long longVal = (Long) property;
      return longVal >= Byte.MIN_VALUE && longVal <= Byte.MAX_VALUE;
    }
    if (property != null && (property instanceof String)) {
      String str = String.class.cast(property);
      try {
        Short.parseShort(str);
        return true;
      } catch (NumberFormatException e) {
        return false;
      }
    }
    return false;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("ByteRule")
        .toString();
  }

  static class Parse implements RuleParse {

    @Override
    public Rule parse(List<String> keyAndValue) {
      String key = keyAndValue.get(0);
      if (!KEY.equals(key)) {
        return null;
      }
      if (keyAndValue.size() == 1) {
        return new ByteRule();
      }
      if (TRUE.equalsIgnoreCase(keyAndValue.get(1))) {
        return new ByteRule();
      }
      return null;
    }

    @Override
    public List<String> toParsableString(Rule rule) {
      if (rule instanceof ByteRule) {
        return Lists.newArrayList(KEY);
      }
      return Lists.newArrayList();
    }
  }
}
