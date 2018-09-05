package com.github.edgar615.util.validation;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import java.util.List;

/**
 * 校验是否是list.
 * <p>
 *
 * @author Edgar  Date 2016/1/6
 */
class ListRule implements Rule {

  private static final String KEY = "list";

  private static final String TRUE = "true";

  private ListRule() {
  }

  static Rule create() {
    return new ListRule();
  }

  @Override
  public String message() {
    return "List Required";
  }

  @Override
  public boolean isValid(Object property) {
    if (property == null) {
      return true;
    }
    if (property instanceof List) {
      return true;
    }
    return false;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("ListRule")
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
        return new ListRule();
      }
      if (TRUE.equalsIgnoreCase(keyAndValue.get(1))) {
        return new ListRule();
      }
      return null;
    }

    @Override
    public List<String> toParsableString(Rule rule) {
      if (rule instanceof ListRule) {
        return Lists.newArrayList(KEY);
      }
      return Lists.newArrayList();
    }
  }
}
