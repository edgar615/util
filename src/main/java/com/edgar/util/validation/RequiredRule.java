package com.edgar.util.validation;

import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * 必填项校验.如果字符串为null或者为""，非法.
 * 注意："  "被认为是合法值.
 * 只校验String类型的值，其他类型默认为合法.
 *
 * @author Edgar  Date 2016/1/6
 */
class RequiredRule implements Rule {

  private RequiredRule() {
  }

  static Rule create() {
    return new RequiredRule();
  }

  @Override
  public String message() {
    return "Required";
  }

  public boolean isValid(Object property) {
    if (property == null) {
      return false;
    }
    if (property instanceof String) {
      return !Strings.isNullOrEmpty(String.class.cast(property));
    }
    return true;
  }

    @Override
    public Map<String, Object> toMap() {
        return ImmutableMap.of("required", true);
    }

    @Override
  public String toString() {
    return MoreObjects.toStringHelper("RequiredRule")
            .toString();
  }
}
