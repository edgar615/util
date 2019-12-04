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
public interface SortAlgorithm {

  <T extends Comparable<? super T>> void sort(List<T> list);

  /**
   * 冒泡排序
   */
  static SortAlgorithm bubble() {
    return new BubbleSortAlgorithm();
  }

  /**
   * 插入排序
   */
  static SortAlgorithm insert() {
    return new InsertSortAlgorithm();
  }


  /**
   * 选择排序
   */
  static SortAlgorithm selection() {
    return new SelectionSortAlgorithm();
  }

  /**
   * 使用默认增量的希尔排序
   */
  static SortAlgorithm shell() {
    return new ShellSortAlgorithm();
  }

  /**
   * 使用Sedgewick增量的希尔排序
   */
  static SortAlgorithm shellSedgewick() {
    return new SedgewickShellSortAlgorithm();
  }

  /**
   * 使用Hibbard增量的希尔排序
   */
  static SortAlgorithm shellHibbard() {
    return new HibbardShellSortAlgorithm();
  }

  /**
   * 使用Knuth增量的希尔排序
   */
  static SortAlgorithm shellKnuth() {
    return new KnuthShellSortAlgorithm();
  }
}
