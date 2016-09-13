package com.edgar.util.validation;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * 参数值等于某个值的校验.
 * <p>
 * 参数值必须等于某个值.
 *
 * @author Edgar  Date 2016/1/6
 */
class EqualsRule implements Rule {

    /**
     * 比较的值.
     */
    private final String value;

    private EqualsRule(String value) {
        this.value = value;
    }

    static Rule create(String value) {
        return new EqualsRule(value);
    }

    @Override
    public String message() {
        return "Equals:" + value;
    }

    @Override
    public boolean isValid(Object property) {
        if (property == null) {
            return true;
        }
        return value.equalsIgnoreCase(property.toString());
    }

    @Override
    public Map<String, Object> toMap() {
        return ImmutableMap.of("equals", value);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("EqualsRule")
                .add("value", value)
                .toString();
    }
}
