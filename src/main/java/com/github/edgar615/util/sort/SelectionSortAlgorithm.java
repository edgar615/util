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
 * 选择排序
 *
 * 选择排序是一种简单直观的排序算法，无论什么数据进去都是 O(n²) 的时间复杂度。所以用到它的时候，数据规模越小越好。唯一的好处可能就是不占用额外的内存空间。
 *
 * 算法步骤
 * <ul>
 * <li>首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置</li>
 * <li>再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾</li>
 * <li>重复第二步，直到所有元素均排序完毕</li>
 * </ul>
 *
 * 由于选择排序一次能确定一个元素的位置，所以选择排序需要循环size-1次。 示例：[20,40,30,10,60,50]
 *
 * 第一次迭代：找到最小的10,[10,40,30,20,60,50]
 *
 * 第二次迭代：找到最小的20，[10,20,30,40,60,50]
 *
 * 第三次迭代：找到最小的30，[10,20,30,40,60,50]
 *
 * 第四次迭代：找到最小的40，[10,20,30,40,60,50]
 *
 * 第五次迭代：找到最小的50，[10,20,30,40,50,60]
 *
 * 第五次迭代：找到最小的60，排序完成，[10,20,30,40,50,60]
 *
 * <ul>
 * <li>平均时间复杂度：O(n*n)</li>
 * <li>最好情况：O(n*n)</li>
 * <li>最坏情况：O(n*n)</li>
 * <li>空间复杂度：O(1)</li>
 * <li>内存占用：In-place：占用常数内存，不占用额外内存</li>
 * <li>稳定性：不稳定</li>
 * </ul>
 */
public class SelectionSortAlgorithm implements SortAlgorithm {

  @Override
  public <T extends Comparable<? super T>> void sort(List<T> list) {
    int len = list.size();
    for (int i = 0; i < len; i++) {
      int minIndex = i;
      for (int j = i + 1; j < len; j++) {
        T prev = list.get(minIndex);
        T next = list.get(j);
        if (prev.compareTo(next) > 0) {
          minIndex = j;
        }
      }
      if (minIndex != i) {
        T temp = list.get(minIndex);
        list.set(minIndex, list.get(i));
        list.set(i, temp);
      }
    }
  }
  /**
   *
   */
  @Override
  public void sort(byte[] array) {
    int len = array.length;
    for (int i = 0; i < len; i++) {
      int minIndex = i;
      for (int j = i + 1; j < len; j++) {
        byte prev = array[minIndex];
        byte next = array[j];
        if (prev > next) {
          minIndex = j;
        }
      }
      if (minIndex != i) {
        byte temp = array[minIndex];
        array[minIndex] = array[i];
        array[i] = temp;
      }
    }
  }

  /**
   *
   */
  @Override
  public void sort(char[] array) {
    int len = array.length;
    for (int i = 0; i < len; i++) {
      int minIndex = i;
      for (int j = i + 1; j < len; j++) {
        char prev = array[minIndex];
        char next = array[j];
        if (prev > next) {
          minIndex = j;
        }
      }
      if (minIndex != i) {
        char temp = array[minIndex];
        array[minIndex] = array[i];
        array[i] = temp;
      }
    }
  }

  /**
   *
   */
  @Override
  public void sort(short[] array) {
    int len = array.length;
    for (int i = 0; i < len; i++) {
      int minIndex = i;
      for (int j = i + 1; j < len; j++) {
        short prev = array[minIndex];
        short next = array[j];
        if (prev > next) {
          minIndex = j;
        }
      }
      if (minIndex != i) {
        short temp = array[minIndex];
        array[minIndex] = array[i];
        array[i] = temp;
      }
    }
  }

  /**
   *
   */
  @Override
  public void sort(int[] array) {
    int len = array.length;
    for (int i = 0; i < len; i++) {
      int minIndex = i;
      for (int j = i + 1; j < len; j++) {
        int prev = array[minIndex];
        int next = array[j];
        if (prev > next) {
          minIndex = j;
        }
      }
      if (minIndex != i) {
        int temp = array[minIndex];
        array[minIndex] = array[i];
        array[i] = temp;
      }
    }
  }

  /**
   *
   */
  @Override
  public void sort(long[] array) {
    int len = array.length;
    for (int i = 0; i < len; i++) {
      int minIndex = i;
      for (int j = i + 1; j < len; j++) {
        long prev = array[minIndex];
        long next = array[j];
        if (prev > next) {
          minIndex = j;
        }
      }
      if (minIndex != i) {
        long temp = array[minIndex];
        array[minIndex] = array[i];
        array[i] = temp;
      }
    }
  }

  /**
   *
   */
  @Override
  public void sort(float[] array) {
    int len = array.length;
    for (int i = 0; i < len; i++) {
      int minIndex = i;
      for (int j = i + 1; j < len; j++) {
        float prev = array[minIndex];
        float next = array[j];
        if (prev > next) {
          minIndex = j;
        }
      }
      if (minIndex != i) {
        float temp = array[minIndex];
        array[minIndex] = array[i];
        array[i] = temp;
      }
    }
  }

  /**
   *
   */
  @Override
  public void sort(double[] array) {
    int len = array.length;
    for (int i = 0; i < len; i++) {
      int minIndex = i;
      for (int j = i + 1; j < len; j++) {
        double prev = array[minIndex];
        double next = array[j];
        if (prev > next) {
          minIndex = j;
        }
      }
      if (minIndex != i) {
        double temp = array[minIndex];
        array[minIndex] = array[i];
        array[i] = temp;
      }
    }
  }
}
