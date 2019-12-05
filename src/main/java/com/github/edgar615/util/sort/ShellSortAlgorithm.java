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
 * 希尔排序
 *
 * 希尔排序，也称递减增量排序算法，是插入排序的一种更高效的改进版本。但希尔排序是非稳定排序算法。
 *
 * 希尔排序是基于插入排序的以下两点性质而提出改进方法的：
 *
 * <ul>
 *   <li>插入排序在对几乎已经排好序的数据操作时，效率高，即可以达到线性排序的效率</li>
 *   <li>但插入排序一般来说是低效的，因为插入排序每次只能将数据移动一位</li>
 * </ul>
 *
 * 希尔排序的基本思想是：先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，待整个序列中的记录”基本有序”时，再对全体记录进行依次直接插入排序。
 *
 * 算法步骤
 * <ul>
 * <li>选择一个增量序列 t1，t2，……，tk，其中 ti > tj, tk = 1；</li>
 * <li>按增量序列个数 k，对序列进行 k 趟排序</li>
 * <li>每趟排序，根据对应的增量 ti，将待排序列分割成若干长度为 m 的子序列，分别对各子表进行直接插入排序。仅增量因子为 1 时，整个序列作为一个表来处理，表长度即为整个序列的长度</li>
 * </ul>
 *
 * 希尔排序与选择排序最主要的差别在于引入了一个step变量(指步长，一些书上喜欢用gap或h来表示)，这使得每次交换元素位置，都可以使该元素向其最终位置跨一大步。
 * 随着排序的进行，数组越来越接近有序，步长也越来越小，直到step=1，此时希尔排序就变得跟插入排序一模一样了，但此时数组已经几乎完全有序了
 *
 *
 * 示例：[20,40,30,10,60,50]
 *
 * n/3向下取整+1=3,拆为gap=3的子序列 [20,10] [40,60] [30,50]
 *
 * 分别插入排序[10,20] [40,60] [30,50] => [10, 40, 30, 20, 60, 50]
 *
 * gap=gap/3向下取整+1=2，拆为gap=2的子序列，[10,30,60] [40,20,50]
 *
 * 分别插入排序[10,30,60] [20,40,50]=> [10, 20, 30, 40, 60, 50]
 *
 * gap=gap/3向下取整+1=1，得到整个序列[10, 20, 30, 40, 60, 50]
 *
 * 插入排序：[10, 20, 30, 40, 50, 60]
 *
 * <b>希尔排序的效率取决于gap的选择</b>
 *
 * <ul>
 *   <li>Shell增量：N/2,N/4,N/8...1</li>
 *   <li>Hibbard增量：1,3,7,15...,2^k-1</li>
 *   <li>Knuth增量：1,4,13,40,...,(3^k - 1)/2</li>
 *   <li>Sedgewick增量：1,5,19,41,109...</li>
 * </ul>
 *
 * <ul>
 *   <li>平均时间复杂度：O(n*log(n))</li>
 *   <li>最好情况：O(n*log(2n))</li>
 *   <li>最坏情况：O(n*log(2n))</li>
 *   <li>空间复杂度：O(1)</li>
 *   <li>内存占用：In-place：占用常数内存，不占用额外内存</li>
 *   <li>稳定性：不稳定</li>
 * </ul>
 */
public class ShellSortAlgorithm implements SortAlgorithm {

  @Override
  public <T extends Comparable<? super T>> void sort(List<T> list) {
    int len = list.size();
    int gap = (int) Math.floor(len / 2);

    while (gap > 0) {
      for (int i = gap; i < len; i++) {
        T tmp = list.get(i);
        int j = i - gap;
        while (j >= 0 && list.get(j).compareTo(tmp) > 0) {
          list.set(j + gap, list.get(j));
          j -= gap;
        }
        list.set(j + gap, tmp);
      }
      if (gap == 1) {
        gap = 0;
      } else {
        gap = (int) Math.floor(gap / 2);
      }
    }
  }
}
