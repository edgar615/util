package com.github.edgar615.util.search;

import com.github.edgar615.util.base.StringUtils;
import com.google.common.base.CharMatcher;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Edgar on 2017/5/15.
 *
 * @author Edgar  Date 2017/5/15
 */
class SearchConvert {

  private static final String REVERSE_KEY = "-";

  static List<Criterion> fromStr(String queryString) {
    List<Map.Entry<String, String>> params = checkAndCreateParams2(queryString);
    return params.stream()
        .map(entry -> criterias(entry.getKey(), entry.getValue()))
        .reduce(Lists.newArrayList(), (criterias, criterias2) -> {
          criterias.addAll(criterias2);
          return new ArrayList<>(criterias);
        });
  }

  private static List<Map.Entry<String, String>> checkAndCreateParams2(String queryString) {
    //用空格分隔，从第一个开始遍历，如果遇到已空格开头，中间包含:的，说明是一个查询参数的开头
    List<String> splitedList = StringUtils.splitRemainDelimiter(queryString.trim(), " ");
    List<StringBuilder> queryList = new ArrayList<>();
    StringBuilder query = new StringBuilder();
    for (int i = 0; i < splitedList.size(); i++) {
      String s = splitedList.get(i);
      if (i == 0) {
        query.append(s);
        i++;
      } else {
        String prev = splitedList.get(i - 1);
        if (s.indexOf(":") > 0 && CharMatcher.whitespace().matchesAllOf(prev)) {
          queryList.add(query);
          query = new StringBuilder();
          query.append(s);
        } else {
          query.append(s);
        }
      }
    }
    if (!Strings.isNullOrEmpty(query.toString())) {
      queryList.add(query);
    }
    List<Map.Entry<String, String>> params = new ArrayList<>();
    for (StringBuilder s : queryList) {
      //第一个:就是分隔符
      String s1 = s.toString();
      int index = s1.indexOf(":");
      String field = s.substring(0, index).trim();
      String value = s.substring(index + 1).trim();
      params.add(Maps.immutableEntry(field, value));
    }
    return params;
  }

  /**
   * 这个方法用:来分隔，不能处理查询参数里带:的问题
   */
  @Deprecated
  private static List<Map.Entry<String, String>> checkAndCreateParams(String queryString) {
    List<String> splitedList =
        Splitter.onPattern(":")
            .omitEmptyStrings()
            .trimResults()
            .splitToList(queryString);
    List<String> list = new ArrayList<>();
    for (int i = 0; i < splitedList.size(); i++) {
      if (i == 0 || i == splitedList.size() - 1) {
        //第一个参数名，最后一个参数值
        list.add(splitedList.get(i).trim());
      } else {
        String str = splitedList.get(i);
        int index = str.lastIndexOf(" ");
        Preconditions.checkArgument(index > 0,
            "Invalid query: %s", queryString);
        //前半部分是上一个参数名对应的参数值，后半部分是下一个参数值对应的参数名
        list.add(str.substring(0, index).trim());
        list.add(str.substring(index + 1).trim());
      }
    }
    Preconditions.checkArgument(list.size() % 2 == 0,
        "Invalid query: %s", queryString);
    List<Map.Entry<String, String>> params = new ArrayList<>();
    for (int i = 0; i < list.size(); i = i + 2) {
      params.add(Maps.immutableEntry(list.get(i), list.get(i + 1)));
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
    List<Criterion> criterias = new LoeCreator().create(field, opValue, negation);
    if (criterias == null) {
      criterias = new GoeCreator().create(field, opValue, negation);
    }
    if (criterias == null) {
      criterias = new BetweenCreator().create(field, opValue, negation);
    }
    if (criterias == null) {
      criterias = new LikeCreator().create(field, opValue, negation);
    }
    if (criterias == null) {
      criterias = new EqCreator().create(field, opValue, negation);
    }
    return criterias;
  }
}
