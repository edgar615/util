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
