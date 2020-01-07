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

package com.github.edgar615.util.collection;

import com.github.edgar615.util.base.Randoms;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;

public class SkipListTest {

  @Test
  public void testAdd() {
    LinkedSkipList<Integer, Integer> skipList = new LinkedSkipList<>();
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < 1000; i ++) {
      Integer num = Integer.parseInt(Randoms.randomNumber(6));
      int k = new Random().nextInt(num) + 1;
      map.put(num % k, num % k);
      skipList.add(num % k, num % k);
    }
    System.out.println(map.size());
    System.out.println(skipList.size());
    Assert.assertEquals(map.size(), skipList.size());
    for (int i : map.keySet()) {
      Assert.assertEquals(map.get(i), skipList.get(i), 0);
    }
  }

  @Test
  public void testRemove() {
    LinkedSkipList<Integer, Integer> skipList = new LinkedSkipList<>();
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < 1000; i ++) {
      Integer num = Integer.parseInt(Randoms.randomNumber(6));
      int k = new Random().nextInt(num) + 1;
      map.put(num % k, num % k);
      skipList.add(num % k, num % k);
    }
    Assert.assertEquals(map.size(), skipList.size());
    List<Integer> removed = new ArrayList<>();
    for (int i =0; i < 1000; i ++) {
      Integer num = Integer.parseInt(Randoms.randomNumber(6));
      int k = new Random().nextInt(num) + 1;
      Integer value = skipList.remove(num % k);
      if (value != null) {
        removed.add(value);
      }
    }
    Assert.assertEquals(map.size(), skipList.size() + removed.size());
    for (int i : removed) {
      Assert.assertNull(skipList.get(i));
    }
//    List<Integer> list = new ArrayList<>(map.keySet());
//    for (int i =0; i < map.size(); i ++) {
//      if (!removed.contains(list.get(i))) {
//        System.out.println(i);
//        Assert.assertEquals(map.get(list.get(i)), skipList.get(list.get(i)), 0);
//      }
//    }
  }

  @Test
  public void testFindRange() {
    LinkedSkipList<Integer, Integer> skipList = new LinkedSkipList<>();
    for (int i = 0; i < 100; i ++) {
      if (i > 25 && i < 35) {

      } else if (i > 40 && i < 50) {

      } else {
        skipList.add(i,i);
      }
    }

    List<Integer> range = skipList.findRange(10, 25);
    System.out.println(range);
    Assert.assertEquals(16, range.size());
    Assert.assertEquals(10, range.get(0), 0);
    Assert.assertEquals(25, range.get(15), 0);
    System.out.println(range);
    range = skipList.findRange(10, 30);
    Assert.assertEquals(16, range.size());
    Assert.assertEquals(10, range.get(0), 0);
    Assert.assertEquals(25, range.get(15), 0);
    System.out.println(range);
    range = skipList.findRange(10, 55);
    Assert.assertEquals(28, range.size());
    Assert.assertEquals(10, range.get(0), 0);
    Assert.assertEquals(25, range.get(15), 0);
    Assert.assertEquals(50, range.get(22), 0);
    Assert.assertEquals(55, range.get(27), 0);
    System.out.println(range);
    range = skipList.findRange(-1, 5);
    Assert.assertEquals(6, range.size());
    Assert.assertEquals(0, range.get(0), 0);
    Assert.assertEquals(5, range.get(5), 0);
    System.out.println(range);

    range = skipList.findRange(200, 300);
    System.out.println(range);
    Assert.assertEquals(0, range.size());
  }

  @Test
  public void testAddArray() {
    ArraySkipList<Integer, Integer> skipList = new ArraySkipList<>();
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < 1000; i ++) {
      Integer num = Integer.parseInt(Randoms.randomNumber(6));
      int k = new Random().nextInt(num) + 1;
      map.put(num % k, num);
      skipList.add(num % k, num);
      System.out.println(i);
    }
    System.out.println(map.size());
    System.out.println(skipList.size());
    Assert.assertEquals(map.size(), skipList.size());
    for (int i : map.keySet()) {
      Assert.assertEquals(map.get(i), skipList.get(i), 0);
    }
  }

  @Test
  public void testAddArray2() {
    ArraySkipList<Integer, Integer> skipList = new ArraySkipList<>();
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < 1000; i ++) {
      Integer num = Integer.parseInt(Randoms.randomNumber(2));
      skipList.add(num, num);
      list.add(num);
    }
    skipList.printAll();
    for (int i =0; i < 5; i ++) {
      skipList.remove(list.get(i));
    }

    for (int i =0; i < 5; i ++) {
      System.out.println(list.get(i) + ":" + skipList.get(list.get(i)));
    }
  }

  @Test
  public void testRemoveArray() {
    ArraySkipList<Integer, Integer> skipList = new ArraySkipList<>();
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < 1000; i ++) {
      Integer num = Integer.parseInt(Randoms.randomNumber(6));
      int k = new Random().nextInt(num) + 1;
      map.put(num % k, num % k);
      skipList.add(num % k, num % k);
    }
//    Assert.assertEquals(map.size(), skipList.size());
    List<Integer> removed = new ArrayList<>();
    for (int i =0; i < 1000; i ++) {
      Integer num = Integer.parseInt(Randoms.randomNumber(6));
      int k = new Random().nextInt(num) + 1;
      Integer value = skipList.remove(num % k);
      if (value != null) {
        removed.add(value);
      }
    }
    Assert.assertEquals(map.size(), skipList.size() + removed.size());
    for (int i : removed) {
      Assert.assertNull(skipList.get(i));
    }
  }

  @Test
  public void testFindRangeArray() {
    ArraySkipList<Integer, Integer> skipList = new ArraySkipList<>();
    for (int i = 0; i < 100; i ++) {
      if (i > 25 && i < 35) {

      } else if (i > 40 && i < 50) {

      } else {
        skipList.add(i,i);
      }
    }

    List<Integer> range = skipList.findRange(10, 25);
    System.out.println(range);
    Assert.assertEquals(16, range.size());
    Assert.assertEquals(10, range.get(0), 0);
    Assert.assertEquals(25, range.get(15), 0);
    System.out.println(range);
    range = skipList.findRange(10, 30);
    Assert.assertEquals(16, range.size());
    Assert.assertEquals(10, range.get(0), 0);
    Assert.assertEquals(25, range.get(15), 0);
    System.out.println(range);
    range = skipList.findRange(10, 55);
    Assert.assertEquals(28, range.size());
    Assert.assertEquals(10, range.get(0), 0);
    Assert.assertEquals(25, range.get(15), 0);
    Assert.assertEquals(50, range.get(22), 0);
    Assert.assertEquals(55, range.get(27), 0);
    System.out.println(range);
    range = skipList.findRange(-1, 5);
    Assert.assertEquals(6, range.size());
    Assert.assertEquals(0, range.get(0), 0);
    Assert.assertEquals(5, range.get(5), 0);
    System.out.println(range);

    range = skipList.findRange(200, 300);
    System.out.println(range);
    Assert.assertEquals(0, range.size());
  }

}
