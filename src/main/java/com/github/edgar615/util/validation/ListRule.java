package com.github.edgar615.util.validation;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;

import java.util.List;
import java.util.Map;

/**
 * 校验是否是list.
 * <p>
 *
 * @author Edgar  Date 2016/1/6
 */
class ListRule implements Rule {


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
    public Map<String, Object> toMap() {
        return ImmutableMap.of("list", true);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("ListRule")
                .toString();
    }
}
