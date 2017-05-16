package com.edgar.util.search;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by Edgar on 2017/5/16.
 *
 * @author Edgar  Date 2017/5/16
 */
class GoeCreator implements CriteriaCreator {
  @Override
  public List<Criteria> create(String field, String opValue, boolean negation) {
    if (opValue.startsWith("<")
        && !opValue.startsWith("<=")) {
      Preconditions.checkArgument(opValue.length() > 1,
                                  "Problems parsing queryString: %s",
                                  negation ? "-" + field : field + ":" + opValue);
      String value = opValue.substring(1);
      if (negation) {
        return Lists.newArrayList(new Criteria(field, Op.GOE, value));
      }
      return Lists.newArrayList(new Criteria(field, Op.LT, value));
    }
    if (opValue.startsWith("<=")) {
      Preconditions.checkArgument(opValue.length() > 2,
                                  "Problems parsing queryString: %s",
                                  negation ? "-" + field : field + ":" + opValue);
      String value = opValue.substring(2);
      if (negation) {
        return Lists.newArrayList(new Criteria(field, Op.GT, value));
      }
      return Lists.newArrayList(new Criteria(field, Op.LOE, value));
    }
    return null;
  }
}
