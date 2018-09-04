package com.github.edgar615.util.validation;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ISO 8601的日期格式.
 * <p>
 * 只校验String类型的值，其他类型默认为非法.
 *
 * @author Edgar  Date 2016/1/6
 */
class ISO8601DateTimeRule implements Rule {

    /**
     * 正则表达式
     */
    private static final Pattern PATTERN = Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2}T0[0-9]:[0-5][0-9]:[0-5][0-9]" +
            "|\\d{4}-\\d{1,2}-\\d{1,2}T1[0-9]:[0-5][0-9]:[0-5][0-9]" +
            "|\\d{4}-\\d{1,2}-\\d{1,2}T2[0-3]:[0-5][0-9]:[0-5][0-9]");

    private ISO8601DateTimeRule() {
    }

    static Rule create() {
        return new ISO8601DateTimeRule();
    }

    @Override
    public String message() {
        return "Must match pattern: yyyy-MM-ddTHH:mm:ss";
    }

    @Override
    public boolean isValid(Object property) {
        if (property != null && (property instanceof String)) {
            String str = String.class.cast(property);
            Matcher matcher = PATTERN.matcher(str);
            return matcher.matches();
        }
        return true;
    }

    @Override
    public Map<String, Object> toMap() {
        return ImmutableMap.of("iso8601Datetime", true);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("ISO8601DateTimeRule")
                .add("iso8601Datetime", true)
                .toString();
    }
}
