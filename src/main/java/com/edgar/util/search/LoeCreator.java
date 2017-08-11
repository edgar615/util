package com.edgar.util.search;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by Edgar on 2017/5/16.
 *
 * @author Edgar  Date 2017/5/16
 */
class LoeCreator implements CriterionCreator {

  @Override
  public List<Criterion> create(String field, String opValue, boolean negation) {
    if (opValue.startsWith(">")
        && !opValue.startsWith(">=")) {
      String value = opValue.substring(1).trim();
      Preconditions.checkArgument(!Strings.isNullOrEmpty(value),
                                  "Problems parsing queryString: %s",
                                  negation ? "-" + field : field + ":" + opValue);
      if (negation) {
        return Lists.newArrayList(new Criterion(field, Op.LE, value));
      }
      return Lists.newArrayList(new Criterion(field, Op.GT, value));
    }
    if (opValue.startsWith(">=")) {
      String value = opValue.substring(2).trim();
      Preconditions.checkArgument(!Strings.isNullOrEmpty(value),
                                  "Problems parsing queryString: %s",
                                  negation ? "-" + field : field + ":" + opValue);
      if (negation) {
        return Lists.newArrayList(new Criterion(field, Op.LT, value));
      }
      return Lists.newArrayList(new Criterion(field, Op.GE, value));
    }
    return null;
  }

}
