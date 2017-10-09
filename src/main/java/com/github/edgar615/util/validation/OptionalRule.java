package com.github.edgar615.util.validation;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 可选值的校验.
 *
 * @author Edgar  Date 2016/5/4
 */
class OptionalRule implements Rule {

  private final List<Object> value = new ArrayList<>();

  private OptionalRule(List<Object> value) {this.value.addAll(value);}

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
  public Map<String, Object> toMap() {
    return ImmutableMap.of("optional", value);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("OptionalRule")
            .add("value", value)
            .toString();
  }
}
