package com.edgar.util.validation;

import java.util.List;
import java.util.Map;

/**
 * 校验规则.
 *
 * @author Edgar  Date 2016/1/6
 */
public interface Rule {
    /**
     * 邮箱校验.
     *
     * @return Rule
     */
    static Rule email() {
        return EmailRule.create();
    }

    /**
     * 相等校验.
     *
     * @return Rule
     */
    static Rule equals(String value) {
        return EqualsRule.create(value);
    }

    /**
     * 必填项校验.
     *
     * @return Rule
     */
    static Rule required() {
        return RequiredRule.create();
    }

    /**
     * 最大长度校验.
     *
     * @param length 最大长度
     * @return Rule
     */
    static Rule maxLength(int length) {
        return MaxLengthRule.create(length);
    }

    /**
     * 最小长度校验.
     *
     * @param length 最小长度
     * @return Rule
     */
    static Rule minLength(int length) {
        return MinLengthRule.create(length);
    }

    /**
     * 最大值校验.
     *
     * @param max 最大值
     * @return Rule
     */
    static Rule max(double max) {
        return MaxRule.create(max);
    }

    /**
     * 最小值校验.
     *
     * @param min 最小值.
     * @return Rule
     */
    static Rule min(double min) {
        return MinRule.create(min);
    }

    /**
     * 正则校验.
     *
     * @param regex 正则表达式
     * @return Rule
     */
    static Rule regex(String regex) {
        return RegexRule.create(regex);
    }

    /**
     * 禁止出现某个参数的校验
     *
     * @return Rule
     */
    static Rule prohibited() {
        return ProhibitedRule.create();
    }

    /**
     * 可选值的校验
     *
     * @param optionals 可以选择的值
     * @return Rule
     */
    static Rule optional(List<Object> optionals) {
        return OptionalRule.create(optionals);
    }

    /**
     * 整数的校验
     *
     * @return Rule
     */
    static Rule integer() {
        return IntRule.create();
    }

    /**
     * bool的校验
     *
     * @return Rule
     */
    static Rule bool() {
        return BoolRule.create();
    }

    /**
     * list的校验
     *
     * @return Rule
     */
    static Rule list() {
        return ListRule.create();
    }

    /**
     * map的校验
     *
     * @return Rule
     */
    static Rule map() {
        return MapRule.create();
    }
    /**
     * 非法数据的错误信息.
     *
     * @return 错误信息
     */
    String message();

    /**
     * 校验数据的合法性.
     *
     * @param property 属性
     * @return 合法，返回true.
     */
    boolean isValid(Object property);

    /**
     * 返回校验规则的Map对象，用于JSON的序列化.
     * @return map对象
     */
    Map<String, Object> toMap();
}
