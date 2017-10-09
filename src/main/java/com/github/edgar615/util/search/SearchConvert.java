package com.github.edgar615.util.search;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
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

  static List<Criterion> fromStr(String queryString) {
    List<Map.Entry<String, String>> params = checkAndCreateParams(queryString);
    return params.stream()
            .map(entry -> criterias(entry.getKey(), entry.getValue()))
            .reduce(Lists.newArrayList(), (criterias, criterias2) -> {
              criterias.addAll(criterias2);
              return new ArrayList<>(criterias);
            });
  }

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
                                    "Problems parsing queryString: %s", queryString);
        //前半部分是上一个参数名对应的参数值，后半部分是下一个参数值对应的参数名
        list.add(str.substring(0, index).trim());
        list.add(str.substring(index + 1).trim());
      }
    }
    Preconditions.checkArgument(list.size() % 2 == 0,
                                "Problems parsing queryString: %s", queryString);
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
    if (field.startsWith("-")) {
      Preconditions.checkArgument(field.length() > 1,
                                  "Problems parsing queryString: %s", field + ":" + opValue);
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
