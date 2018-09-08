package com.github.edgar615.util.validation;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 只能是数字.
 *
 * @author Edgar
 * @create 2018-09-04 15:13
 **/
class DecimalRule implements Rule {

  private static final String KEY = "decimal";

  /**
   * 正则表达式
   */
  private final String regex;

  private final int point;

  private DecimalRule(int point) {
    Preconditions.checkArgument(point > 0);
    this.point = point;
    String pointPattern = "\\d{1," + point + "}";
    this.regex = "[1-9]\\d*\\." + pointPattern + "|0\\." + pointPattern;
  }

  static Rule create(int point) {
    return new DecimalRule(point);
  }

  @Override
  public String message() {
    return "must be numeric and contain " + point + " decimal points";
  }

  @Override
  public boolean isValid(Object property) {
    if (property != null) {
      String str = property.toString();
      Pattern pattern = Pattern.compile(this.regex);
      Matcher matcher = pattern.matcher(str);
      return matcher.matches();
    }
    return true;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("DecimalRule")
        .add("point", point)
        .toString();
  }

  int point() {
    return point;
  }

  static class Parser implements RuleParser {

    @Override
    public Rule parse(List<String> keyAndValue) {
      String key = keyAndValue.get(0);
      if (!KEY.equals(key)) {
        return null;
      }
      if (keyAndValue.size() > 1) {
        return new DecimalRule(Integer.parseInt(keyAndValue.get(1)));
      }
      return null;
    }

    @Override
    public List<String> toParsableString(Rule rule) {
      if (rule instanceof DecimalRule) {
        return Lists.newArrayList(KEY, ((DecimalRule) rule).point + "");
      }
      return Lists.newArrayList();
    }
  }
}
