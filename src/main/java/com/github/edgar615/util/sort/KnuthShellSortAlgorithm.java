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

import java.util.List;

/**
 * 使用Knuth增量的希尔排序
 */
public class KnuthShellSortAlgorithm implements SortAlgorithm {

  @Override
  public <T extends Comparable<? super T>> void sort(List<T> list) {
    int len = list.size();
    int[] gapArray = genGap(len);

    for (int s = gapArray.length - 1; s >= 0; s --) {
      int gap = gapArray[s];
      for (int i = gap; i < len; i++) {
        T tmp = list.get(i);
        int j = i;
        while (j >= gap && list.get(j - gap).compareTo(tmp) > 0) {
          list.set(j, list.get(j - gap));
          j -= gap;
        }
        if (i != j) {
          list.set(j, tmp);
        }
      }
    }
  }

  /**
   * 生成gap
   */
  public int[] genGap(int len) {
    int[] knuthArray = new int[len];
    int gapLen = 0;
    for (int n = 1; n < len; n ++) {
      int gap = (int) Math.floor((int) ((Math.pow(3, n) - 1) / 2));
      if (gap > len / 2) {
        break;
      }
      knuthArray[n-1] = gap;
      gapLen ++;
    }
    int[] gapArray = new int[gapLen];
    // 仅仅复制有效步长，也就是n个有效的
    System.arraycopy(knuthArray, 0, gapArray, 0, gapLen);
    return gapArray;
  }

}
