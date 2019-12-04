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

package com.github.edgar615.util.sort;

import com.github.edgar615.util.base.Randoms;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;

public class SortTest {

  @Test
  public void testBubble() {
//    List<Integer> list = Lists.newArrayList(20,40,30,10,60,50);
    List<Integer> list = Lists.newArrayList(45,84,77,83,55,49,91,64,91,5,37,31,70,38,51);
    SortAlgorithm.bubble().sort(list);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(list));
  }

  @Test
  public void testBubble2() {
    List<Integer> list = fillArray();
    SortAlgorithm.bubble().sort(list);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(list));
  }

  @Test
  public void testInsert() {
    List<Integer> list = fillArray();
    SortAlgorithm.insert().sort(list);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(list));
  }

  @Test
  public void testSelection() {
    List<Integer> list = fillArray();
    SortAlgorithm.selection().sort(list);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(list));
  }

  @Test
  public void testShell() {
    List<Integer> list = fillArray();
    SortAlgorithm.shell().sort(list);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(list));
  }

  @Test
  public void testShellSedgewick() {
    List<Integer> list = fillArray();
    SortAlgorithm.shellSedgewick().sort(list);
    System.out.println(list);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(list));
  }

  @Test
  public void testShellHibbard() {
    List<Integer> list = fillArray();
    SortAlgorithm.shellHibbard().sort(list);
    System.out.println(list);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(list));
  }

  @Test
  public void testShellKnuth() {
    List<Integer> list = fillArray();
    SortAlgorithm.shellKnuth().sort(list);
    System.out.println(list);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(list));
  }

  private List<Integer> fillArray() {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < 10000; i ++){
      int len = (new Random()).nextInt(4) + 1;
      list.add(Integer.parseInt(Randoms.randomNumber(len)));
    }
    return list;
  }

}
