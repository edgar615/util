package com.github.edgar615.util.validation;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import java.util.List;

/**
 * 校验是否是short.
 * <p>
 * 只校验String类型的值，其他类型默认为合法.
 *
 * @author Edgar  Date 2016/1/6
 */
class ShortRule implements Rule {

  private static final String KEY = "short";

  private static final String TRUE = "true";


  private ShortRule() {
  }

  static Rule create() {
    return new ShortRule();
  }

  @Override
  public String message() {
    return "Short Required";
  }

  @Override
  public boolean isValid(Object property) {
    if (property == null) {
      return true;
    }
    if (property instanceof Short) {
      return true;
    }
    if (property instanceof Byte) {
      return true;
    }
    if (property instanceof Integer) {
      Integer intVal = (Integer) property;
      return intVal >= Short.MIN_VALUE && intVal <= Short.MAX_VALUE;
    }
    if (property instanceof Long) {
      Long longVal = (Long) property;
      return longVal >= Short.MIN_VALUE && longVal <= Short.MAX_VALUE;
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
    return MoreObjects.toStringHelper("ShortRule")
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
        return new ShortRule();
      }
      if (TRUE.equalsIgnoreCase(keyAndValue.get(1))) {
        return new ShortRule();
      }
      return null;
    }

    @Override
    public List<String> toParsableString(Rule rule) {
      if (rule instanceof ShortRule) {
        return Lists.newArrayList(KEY);
      }
      return Lists.newArrayList();
    }
  }
}
