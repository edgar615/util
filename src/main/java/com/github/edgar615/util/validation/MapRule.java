package com.github.edgar615.util.validation;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;

/**
 * 校验是否是map.
 * <p>
 *
 * @author Edgar  Date 2016/1/6
 */
class MapRule implements Rule {

  private static final String KEY = "map";

  private static final String TRUE = "true";

  private MapRule() {
  }

  static Rule create() {
    return new MapRule();
  }

  @Override
  public String message() {
    return "Map Required";
  }

  @Override
  public boolean isValid(Object property) {
    if (property == null) {
      return true;
    }
    if (property instanceof Map) {
      return true;
    }
    return false;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("MapRule")
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
        return new MapRule();
      }
      if (TRUE.equalsIgnoreCase(keyAndValue.get(1))) {
        return new MapRule();
      }
      return null;
    }

    @Override
    public List<String> toParsableString(Rule rule) {
      if (rule instanceof MapRule) {
        return Lists.newArrayList(KEY);
      }
      return Lists.newArrayList();
    }
  }
}
