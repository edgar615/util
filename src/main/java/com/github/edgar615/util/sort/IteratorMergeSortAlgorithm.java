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

import java.util.ArrayList;
import java.util.List;

/**
 * 使用迭代法（Bottom-up）实现的归并排序
 *
 * <ul>
 * <li>1. 将序列每相邻两个数字进行归并操作，形成`ceil(n/2)`个序列，排序后每个序列包含两/一个元素</li>
 * <li>2. 若此时序列数不是1个则将上述序列再次归并，形成`ceil(n/4)`个序列，每个序列包含四/三个元素</li>
 * <li>3. 重复步骤2，直到所有元素排序完毕，即序列数为1</li>
 * </ul>
 *
 */
public class IteratorMergeSortAlgorithm extends MergeSortAlgorithm {

  @Override
  public <T extends Comparable<? super T>> void sort(List<T> list) {
    int len = list.size();
    if (len < 2) {
      return;
    }
    List<T> aux = new ArrayList<>(len);
    for (int i = 0; i < len; i ++) {
      aux.add(null);
    }
    for (int subLen = 1; subLen < len; subLen = subLen + subLen) {
      // 相邻归并
      for (int low = 0; low < len; low = low + subLen + subLen) {
        int high = Math.min(low + subLen + subLen - 1, len - 1);
        int mid = (int) Math.floor((subLen + subLen - 1) / 2) + low;
        mid = Math.min(mid, high);
        merge(list, low, mid, high, aux);
      }
    }
  }

}
