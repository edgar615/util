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

import java.util.HashSet;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;

public class CriterionTest {

  @Test
  public void testEquals() {
    Criterion criteria = new Criterion("a", Op.IS_NOT_NULL);
    Criterion criteria2 = new Criterion("a", Op.IS_NOT_NULL);
    Set<Criterion> criterias = new HashSet<Criterion>();
    criterias.add(criteria);
    criterias.add(criteria2);
    Assert.assertEquals(criteria, criteria2);
    Assert.assertTrue(criterias.size() == 1);
  }

  @Test
  public void testContain() {
    Set<Criterion> criterias = new HashSet<>();
    criterias.add(new Criterion("a", Op.EQ, "b"));
    criterias.add(new Criterion("a", Op.EQ, "b"));
    Assert.assertEquals(1, criterias.size());
    Assert.assertTrue(criterias.contains(new Criterion("a", Op.EQ,
        "b")));
  }
}
