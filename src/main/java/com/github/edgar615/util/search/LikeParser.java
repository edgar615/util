package com.github.edgar615.util.search;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.List;

/**
 * Created by Edgar on 2017/5/16.
 *
 * @author Edgar  Date 2017/5/16
 */
class LikeParser implements CriterionParser {

  private static final String WILDCARD = "*";

  @Override
  public List<Criterion> create(String field, String opValue, boolean negation) {
    if (opValue.startsWith(WILDCARD) && opValue.endsWith(WILDCARD)) {
      Preconditions.checkArgument(opValue.length() > 2,
          "Problems parsing query: %s", field + ":" + opValue);
      Preconditions.checkArgument(!negation,
          "Problems parsing query: %s",
          negation ? "-" + field : field + ":" + opValue);
      return Lists
          .newArrayList(new Criterion(field, Op.CN, opValue.substring(1, opValue.length() - 1)));
    }
    if (opValue.startsWith(WILDCARD) && !opValue.endsWith(WILDCARD)) {
      Preconditions.checkArgument(!negation,
          "Problems parsing query: %s", field + ":" + opValue);
      Preconditions.checkArgument(!negation,
          "Problems parsing query: %s",
          negation ? "-" + field : field + ":" + opValue);
      return Lists
          .newArrayList(new Criterion(field, Op.EW, opValue.substring(1, opValue.length())));
    }
    if (!opValue.startsWith(WILDCARD) && opValue.endsWith(WILDCARD)) {
      Preconditions.checkArgument(!negation,
          "Problems parsing query: %s",
          negation ? "-" + field : field + ":" + opValue);
      Preconditions.checkArgument(!negation,
          "Problems parsing query: %s", field + ":" + opValue);
      return Lists
          .newArrayList(new Criterion(field, Op.SW, opValue.substring(0, opValue.length() - 1)));
    }
    return null;
  }
}
