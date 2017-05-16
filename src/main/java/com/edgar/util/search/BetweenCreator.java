package com.edgar.util.search;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by Edgar on 2017/5/16.
 *
 * @author Edgar  Date 2017/5/16
 */
class BetweenCreator implements CriteriaCreator {
  @Override
  public List<Criteria> create(String field, String opValue, boolean negation) {
    if (opValue.contains("..")) {
      List<String> rangeList = Splitter.on("..")
              .limit(2)
              .omitEmptyStrings()
              .trimResults()
              .splitToList(opValue);
      Preconditions.checkArgument(rangeList.size() == 2,
                                  "Problems parsing queryString: %s",
                                  negation ? "-" + field : field + ":" + opValue);
      String start = rangeList.get(0);
      String end = rangeList.get(1);
      if ("*".equals(start) && "*".equals(end)) {
        Preconditions.checkArgument(rangeList.size() == 2,
                                    "Problems parsing queryString: %s",
                                    negation ? "-" + field : field + ":" + opValue);
      } else if ("*".equals(start)) {
        if (negation) {
          return Lists.newArrayList(new Criteria(field, Op.GT, end));
        }
        return Lists.newArrayList(new Criteria(field, Op.LOE, end));
      } else if ("*".equals(end)) {
        if (negation) {
          return Lists.newArrayList(new Criteria(field, Op.LT, start));
        }
        return Lists.newArrayList(new Criteria(field, Op.GOE, start));
      } else {
        if (negation) {
          return Lists.newArrayList(new Criteria(field, Op.LT, start),
                                    new Criteria(field, Op.GT, end));
        }
        return Lists.newArrayList(new Criteria(field, Op.BETWEEN, start, end));
      }
    }
    return null;
  }
}
