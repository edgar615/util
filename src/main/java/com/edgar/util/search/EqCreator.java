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
class EqCreator implements CriteriaCreator {
  @Override
  public List<Criteria> create(String field, String opValue, boolean negation) {
    //相等
    Preconditions.checkArgument(!Strings.isNullOrEmpty(opValue),
                                "Problems parsing queryString: %s",
                                negation ? "-" + field : field + ":" + opValue);
    if (negation) {
      return Lists.newArrayList(new Criteria(field, Op.NE, opValue));
    }
    return Lists.newArrayList(new Criteria(field, Op.EQ, opValue));
  }
}
