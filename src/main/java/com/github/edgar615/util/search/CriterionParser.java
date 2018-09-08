package com.github.edgar615.util.search;

import java.util.List;

/**
 * Created by Edgar on 2017/5/16.
 *
 * @author Edgar  Date 2017/5/16
 */
public interface CriterionParser {

  List<Criterion> create(String field, String opValue, boolean negation);

}
