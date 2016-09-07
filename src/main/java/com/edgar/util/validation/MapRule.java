package com.edgar.util.validation;

import com.google.common.base.MoreObjects;

import java.util.List;
import java.util.Map;

/**
 * 校验是否是map.
 * <p>
 *
 * @author Edgar  Date 2016/1/6
 */
class MapRule implements Rule {


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
}
