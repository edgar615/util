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
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import java.util.List;

/**
 * Created by Edgar on 2017/5/16.
 *
 * @author Edgar  Date 2017/5/16
 */
class LoeParser implements CriterionParser {

  private static final String GE = ">=";
  private static final String GT = ">";

  @Override
  public List<Criterion> create(String field, String opValue, boolean negation) {
    if (opValue.startsWith(GT)
        && !opValue.startsWith(GE)) {
      String value = opValue.substring(1).trim();
      Preconditions.checkArgument(!Strings.isNullOrEmpty(value),
          "Problems parsing query: %s",
          negation ? "-" + field : field + ":" + opValue);
      if (negation) {
        return Lists.newArrayList(new Criterion(field, Op.LE, value));
      }
      return Lists.newArrayList(new Criterion(field, Op.GT, value));
    }
    if (opValue.startsWith(GE)) {
      String value = opValue.substring(2).trim();
      Preconditions.checkArgument(!Strings.isNullOrEmpty(value),
          "Problems parsing query: %s",
          negation ? "-" + field : field + ":" + opValue);
      if (negation) {
        return Lists.newArrayList(new Criterion(field, Op.LT, value));
      }
      return Lists.newArrayList(new Criterion(field, Op.GE, value));
    }
    return null;
  }

}
