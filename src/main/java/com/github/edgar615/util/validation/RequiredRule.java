package com.github.edgar615.util.validation;

import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import java.util.List;

/**
 * 必填项校验.如果字符串为null或者为""，非法. 注意："  "被认为是合法值. 只校验String类型的值，其他类型默认为合法.
 *
 * @author Edgar  Date 2016/1/6
 */
class RequiredRule implements Rule {

  private static final String KEY = "required";

  private static final String TRUE = "true";

  private RequiredRule() {
  }

  static Rule create() {
    return new RequiredRule();
  }

  @Override
  public String message() {
    return "Required";
  }

  @Override
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
  public String toString() {
    return MoreObjects.toStringHelper("RequiredRule")
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
        return new RequiredRule();
      }
      if (TRUE.equalsIgnoreCase(keyAndValue.get(1))) {
        return new RequiredRule();
      }
      return null;
    }

    @Override
    public List<String> toParsableString(Rule rule) {
      if (rule instanceof RequiredRule) {
        return Lists.newArrayList(KEY);
      }
      return Lists.newArrayList();
    }
  }
}
