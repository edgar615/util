package com.github.edgar615.util.validation;

import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 可选值的校验.
 *
 * @author Edgar  Date 2016/5/4
 */
class OptionalRule implements Rule {

  private static final String KEY = "optional";

  private static final String SPLITTER = ",";

  private final List<Object> value = new ArrayList<>();

  private OptionalRule(List<Object> value) {
    this.value.addAll(value);
  }

  static Rule create(List<Object> value) {
    return new OptionalRule(value);
  }


  @Override
  public String message() {
    return "Optional value:" + value;
  }

  @Override
  public boolean isValid(Object property) {
    if (property != null) {
      return value.stream().anyMatch(obj -> property.toString().equalsIgnoreCase(obj.toString()));
    }
    return true;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("OptionalRule")
        .add("value", value)
        .toString();
  }

  List<Object> value() {
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
        List<String> valueList = Splitter.on(SPLITTER).trimResults().omitEmptyStrings()
            .splitToList(keyAndValue.get(1));
        return new OptionalRule(valueList.stream().map(s -> s).collect(Collectors.toList()));
      }
      return null;
    }

    @Override
    public List<String> toParsableString(Rule rule) {
      if (rule instanceof OptionalRule) {
        return Lists.newArrayList(KEY, Joiner.on(SPLITTER).join(((OptionalRule) rule).value));
      }
      return Lists.newArrayList();
    }
  }
}
