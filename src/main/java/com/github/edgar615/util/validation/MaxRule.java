package com.github.edgar615.util.validation;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 数值最大值的校验.
 * <p>
 * 只校验数值类型的值，其他类型默认为合法.
 *
 * @author Edgar  Date 2016/1/6
 */
class MaxRule implements Rule {

    /**
     * 最大值.
     */
    private final int value;

    private MaxRule(int value) {
        this.value = value;
    }

    static Rule create(int value) {
        return new MaxRule(value);
    }

    @Override
    public String message() {
        return "Max value:" + value;
    }

    @Override
    public boolean isValid(Object property) {
        if (property != null && property instanceof Short) {
            return Short.class.cast(property) <= value;
        }
        if (property != null && property instanceof Integer) {
            return Integer.class.cast(property) <= value;
        }
        if (property != null && property instanceof Long) {
            return Long.class.cast(property) <= value;
        }
        if (property != null && property instanceof Float) {
            return Float.class.cast(property) <= value;
        }
        if (property != null && property instanceof Double) {
            return Double.class.cast(property) <= value;
        }
        if (property != null && (property instanceof String)) {
            String str = String.class.cast(property);
            try {
                return new BigDecimal(str).compareTo(new BigDecimal(value + "")) <= 0;
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Map<String, Object> toMap() {
        return ImmutableMap.of("max", value);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("MaxRule")
                .add("value", value)
                .toString();
    }
}
