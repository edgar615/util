/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.edgar615.util.search;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import java.util.List;

/**
 * Created by Edgar on 2017/5/16.
 *
 * @author Edgar  Date 2017/5/16
 */
class BetweenParser implements CriterionParser {

  private static final String SPLIT = "..";

  private static final String WILDCARD = "*";

  @Override
  public List<Criterion> create(String field, String opValue, boolean negation) {
    if (opValue.contains(SPLIT)) {
      List<String> rangeList = Splitter.on(SPLIT)
          .limit(2)
          .omitEmptyStrings()
          .trimResults()
          .splitToList(opValue);
      Preconditions.checkArgument(rangeList.size() == 2,
          "Problems parsing query: %s",
          negation ? "-" + field : field + ":" + opValue);
      String start = rangeList.get(0);
      String end = rangeList.get(1);
      if (WILDCARD.equals(start) && WILDCARD.equals(end)) {
        Preconditions.checkArgument(rangeList.size() == 2,
            "Problems parsing query: %s",
            negation ? "-" + field : field + ":" + opValue);
      } else if (WILDCARD.equals(start)) {
        if (negation) {
          return Lists.newArrayList(new Criterion(field, Op.GT, end));
        }
        return Lists.newArrayList(new Criterion(field, Op.LE, end));
      } else if (WILDCARD.equals(end)) {
        if (negation) {
          return Lists.newArrayList(new Criterion(field, Op.LT, start));
        }
        return Lists.newArrayList(new Criterion(field, Op.GE, start));
      } else {
        if (negation) {
          return Lists.newArrayList(new Criterion(field, Op.LT, start),
              new Criterion(field, Op.GT, end));
        }
        return Lists.newArrayList(new Criterion(field, Op.BETWEEN, start, end));
      }
    }
    return null;
  }
}
