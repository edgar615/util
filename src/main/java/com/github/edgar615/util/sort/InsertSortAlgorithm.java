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
 * 插入排序
 *
 * 插入排序是一种最简单直观的排序算法，它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。类似与打扑克牌
 *
 * 算法步骤
 * <ul>
 * <li>将第一待排序序列第一个元素看做一个有序序列，把第二个元素到最后一个元素当成是未排序序列</li>
 * <li>从头到尾依次扫描未排序序列，将扫描到的每个元素插入有序序列的适当位置。（如果待插入的元素与有序序列中的某个元素相等，则将待插入元素插入到相等元素的后面。）</li>
 * </ul>
 *
 * 示例：[20,40,30,10,60,50]
 *
 * 第一次迭代：没有变化[20,40,30,10,60,50]
 *
 * 第二次迭代：30插入到正确位置，[20, 30, 40, 10, 60, 50]
 *
 * 第三次迭代：10插入正确的位置：[10, 20, 30, 40, 60, 50]
 *
 * 第四次迭代：没有变化[10, 20, 30, 40, 60, 50]
 *
 * 第五次迭代：50插入正确的位置：[10, 20, 30, 40, 50, 60]
 *
 * 插入排序和冒泡排序一样，也有一种优化算法，叫做拆半插入。由于在排序算法中，前半部分的有序序列已经排好序，所以我们不必按顺序查找插入点，可以直接看着二分查找法来加快找到插入点
 *
 * <ul>
 *   <li>平均时间复杂度：O(n*n)</li>
 *   <li>最好情况：O(n)</li>
 *   <li>最坏情况：O(n*n)</li>
 *   <li>空间复杂度：O(1)</li>
 *   <li>内存占用：In-place：占用常数内存，不占用额外内存</li>
 *   <li>稳定性：稳定</li>
 * </ul>
 */
public class InsertSortAlgorithm implements SortAlgorithm {

  @Override
  public <T extends Comparable<? super T>> void sort(List<T> list) {
    int len = list.size();
    for (int i = 1; i < len; i++) {
      T insertion = list.get(i);
      int low = binaraySearch(list, i - 1, insertion);
      // j用来定义有序数组
      // 插入位置之后的元素依次向后移动
      for (int j = i; j > low; j--) {
        list.set(j, list.get(j-1));
      }
      list.set(low, insertion);
    }
  }

  /**
   *
   */
  @Override
  public void sort(byte[] array) {
    int len = array.length;
    for (int i = 1; i < len; i++) {
      byte insertion = array[i];
      int low = binaraySearch(array, i - 1, insertion);
      for (int j = i; j > low; j--) {
        array[j] = array[j - 1];
      }
      array[low] = insertion;
    }
  }

  /**
   *
   */
  @Override
  public void sort(char[] array) {
    int len = array.length;
    for (int i = 1; i < len; i++) {
      char insertion = array[i];
      int low = binaraySearch(array, i - 1, insertion);
      for (int j = i; j > low; j--) {
        array[j] = array[j - 1];
      }
      array[low] = insertion;
    }
  }

  /**
   *
   */
  @Override
  public void sort(short[] array) {
    int len = array.length;
    for (int i = 1; i < len; i++) {
      short insertion = array[i];
      int low = binaraySearch(array, i - 1, insertion);
      for (int j = i; j > low; j--) {
        array[j] = array[j - 1];
      }
      array[low] = insertion;
    }
  }

  /**
   *
   */
  @Override
  public void sort(int[] array) {
    int len = array.length;
    for (int i = 1; i < len; i++) {
      int insertion = array[i];
      int low = binaraySearch(array, i - 1, insertion);
      for (int j = i; j > low; j--) {
        array[j] = array[j - 1];
      }
      array[low] = insertion;
    }
  }

  /**
   *
   */
  @Override
  public void sort(long[] array) {
    int len = array.length;
    for (int i = 1; i < len; i++) {
      long insertion = array[i];
      int low = binaraySearch(array, i - 1, insertion);
      for (int j = i; j > low; j--) {
        array[j] = array[j - 1];
      }
      array[low] = insertion;
    }
  }

  /**
   *
   */
  @Override
  public void sort(float[] array) {
    int len = array.length;
    for (int i = 1; i < len; i++) {
      float insertion = array[i];
      int low = binaraySearch(array, i - 1, insertion);
      for (int j = i; j > low; j--) {
        array[j] = array[j - 1];
      }
      array[low] = insertion;
    }
  }

  /**
   *
   */
  @Override
  public void sort(double[] array) {
    int len = array.length;
    for (int i = 1; i < len; i++) {
      double insertion = array[i];
      int low = binaraySearch(array, i - 1, insertion);
      for (int j = i; j > low; j--) {
        array[j] = array[j - 1];
      }
      array[low] = insertion;
    }
  }

  /**
   *
   */
  public int binaraySearch(byte[] array, int high, byte target) {
    int low = 0;
    while (low <= high) {
      int middle = low + (high - low) / 2;
      byte obj = array[middle];
      if (target > obj) {
        low = middle + 1;
      } else {
        high = middle - 1;
      }
    }
    return low;
  }

  /**
   *
   */
  public int binaraySearch(char[] array, int high, char target) {
    int low = 0;
    while (low <= high) {
      int middle = low + (high - low) / 2;
      char obj = array[middle];
      if (target > obj) {
        low = middle + 1;
      } else {
        high = middle - 1;
      }
    }
    return low;
  }

  /**
   *
   */
  public int binaraySearch(short[] array, int high, short target) {
    int low = 0;
    while (low <= high) {
      int middle = low + (high - low) / 2;
      short obj = array[middle];
      if (target > obj) {
        low = middle + 1;
      } else {
        high = middle - 1;
      }
    }
    return low;
  }

  /**
   *
   */
  public int binaraySearch(int[] array, int high, int target) {
    int low = 0;
    while (low <= high) {
      int middle = low + (high - low) / 2;
      int obj = array[middle];
      if (target > obj) {
        low = middle + 1;
      } else {
        high = middle - 1;
      }
    }
    return low;
  }

  /**
   *
   */
  public int binaraySearch(long[] array, int high, long target) {
    int low = 0;
    while (low <= high) {
      int middle = low + (high - low) / 2;
      long obj = array[middle];
      if (target > obj) {
        low = middle + 1;
      } else {
        high = middle - 1;
      }
    }
    return low;
  }

  /**
   *
   */
  public int binaraySearch(float[] array, int high, float target) {
    int low = 0;
    while (low <= high) {
      int middle = low + (high - low) / 2;
      float obj = array[middle];
      if (target > obj) {
        low = middle + 1;
      } else {
        high = middle - 1;
      }
    }
    return low;
  }

  /**
   *
   */
  public int binaraySearch(double[] array, int high, double target) {
    int low = 0;
    while (low <= high) {
      int middle = low + (high - low) / 2;
      double obj = array[middle];
      if (target > obj) {
        low = middle + 1;
      } else {
        high = middle - 1;
      }
    }
    return low;
  }

  private <T extends Comparable<? super T>> int binaraySearch(List<T> list, int high, T target) {
    int low = 0;
    while (low <= high) {
      int middle = low + (high - low) / 2;
      T obj = list.get(middle);
      if (target.compareTo(obj) > 0) {
        low = middle + 1;
      } else {
        high = middle - 1;
      }
    }
    return low;
  }
}
