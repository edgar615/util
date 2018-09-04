package com.github.edgar615.util.validation;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * 校验是否是long.
 * <p>
 * 只校验String类型的值，其他类型默认为合法.
 *
 * @author Edgar  Date 2016/1/6
 */
class LongRule implements Rule {

    private LongRule() {
    }

    static Rule create() {
        return new LongRule();
    }

    @Override
    public String message() {
        return "Long Required";
    }

    @Override
    public boolean isValid(Object property) {
        if (property == null) {
            return true;
        }
        if (property instanceof Long) {
            return true;
        }
        if (property instanceof Integer) {
            return true;
        }
        if (property instanceof Short) {
            return true;
        }
        if (property instanceof Byte) {
            return true;
        }
        if (property != null && (property instanceof String)) {
            String str = String.class.cast(property);
            try {
                Long.parseLong(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public Map<String, Object> toMap() {
        return ImmutableMap.of("long", true);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("LongRule")
                .toString();
    }
}
