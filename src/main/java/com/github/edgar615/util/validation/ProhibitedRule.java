package com.github.edgar615.util.validation;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

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

    @Override
    public boolean isValid(Object property) {
        if (property == null) {
            return true;
        }
        return false;
    }

    @Override
    public Map<String, Object> toMap() {
        return ImmutableMap.of("prohibited", true);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("ProhibitedRule")
                .toString();
    }
}
