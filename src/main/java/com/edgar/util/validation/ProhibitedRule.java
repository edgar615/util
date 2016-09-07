package com.edgar.util.validation;

import com.google.common.base.MoreObjects;

/**
 * 禁止属性校验.如果该属性不为null，这表示非法.
 *
 * @author Edgar  Date 2016/4/18
 */
public class ProhibitedRule implements Rule {

  private ProhibitedRule() {
  }

  static Rule create() {
    return new ProhibitedRule();
  }

  @Override
  public String message() {
    return "Prohibited";
  }

  public boolean isValid(Object property) {
    if (property == null) {
      return true;
    }
    return false;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("ProhibitedRule")
            .toString();
  }
}
