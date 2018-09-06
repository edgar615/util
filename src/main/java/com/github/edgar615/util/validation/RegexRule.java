package com.github.edgar615.util.validation;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式校验.
 * <p>
 * 只校验String类型的值，其他类型默认为合法.
 *
 * @author Edgar  Date 2016/1/6
 */
class RegexRule implements Rule {

  private static final String KEY = "regex";

  /**
   * 正则表达式
   */
  private final String value;

  private RegexRule(String value) {
    this.value = value;
  }

  static Rule create(String value) {
    return new RegexRule(value);
  }

  @Override
  public String message() {
    return "Must match pattern:" + value;
  }

  @Override
  public boolean isValid(Object property) {
    if (property != null && (property instanceof String)) {
      String str = String.class.cast(property);
      Pattern pattern = Pattern.compile(value);
      Matcher matcher = pattern.matcher(str);
      return matcher.matches();
    }
    return true;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("RegexRule")
        .add("value", value)
        .toString();
  }

  String value() {
    return value;
  }

  static class Parse implements RuleParse {

    @Override
    public Rule parse(List<String> keyAndValue) {
      String key = keyAndValue.get(0);
      if (!KEY.equals(key)) {
        return null;
      }
      if (keyAndValue.size() > 1) {
        return new RegexRule(keyAndValue.get(1));
      }
      return null;
    }

    @Override
    public List<String> toParsableString(Rule rule) {
      if (rule instanceof RegexRule) {
        return Lists.newArrayList(KEY, ((RegexRule) rule).value + "");
      }
      return Lists.newArrayList();
    }
  }
}
