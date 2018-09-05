package com.github.edgar615.util.validation;

import java.util.List;

/**
 * 校验规则.
 *
 * @author Edgar  Date 2016/1/6
 */
public interface Rule {

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
   * 固定长度校验.
   *
   * @param length 固定长度
   * @return Rule
   */
  static Rule fixLength(int length) {
    return FixLengthRule.create(length);
  }

  /**
   * 最大值校验.
   *
   * @param max 最大值
   * @return Rule
   */
  static Rule max(int max) {
    return MaxRule.create(max);
  }

  /**
   * 最小值校验.
   *
   * @param min 最小值.
   * @return Rule
   */
  static Rule min(int min) {
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
   * ISO 8601的日期格式
   *
   * @return Rule
   */
  static Rule iso8601Date() {
    return ISO8601DateRule.create();
  }

  /**
   * ISO 8601的时间格式
   *
   * @return Rule
   */
  static Rule iso8601Time() {
    return ISO8601TimeRule.create();
  }

  /**
   * ISO 8601的日期时间格式
   *
   * @return Rule
   */
  static Rule iso8601Datetime() {
    return ISO8601DateTimeRule.create();
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
   * byte的校验
   *
   * @return Rule
   */
  static Rule byteRule() {
    return ByteRule.create();
  }

  /**
   * short的校验
   *
   * @return Rule
   */
  static Rule shortRule() {
    return ShortRule.create();
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
   * long的校验
   *
   * @return Rule
   */
  static Rule longRule() {
    return LongRule.create();
  }

  /**
   * float的校验
   *
   * @return Rule
   */
  static Rule floatRule() {
    return FloatRule.create();
  }

  /**
   * double的校验
   *
   * @return Rule
   */
  static Rule doubleRule() {
    return DoubleRule.create();
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
   * 字母的校验.
   *
   * @return Rule
   */
  static Rule alpha() {
    return AlphaRule.create();
  }

  /**
   * 字母、数字、下划线的校验.
   *
   * @return Rule
   */
  static Rule alphaUnderscore() {
    return AlphaUnderscoreRule.create();
  }

  /**
   * 字母、数字的校验.
   *
   * @return Rule
   */
  static Rule alphaNumber() {
    return AlphaNumberRule.create();
  }

  /**
   * 字母、空格的校验.
   *
   * @return Rule
   */
  static Rule alphaSpace() {
    return AlphaSpaceRule.create();
  }

  /**
   * 数字的校验(不考虑长度).
   *
   * @return Rule
   */
  static Rule digits() {
    return DigitsRule.create();
  }

  /**
   * 数字的校验(考虑长度).
   *
   * @param length 长度
   * @return Rule
   */
  static Rule digits(int length) {
    return DigitsRule.create(length);
  }

  /**
   * 带小数点的数字校验.
   *
   * @param point 小数的长度
   * @return Rule
   */
  static Rule decimal(int point) {
    return DecimalRule.create(point);
  }
}
