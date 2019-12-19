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
    new BubbleSortAlgorithm().sort(list);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(list));
  }

  @Test
  public void testBubble2() {
    List<Integer> list = fillList();
    new BubbleSortAlgorithm().sort(list);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(list));

    int[] array = fillArray();
    new BubbleSortAlgorithm().sort(array);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(arrayToList(array)));
  }

  @Test
  public void testInsert() {
    List<Integer> list = fillList();
    new InsertSortAlgorithm().sort(list);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(list));

    int[] array = fillArray();
    new InsertSortAlgorithm().sort(array);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(arrayToList(array)));
  }

  @Test
  public void testSelection() {
    List<Integer> list = fillList();
    new SelectionSortAlgorithm().sort(list);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(list));

    int[] array = fillArray();
    new SelectionSortAlgorithm().sort(array);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(arrayToList(array)));
  }

  @Test
  public void testShell() {
    List<Integer> list = fillList();
    new ShellSortAlgorithm().sort(list);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(list));

    int[] array = fillArray();
    new ShellSortAlgorithm().sort(array);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(arrayToList(array)));
  }

  @Test
  public void testShellSedgewick() {
    List<Integer> list = fillList();
    new SedgewickShellSortAlgorithm().sort(list);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(list));

    int[] array = fillArray();
    new SedgewickShellSortAlgorithm().sort(array);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(arrayToList(array)));
  }

  @Test
  public void testShellHibbard() {
    List<Integer> list = fillList();
    new HibbardShellSortAlgorithm().sort(list);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(list));

    int[] array = fillArray();
    new HibbardShellSortAlgorithm().sort(array);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(arrayToList(array)));
  }

  @Test
  public void testShellKnuth() {
    List<Integer> list = fillList();
    new KnuthShellSortAlgorithm().sort(list);
    System.out.println(list);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(list));

    int[] array = fillArray();
    new KnuthShellSortAlgorithm().sort(array);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(arrayToList(array)));
  }

  @Test
  public void testMerge() {
    List<Integer> list = fillList();
    new MergeSortAlgorithm().sort(list);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(list));

    int[] array = fillArray();
    new MergeSortAlgorithm().sort(array);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(arrayToList(array)));
  }

  @Test
  public void testMergeIterator() {
    List<Integer> list = fillList();
//    List<Integer> list = Lists.newArrayList(45,84,77,83,55,49,91,64,91,5,37,31,70,38,51,14,34);
    new IteratorMergeSortAlgorithm().sort(list);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(list));

    int[] array = fillArray();
    new IteratorMergeSortAlgorithm().sort(array);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(arrayToList(array)));
  }

  @Test
  public void testLomutoQuick() {
    List<Integer> list = fillList();
//    List<Integer> list = Lists.newArrayList(45,84,77,83,55,49,91,5,37,31,70,38,51,14,34);
    new LomutoQuickSortAlgorithm().sort(list);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(list));
    System.out.println(list);

    int[] array = fillArray();
    new LomutoQuickSortAlgorithm().sort(array);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(arrayToList(array)));
  }

  @Test
  public void testHoareQuick() {
    List<Integer> list = fillList();
    new HoareQuickSortAlgorithm().sort(list);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(list));

    int[] array = fillArray();
    new HoareQuickSortAlgorithm().sort(array);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(arrayToList(array)));
  }

  @Test
  public void testMedianOfThreeQuick() {
    List<Integer> list = fillList();
    new MedianOfThreeQuickSortAlgorithm().sort(list);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(list));

    int[] array = fillArray();
    new MedianOfThreeQuickSortAlgorithm().sort(array);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(arrayToList(array)));
  }

  @Test
  public void testQuick() {
    List<Integer> list = fillList();
//    List<Integer> list = Lists.newArrayList(5, 4, 7, 8, 2, 7, 8, 5, 6, 3);
    new QuickSortAlgorithm().sort(list);
    System.out.println(list);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(list));

    int[] array = fillArray();
    new QuickSortAlgorithm().sort(array);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(arrayToList(array)));
  }

  @Test
  public void testHeap() {
    List<Integer> list = fillList();
//    List<Integer> list = Lists.newArrayList(5, 4, 7, 8, 2, 7, 8, 5, 6, 3);
    new HeapSortAlgorithm().sort(list);
    System.out.println(list);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(list));

    int[] array = fillArray();
//    int[] array = new int[] {7,5,19,8,4,1,20,13,16};
    new HeapSortAlgorithm().sort(array);
    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(arrayToList(array)));
  }

  @Test
  public void testCounting() {
//    List<Integer> list = fillList();
////    List<Integer> list = Lists.newArrayList(5, 4, 7, 8, 2, 7, 8, 5, 6, 3);
//    new HeapSortAlgorithm().sort(list);
//    System.out.println(list);
//    Assert.assertTrue(Ordering.<Integer>natural().isOrdered(list));

    int[] array = fillArray();
//    int[] array = new int[] {2, 8, 5, 1, 10, 5, 9, 9, 3, 5, 6, 6, 2, 8, 2};
    new CountingSortAlgorithm().sort(array);
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
