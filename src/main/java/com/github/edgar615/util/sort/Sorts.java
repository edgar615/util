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

package com.github.edgar615.util.sort;

import java.util.List;

/**
 * 排序算法
 */
public interface Sorts {

  <T extends Comparable<? super T>> void sort(List<T> list);

  /**
   * 冒泡排序
   */
  static <T extends Comparable<? super T>> void bubble(List<T> list) {
    new BubbleSortAlgorithm().sort(list);
  }

  /**
   * 插入排序
   */
  static <T extends Comparable<? super T>> void insert(List<T> list) {
    new InsertSortAlgorithm().sort(list);
  }


  /**
   * 选择排序
   */
  static <T extends Comparable<? super T>> void selection(List<T> list) {
    new SelectionSortAlgorithm().sort(list);
  }

  /**
   * 使用默认增量的希尔排序
   */
  static <T extends Comparable<? super T>> void shell(List<T> list) {
    new ShellSortAlgorithm().sort(list);
  }

  /**
   * 使用Sedgewick增量的希尔排序
   */
  static <T extends Comparable<? super T>> void shellSedgewick(List<T> list) {
    new SedgewickShellSortAlgorithm().sort(list);
  }

  /**
   * 使用Hibbard增量的希尔排序
   */
  static <T extends Comparable<? super T>> void shellHibbard(List<T> list) {
    new HibbardShellSortAlgorithm().sort(list);
  }

  /**
   * 使用Knuth增量的希尔排序
   */
  static <T extends Comparable<? super T>> void shellKnuth(List<T> list) {
    new KnuthShellSortAlgorithm().sort(list);
  }

  /**
   * 递归方式的归并排序
   */
  static <T extends Comparable<? super T>> void merge(List<T> list) {
    new MergeSortAlgorithm().sort(list);
  }

  /**
   * 迭代方式的归并排序
   */
  static <T extends Comparable<? super T>> void mergeIterator(List<T> list) {
    new IteratorMergeSortAlgorithm().sort(list);
  }
}
