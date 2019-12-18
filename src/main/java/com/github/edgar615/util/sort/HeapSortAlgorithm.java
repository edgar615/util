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

import com.google.common.base.Joiner;
import java.util.List;

public class HeapSortAlgorithm implements SortAlgorithm {

  @Override
  public <T extends Comparable<? super T>> void sort(List<T> list) {

  }

  @Override
  public void sort(int[] array) {
    buildHeap(array, array.length);
    int k = array.length - 1;
    while (k > 1) {
      SortUtils.swap(array, 1, k);
      --k;
      heapify(array, 1, array.length);
    }
  }


  private void buildHeap(int[] array, int n) {
    for (int i = (int) Math.floor(n / 2); i >= 0; i--) {
      heapify(array, i, n);
    }
  }


  private static void heapify(int[] array, int i, int len) {
    while (true) {
      int maxPos = i;
      int left = 2 * i + 1;
      int right = 2 * i + 2;
      if (left < len && array[i] < array[left]) {
        maxPos = left;
      };
      if (right < len && array[maxPos] < array[right]) {
        maxPos = right;
      };
      if (maxPos == i) {
        break;
      };
      SortUtils.swap(array, i, maxPos);
      i = maxPos;
    }
  }
}
