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

package com.github.edgar615.util.base;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by edgar on 16-3-31.
 */
public class MorePreconditionsTest {

  @Test
  public void testString() {
    MorePreconditions.checkNotNullOrEmpty(" ");
    MorePreconditions.checkNotNullOrEmpty("1");
  }


  @Test(expected = IllegalArgumentException.class)
  public void testNullString() {
    MorePreconditions.checkNotNullOrEmpty(" ");
    MorePreconditions.checkNotNullOrEmpty("1");
    MorePreconditions.checkNotNullOrEmpty(null);
    Assert.fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyString() {
    MorePreconditions.checkNotNullOrEmpty(" ");
    MorePreconditions.checkNotNullOrEmpty("1");
    MorePreconditions.checkNotNullOrEmpty("");
    Assert.fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullOrEmpty() {
    MorePreconditions.checkNotEmpty(ImmutableList.of(1));
    MorePreconditions.checkNotEmpty(ImmutableList.of());
    Assert.fail();
  }

  @Test(expected = NullPointerException.class)
  public void testNoNullElement() {
    MorePreconditions.checkNoNullElements(ImmutableList.of(1));
    MorePreconditions.checkNoNullElements(Lists.newArrayList(1, null, 1));
    Assert.fail();
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testIndex() {
    MorePreconditions.checkIndex(ImmutableList.of(1), 0);
    MorePreconditions.checkIndex(ImmutableList.of(1), 1);
    Assert.fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIntRange() {
    MorePreconditions.checkArgumentRange(3, 1, 3);
    MorePreconditions.checkArgumentRange(3, 4, 40, "%s必须在4和40之间");
    Assert.fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDoubleRange() {
    MorePreconditions.checkArgumentRange(3.0, 1, 3);
    MorePreconditions.checkArgumentRange(3.0, 4, 40, "%s必须在4和40之间");
    Assert.fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAll() {
    MorePreconditions.checkAllArguments("all", true, true);
    MorePreconditions.checkAllArguments("all", true, false);
    Assert.fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAny() {
    MorePreconditions.checkAnyArguments("all", true, false);
    MorePreconditions.checkAnyArguments("all", false, false);
    Assert.fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInstance() {
    MorePreconditions.checkInstanceOf(List.class, Lists.newArrayList());
    MorePreconditions.checkInstanceOf(List.class, Sets.newHashSet());
    Assert.fail();
  }
}
