package com.github.edgar615.util.validation;

import com.github.edgar615.util.reflect.BeanUtils;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 校验工具类.
 *
 * @author Edgar  Date 2016/4/13
 */
public class Validations {

  private Validations() {
    throw new AssertionError("Not instantiable: " + Validations.class);
  }

  /**
   * 校验JsonObject，仅支持单层校验. 如果参数值是list或者map，不会遍历list或map内部是否合法。
   * <p>
   *
   * @param params 需要校验的map对象
   * @param rules 校验规则的map对象，map的键值是需要校验的属性名，值是校验规则的集合
   */
  public static void validate(final Map<String, Object> params,
      final Multimap<String, Rule> rules) {
    Multimap<String, String> error = ArrayListMultimap.create();
    if (params == null) {
      return;
    }
    rules.asMap().forEach((field, fieldRules) -> {
      fieldRules.stream().forEach(rule -> {
        Object value = params.get(field);
        if (checkParameter(value)) {
          if (!rule.isValid(value)) {
            error.put(field, rule.message());
          }
        }
      });
    });
    if (!error.isEmpty()) {
      throw new ValidationException(error);
    }
  }

  /**
   * 校验MultiMap.
   *
   * @param multiMap 需要校验的对象
   * @param rules 校验规则的map对象，map的键值是需要校验的属性名，值是校验规则的集合
   */
  public static void validate(final Multimap<String, String> multiMap,
      final Multimap<String, Rule> rules) {
    Multimap<String, String> error = ArrayListMultimap.create();
    if (multiMap == null) {
      return;
    }
    rules.asMap().forEach((field, fieldRules) -> {
      fieldRules.stream().forEach(rule -> {
        List<String> values = new ArrayList<>(multiMap.get(field));
        if (checkRquired(rule, values)) {
          error.put(field, rule.message());
        }
        values.stream().forEach(value -> {
          if (checkParameter(value)) {
            if (!rule.isValid(value)) {
              error.put(field, rule.message());
            }
          }
        });
      });
    });
    if (!error.isEmpty()) {
      throw new ValidationException(error);
    }
  }

  /**
   * 校验JAVA BEAN
   * @param target 被校验的对象
   * @param rules 校验规则
   */
  public static void validate(Object target,Multimap<String, Rule> rules) {
    Map<String, Object> map = BeanUtils.toMap(target);
    validate(map, rules);
  }

  private static boolean checkRquired(Rule rule, List<String> values) {
    return (values == null || values.isEmpty()) && rule instanceof RequiredRule;
  }

  private static boolean checkParameter(Object value) {
    return value == null
        || value instanceof String
        || value instanceof Boolean
        || value instanceof Short
        || value instanceof Byte
        || value instanceof Character
        || value instanceof Integer
        || value instanceof Long
        || value instanceof Float
        || value instanceof Double
        || value instanceof List
        || value instanceof Map;
  }
}
