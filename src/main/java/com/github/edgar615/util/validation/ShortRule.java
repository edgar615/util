package com.github.edgar615.util.validation;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * 校验是否是short.
 * <p>
 * 只校验String类型的值，其他类型默认为合法.
 *
 * @author Edgar  Date 2016/1/6
 */
class ShortRule implements Rule {

    private ShortRule() {
    }

    static Rule create() {
        return new ShortRule();
    }

    @Override
    public String message() {
        return "Short Required";
    }

    @Override
    public boolean isValid(Object property) {
        if (property == null) {
            return true;
        }
        if (property instanceof Short) {
            return true;
        }
        if (property instanceof Byte) {
            return true;
        }
        if (property instanceof Integer) {
            Integer intVal = (Integer) property;
            return intVal >= Short.MIN_VALUE && intVal <= Short.MAX_VALUE;
        }
        if (property instanceof Long) {
            Long longVal = (Long) property;
            return longVal >= Short.MIN_VALUE && longVal <= Short.MAX_VALUE;
        }
        if (property != null && (property instanceof String)) {
            String str = String.class.cast(property);
            try {
                Short.parseShort(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public Map<String, Object> toMap() {
        return ImmutableMap.of("short", true);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("ShortRule")
                .toString();
    }
}
