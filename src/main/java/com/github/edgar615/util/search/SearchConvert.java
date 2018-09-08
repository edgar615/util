package com.github.edgar615.util.search;

import com.github.edgar615.util.base.StringUtils;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import sun.invoke.empty.Empty;

/**
 * Created by Edgar on 2017/5/15.
 *
 * @author Edgar  Date 2017/5/15
 */
class SearchConvert {

  private static final String REVERSE_KEY = "-";

  /**
   * Splits each key-value pair.
   */
  private static final String KEYS_SEPARATOR = " ";

  private static final String KEY_VALUE_SEPARATOR = ":";

  private static final String EMPTY = "";

  static List<Criterion> fromStr(String queryString) {
    List<Map.Entry<String, String>> params = checkAndCreateKeyAndValue(queryString);
    return params.stream()
        .map(entry -> criterias(entry.getKey(), entry.getValue()))
        .reduce(Lists.newArrayList(), (criterias, criterias2) -> {
          criterias.addAll(criterias2);
          return new ArrayList<>(criterias);
        });
  }

  private static List<Map.Entry<String, String>> checkAndCreateKeyAndValue(String queryString) {
    //用空格分隔，从第一个开始遍历，如果遇到以空格开头，中间包含:的，说明是一个查询参数的开头
    List<String> splitRemainDelimiter = StringUtils
        .splitRemainDelimiter(queryString.trim(), KEYS_SEPARATOR);
    List<String> disassemblyList = new ArrayList<>();
    for (String str : splitRemainDelimiter) {
      if (str.contains(KEY_VALUE_SEPARATOR)) {
        disassemblyList.addAll(StringUtils.splitRemainDelimiter(str, KEY_VALUE_SEPARATOR));
      } else {
        disassemblyList.add(str);
      }
    }
    //查找:分隔符的索引
    List<Integer> splitIndexes = new ArrayList<>();
    for (int i = 0; i < disassemblyList.size(); i++) {
      String s = disassemblyList.get(i);
      if (KEY_VALUE_SEPARATOR.equals(s)) {
        splitIndexes.add(i);
      }
    }

    int cursor = 0;
    List<String> noSepatatorList = new ArrayList<>();
    for (int i = 0; i < splitIndexes.size();i ++) {
      int splitIndex = splitIndexes.get(i);
      String last = disassemblyList.get(splitIndex - 1);
      if (CharMatcher.whitespace().matchesAllOf(last)) {
        noSepatatorList.add(Joiner.on(EMPTY).join(disassemblyList.subList(cursor, Math.max(splitIndex - 1, 0))));
      } else {
        if (cursor == splitIndex - 1) {
          noSepatatorList.add(last);
        } else {
          noSepatatorList.add(Joiner.on(EMPTY).join(disassemblyList.subList(cursor, Math.max(splitIndex - 2, 0))));
          noSepatatorList.add(last);
        }
      }
      cursor = splitIndex + 1;
    }
    noSepatatorList.add(Joiner.on(EMPTY).join(disassemblyList.subList(cursor, disassemblyList.size())));
    List<Map.Entry<String, String>> params = new ArrayList<>();
    for (int i = 0; i < noSepatatorList.size() - 1; i = i + 2) {
      String key = noSepatatorList.get(i).trim();
      String value = noSepatatorList.get(i + 1).trim();
      params.add(Maps.immutableEntry(key, value));
    }
    return params;
  }

  private static List<Criterion> criterias(String key, String opValue) {
    //大于等于
    String field = key;
    boolean negation = false;
    if (field.startsWith(REVERSE_KEY)) {
      Preconditions.checkArgument(field.length() > 1,
          "Invalid query: %s", field + ":" + opValue);
      field = field.substring(1);
      negation = true;
    }
    List<Criterion> criterias = new LoeParser().create(field, opValue, negation);
    if (criterias == null) {
      criterias = new GoeParser().create(field, opValue, negation);
    }
    if (criterias == null) {
      criterias = new BetweenParser().create(field, opValue, negation);
    }
    if (criterias == null) {
      criterias = new LikeParser().create(field, opValue, negation);
    }
    if (criterias == null) {
      criterias = new EqParser().create(field, opValue, negation);
    }
    return criterias;
  }
}
