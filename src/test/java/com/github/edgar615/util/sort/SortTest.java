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
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;

public class SortTest {

  @Test
  public void testBubble() {
//    List<Integer> list = Lists.newArrayList(20,40,30,10,60,50);
    List<Integer> list = Lists
        .newArrayList(45, 84, 77, 83, 55, 49, 91, 64, 91, 5, 37, 31, 70, 38, 51);
    Sorts.bubble(list);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(list));
  }

  @Test
  public void testBubble2() {
    List<Integer> list = fillList();
    Sorts.bubble(list);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(list));

    int[] array = fillArray();
    Sorts.bubble(array);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(arrayToList(array)));
  }

  @Test
  public void testInsert() {
    List<Integer> list = fillList();
    Sorts.insert(list);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(list));

    int[] array = fillArray();
    Sorts.insert(array);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(arrayToList(array)));
  }

  @Test
  public void testSelection() {
    List<Integer> list = fillList();
    Sorts.selection(list);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(list));

    int[] array = fillArray();
    Sorts.selection(array);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(arrayToList(array)));
  }

  @Test
  public void testShell() {
    List<Integer> list = fillList();
    Sorts.shell(list);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(list));

    int[] array = fillArray();
    Sorts.shell(array);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(arrayToList(array)));
  }

  @Test
  public void testShellSedgewick() {
    List<Integer> list = fillList();
    Sorts.shellSedgewick(list);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(list));

    int[] array = fillArray();
    Sorts.shellHibbard(array);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(arrayToList(array)));
  }

  @Test
  public void testShellHibbard() {
    List<Integer> list = fillList();
    Sorts.shellHibbard(list);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(list));

    int[] array = fillArray();
    Sorts.shellHibbard(array);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(arrayToList(array)));
  }

  @Test
  public void testShellKnuth() {
    List<Integer> list = fillList();
    Sorts.shellKnuth(list);
    System.out.println(list);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(list));

    int[] array = fillArray();
    Sorts.shellKnuth(array);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(arrayToList(array)));
  }

  @Test
  public void testMerge() {
    List<Integer> list = fillList();
    Sorts.merge(list);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(list));

    int[] array = fillArray();
    Sorts.merge(array);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(arrayToList(array)));
  }

  @Test
  public void testMergeIterator() {
    List<Integer> list = fillList();
//    List<Integer> list = Lists.newArrayList(45,84,77,83,55,49,91,64,91,5,37,31,70,38,51,14,34);
    Sorts.mergeIterator(list);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(list));

    int[] array = fillArray();
    Sorts.mergeIterator(array);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(arrayToList(array)));
  }

  private List<Integer> fillList() {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < 884; i++) {
      int len = (new Random()).nextInt(4) + 1;
      list.add(Integer.parseInt(Randoms.randomNumber(len)));
    }
    return list;
  }

  private List<Integer> arrayToList(int[] array) {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < array.length; i++) {
      list.add(array[i]);
    }
    return list;
  }

  private int[] fillArray() {
    int[] array = new int[884];
    for (int i = 0; i < 884; i++) {
      int len = (new Random()).nextInt(4) + 1;
      array[i] = Integer.parseInt(Randoms.randomNumber(len));
    }
    return array;
  }

}
