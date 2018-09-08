package com.github.edgar615.util.search;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import java.util.List;

/**
 * Created by Edgar on 2017/5/16.
 *
 * @author Edgar  Date 2017/5/16
 */
class EqParser implements CriterionParser {

  private static final String IN_SEPARATOR = ",";

  @Override
  public List<Criterion> create(String field, String opValue, boolean negation) {
    Preconditions.checkArgument(!Strings.isNullOrEmpty(opValue),
        "Problems parsing query: %s",
        negation ? "-" + field : field + ":" + opValue);
    if (opValue.contains(IN_SEPARATOR)) {
      //IN
      List<String> inValues = Splitter.on(IN_SEPARATOR).trimResults().omitEmptyStrings()
          .splitToList(opValue);
      if (negation) {
        return Lists.newArrayList(new Criterion(field, Op.NOT_IN, inValues));
      }
      return Lists.newArrayList(new Criterion(field, Op.IN, inValues));
    }
    //=
    if (negation) {
      return Lists.newArrayList(new Criterion(field, Op.NE, opValue));
    }
    return Lists.newArrayList(new Criterion(field, Op.EQ, opValue));
  }
}
