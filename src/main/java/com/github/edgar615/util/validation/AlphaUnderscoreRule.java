package com.github.edgar615.util.validation;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 只能包含字母、数字、下划线.
 *
 * @author Edgar
 * @create 2018-09-04 15:13
 **/
class AlphaUnderscoreRule implements Rule {

  /**
   * 正则表达式
   */
  private static final Pattern PATTERN = Pattern.compile("[0-9A-Za-z_]*");

  private static final String KEY = "alphaUnderscore";

  private static final String TRUE = "true";

  private AlphaUnderscoreRule() {
  }

  static Rule create() {
    return new AlphaUnderscoreRule();
  }

  @Override
  public String message() {
    return "only contain alphabetic characters, numbers or underscores";
  }

  @Override
  public boolean isValid(Object property) {
    if (property != null && (property instanceof String)) {
      String str = String.class.cast(property);
      Matcher matcher = PATTERN.matcher(str);
      return matcher.matches();
    }
    return true;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("AlphaUnderscoreRule")
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
        return new AlphaUnderscoreRule();
      }
      if (TRUE.equalsIgnoreCase(keyAndValue.get(1))) {
        return new AlphaUnderscoreRule();
      }
      return null;
    }

    @Override
    public List<String> toParsableString(Rule rule) {
      if (rule instanceof AlphaUnderscoreRule) {
        return Lists.newArrayList(KEY);
      }
      return Lists.newArrayList();
    }
  }
}
