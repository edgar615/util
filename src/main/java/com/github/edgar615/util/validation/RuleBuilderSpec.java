package com.github.edgar615.util.validation;

import com.google.common.base.Splitter;

/**
 * Rule的String定义解析.
 *
 * @author Edgar
 * @create 2018-09-05 11:06
 **/
public class RuleBuilderSpec {

  /**
   * Splits each key-value pair.
   */
  private static final Splitter KEYS_SPLITTER = Splitter.on(',').trimResults();

  /**
   * Splits the key from the value.
   */
  private static final Splitter KEY_VALUE_SPLITTER = Splitter.on(':').trimResults();

//  public static RuleBuilderSpec parse(String specification) {
//    if (!specification.isEmpty()) {
//      for (String keyValuePair : KEYS_SPLITTER.split(specification)) {
//        List<String> keyAndValue = ImmutableList.copyOf(KEY_VALUE_SPLITTER.split(keyValuePair));
//        Preconditions.checkArgument(!keyAndValue.isEmpty(), "blank key-value pair");
//        Preconditions.checkArgument(
//            keyAndValue.size() <= 2,
//            "key-value pair %s with more than one equals sign",
//            keyValuePair);
//
//        // Find the ValueParser for the current key.
//        String key = keyAndValue.get(0);
//        ValueParser valueParser = VALUE_PARSERS.get(key);
//        checkArgument(valueParser != null, "unknown key %s", key);
//
//        String value = keyAndValue.size() == 1 ? null : keyAndValue.get(1);
//        valueParser.parse(spec, key, value);
//      }
//    }
//
//    return spec;
//  }
}
