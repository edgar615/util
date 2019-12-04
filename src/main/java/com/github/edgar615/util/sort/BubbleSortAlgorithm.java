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
 * 冒泡排序（Bubble Sort）
 *
 * 冒泡排序是一种简单直观的排序算法。它重复地走访过要排序的数列，一次比较两个元素，如果他们的顺序错误就把他们交换过来。
 *
 * 走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成
 *
 * 算法步骤
 * <ul>
 * <li>比较相邻的元素。如果第一个比第二个大，就交换他们两个</li>
 * <li>对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数</li>
 * <li>针对所有的元素重复以上的步骤，除了最后一个</li>
 * <li>持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较</li>
 * </ul>
 *
 * 仔细观察上面的流程会发现即使数组在某次循环已经排序完成，依然会继续执行循环。为了优化这种情况，我们可以增加一个标记来表明此次循环是否进行了交换，如果没有交换说明排序已经完成
 *
 * 示例：[20,40,30,10,60,50]
 *
 * 第一次迭代：[20,30,10,40,50,60]
 *
 * 第二次迭代：[20,10,30,40,50,60]
 *
 * 第三次迭代：[10,20,30,40,50,60]
 *
 * 第四次迭代：没有变化完成排序，[10,20,30,40,50,60]
 *
 * <ul>
 *   <li>平均时间复杂度：O(n*n)</li>
 *   <li>最好情况：O(n)</li>
 *   <li>最坏情况：O(n*n)</li>
 *   <li>空间复杂度：O(1)</li>
 *   <li>内存占用：In-place：占用常数内存，不占用额外内存</li>
 *   <li>稳定性：稳定</li>
 * </ul>
 *
 *
 */
class BubbleSortAlgorithm implements SortAlgorithm {

  @Override
  public <T extends Comparable<? super T>> void sort(List<T> list) {
    int len = list.size();
    for (int i = 1; i < len; i++) {
      boolean complete = true;
      for (int j = 0; j < len - 1; j++) {
        T prev = list.get(j);
        T next = list.get(j + 1);
        if (prev.compareTo(next) > 0) {
          T temp = prev;
          list.set(j, next);
          list.set(j + 1, temp);
          complete = false;
        }
      }
      if (complete) {
        break;
      }
    }
  }

}
