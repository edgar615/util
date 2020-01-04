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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;

public class SkipLinkedListTest {

  @Test
  public void testAdd() {
    SkipLinkedList<Integer, Integer> skipList = new SkipLinkedList<>();
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < 1000000; i ++) {
      Integer num = Integer.parseInt(Randoms.randomNumber(6));
      int k = new Random().nextInt(num) + 1;
      map.put(num % k, num % k);
      skipList.add(num % k, num % k);
    }
    Assert.assertEquals(map.size(), skipList.size());
    for (int i : map.keySet()) {
      Assert.assertEquals(map.get(i), skipList.get(i), 0);
    }
  }

  @Test
  public void testRemove() {
    SkipLinkedList<Integer, Integer> skipList = new SkipLinkedList<>();
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < 1000000; i ++) {
      Integer num = Integer.parseInt(Randoms.randomNumber(6));
      int k = new Random().nextInt(num) + 1;
      map.put(num % k, num % k);
      skipList.add(num % k, num % k);
    }
    Assert.assertEquals(map.size(), skipList.size());
    int count = 0;
    for (int i =0; i < 100000; i ++) {
      Integer num = Integer.parseInt(Randoms.randomNumber(6));
      int k = new Random().nextInt(num) + 1;
      Integer value = skipList.remove(num % k);
      if (value != null) {
        count ++;
      }
    }
    Assert.assertEquals(map.size(), skipList.size() + count);
  }

  @Test
  public void testFindRange() {
    SkipLinkedList<Integer, Integer> skipList = new SkipLinkedList<>();
    for (int i = 0; i < 100; i ++) {
      if (i > 25 && i < 35) {

      } else if (i > 40 && i < 50) {

      } else {
        skipList.add(i,i);
      }
    }
    List<Integer> range = skipList.findRange(10, 25);
    System.out.println(range);
    range = skipList.findRange(10, 30);
    System.out.println(range);
    range = skipList.findRange(10, 55);
    System.out.println(range);
    range = skipList.findRange(-1, 5);
    System.out.println(range);
  }
}
