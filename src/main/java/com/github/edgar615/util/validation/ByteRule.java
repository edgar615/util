package com.github.edgar615.util.validation;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import java.util.Map;

/**
 * 校验是否是byte.
 * <p>
 * 只校验String类型的值，其他类型默认为合法.
 *
 * @author Edgar  Date 2016/1/6
 */
class ByteRule implements Rule {

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
  public Map<String, Object> toMap() {
    return ImmutableMap.of("byte", true);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("ByteRule")
        .toString();
  }
}
