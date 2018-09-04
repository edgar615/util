package com.github.edgar615.util.base;

import com.github.edgar615.util.search.Criterion;
import com.github.edgar615.util.search.MapPredicate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Json工具类.
 *
 * @author Edgar
 * @version 1.0
 */
public interface MapUtils {

  static void mergeIn(Map<String, Object> map, Map<String, Object> other) {
    map.putAll(other);
  }

  /**
   * 从一个map中获取String
   */
  static String getString(Map<String, Object> map, String key) {
    Objects.requireNonNull(key);
    CharSequence cs = (CharSequence) map.get(key);
    return cs == null ? null : cs.toString();
  }

  /**
   * 从一个map中获取string
   *
   * @param def 默认值
   */
  static String getString(Map<String, Object> map, String key, String def) {
    String cs = getString(map, key);
    if (cs == null) {
      return def;
    }
    return cs;
  }

  /**
   * 从一个map中获取int
   *
   * @param map map
   * @param key 键值
   * @return int类型的值，如果value不能转换为int，抛出异常
   */
  static Integer getInteger(Map<String, Object> map, String key) {
    Object value = map.get(key);
    if (value == null) {
      return null;
    }
    if (value instanceof Integer) {
      return (Integer) value;
    }
    if (value instanceof String) {
      if ("".equalsIgnoreCase(value.toString())) {
        return 0;
      }
      return Integer.parseInt(value.toString());
    }
    throw new ClassCastException(value.getClass() + " cannot be cast to java.lang.Integer");
  }

  /**
   * 从一个map中获取int
   *
   * @param map map
   * @param key 键值
   * @param def 默认值
   * @return int类型的值，如果value不能转换为int，抛出异常，如果没有该值，返回默认值
   */
  static Integer getInteger(Map<String, Object> map, String key, int def) {
    Integer value = getInteger(map, key);

    if (value == null) {
      return def;
    }
    return value;
  }

  /**
   * 从一个map中获取bool
   *
   * @param map map
   * @param key 键值
   * @return bool类型的值，如果value不能转换为bool，抛出异常
   */
  static Boolean getBoolean(Map<String, Object> map, String key) {
    Object value = map.get(key);
    if (value == null) {
      return null;
    }
    if (value instanceof Boolean) {
      return (Boolean) value;
    }
    if (value instanceof String) {
      return Boolean.parseBoolean(value.toString());
    }
    throw new ClassCastException(value.getClass() + " cannot be cast to java.lang.Boolean");
  }

  /**
   * 从一个map中获取bool
   *
   * @param map map
   * @param key 键值
   * @param def 默认值
   * @return bool类型的值，如果value不能转换为bool，抛出异常，如果没有该值，返回默认值
   */
  static Boolean getBoolean(Map<String, Object> map, String key, Boolean def) {
    Boolean value = getBoolean(map, key);

    if (value == null) {
      return def;
    }
    return value;
  }

  /**
   * 从一个map中获取long
   *
   * @param map map
   * @param key 键值
   * @return long类型的值，如果value不能转换为long，抛出异常
   */
  static Long getLong(Map<String, Object> map, String key) {
    Object value = map.get(key);
    if (value == null) {
      return null;
    }
    if (value instanceof Long) {
      return (Long) value;
    }
    if (value instanceof String) {
      if ("".equalsIgnoreCase(value.toString())) {
        return 0L;
      }
      return Long.parseLong(value.toString());
    }
    throw new ClassCastException(value.getClass() + " cannot be cast to java.lang.Long");
  }

  /**
   * 从一个map中获取long
   *
   * @param map map
   * @param key 键值
   * @param def 默认值
   * @return long类型的值，如果value不能转换为long，抛出异常，如果没有该值，返回默认值
   */
  static Long getLong(Map<String, Object> map, String key, long def) {
    Long value = getLong(map, key);

    if (value == null) {
      return def;
    }
    return value;
  }

  /**
   * 从一个map中获取double
   *
   * @param map map
   * @param key 键值
   * @return int类型的值，如果value不能转换为Double，抛出异常
   */
  static Double getDouble(Map<String, Object> map, String key) {
    Object value = map.get(key);
    if (value == null) {
      return null;
    }
    if (value instanceof Double) {
      return (Double) value;
    }
    if (value instanceof Float) {
      return Double.parseDouble(value.toString());
    }
    if (value instanceof String) {
      if ("".equalsIgnoreCase(value.toString())) {
        return 0d;
      }
      return Double.parseDouble(value.toString());
    }
    throw new ClassCastException(value.getClass() + " cannot be cast to java.lang.Double");
  }

  /**
   * 从一个map中获取Double
   *
   * @param map map
   * @param key 键值
   * @param def 默认值
   * @return int类型的值，如果value不能转换为Double，抛出异常，如果没有该值，返回默认值
   */
  static Double getDouble(Map<String, Object> map, String key, double def) {
    Double value = getDouble(map, key);

    if (value == null) {
      return def;
    }
    return value;
  }

  /**
   * 从一个map中获取Float
   *
   * @param map map
   * @param key 键值
   * @return int类型的值，如果value不能转换为Float，抛出异常
   */
  static Float getFloat(Map<String, Object> map, String key) {
    Object value = map.get(key);
    if (value == null) {
      return null;
    }
    if (value instanceof Float) {
      return (Float) value;
    }
    if (value instanceof Double) {
      return Float.parseFloat(value.toString());
    }
    if (value instanceof String) {
      if ("".equalsIgnoreCase(value.toString())) {
        return 0f;
      }
      return Float.parseFloat(value.toString());
    }
    throw new ClassCastException(value.getClass() + " cannot be cast to java.lang.Float");
  }

  /**
   * 从一个map中获取Float
   *
   * @param map map
   * @param key 键值
   * @param def 默认值
   * @return int类型的值，如果value不能转换为Float,抛出异常，如果没有该值，返回默认值
   */
  static Float getFloat(Map<String, Object> map, String key, float def) {
    Float value = getFloat(map, key);

    if (value == null) {
      return def;
    }
    return value;
  }

  /**
   * 取差集
   */
  static Map<String, Object> difference(Map<String, Object> oldMap,
      Map<String, Object> newMap) {
    Map<String, Object> difference = new HashMap<>();
    for (String key : newMap.keySet()) {
      if (!oldMap.containsKey(key)) {
        difference.put(key, newMap.get(key));
      } else {
        Object oldValue = oldMap.get(key);
        Object newValue = newMap.get(key);
        if (newValue != null && !oldValue.toString().equals(newValue.toString())) {
          difference.put(key, newValue);
        }
      }
    }
    return difference;
  }

  /**
   * 检查一个map是否符合条件
   */
  static boolean check(Map<String, Object> map, List<Criterion> criteria) {
    return criteria.stream()
        .map(c -> new MapPredicate().apply(map, c))
        .reduce(true, (b1, b2) -> b1 && b2);
  }

  /**
   * 删除value是null的键值对
   */
  static Map<String, Object> removeNull(Map<String, Object> source) {
    Map<String, Object> map = new HashMap<>();
    source.forEach((k, v) -> {
      if (v != null) {
        map.put(k, v);
      }
    });
    return map;
  }

}