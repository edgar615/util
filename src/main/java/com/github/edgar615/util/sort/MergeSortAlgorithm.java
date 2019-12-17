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
 * 归并排序（英语：Merge sort，或mergesort），
 *
 * 归并算法是创建在归并操作上的一种有效的排序算法，效率为`O(n\log n)`。1945年由约翰·冯·诺伊曼首次提出。该算法是采用分治法（Divide and
 * Conquer）的一个非常典型的应用，且各层分治递归可以同时进行
 *
 * 归并操作（merge），也叫归并算法，指的是将两个已经排序的序列合并成一个序列的操作。归并排序算法依赖归并操作。
 *
 * 归并操作有两种实现方式
 *
 * 递归法（Top-down）和 迭代法（Bottom-up）
 *
 * 这个类是递归法的实现
 *
 * <ul>
 * <li>1. 申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列</li>
 * <li>2. 设定两个指针，最初位置分别为两个已经排序序列的起始位置</li>
 * <li>3. 比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置</li>
 * <li>4. 重复步骤3直到某一指针到达序列尾</li>
 * <li>5. 将另一序列剩下的所有元素直接复制到合并序列尾</li>
 * </ul>
 *
 * <ul>
 * <li>平均时间复杂度：O(n log n)</li>
 * <li>最好情况：O(n log n)</li>
 * <li>最坏情况：O(n log n)</li>
 * <li>空间复杂度：O(n)</li>
 * <li>内存占用：Out-place：占用额外内存</li>
 * <li>稳定性：稳定</li>
 * </ul>
 */
public class MergeSortAlgorithm implements SortAlgorithm {

  @Override
  public <T extends Comparable<? super T>> void sort(List<T> list) {
    int len = list.size();
    if (len < 2) {
      return;
    }
    List<T> aux = new ArrayList<>(len);
    for (int i = 0; i < len; i++) {
      aux.add(null);
    }
    mergeSort(list, 0, len - 1, aux);
  }

  @Override
  public void sort(byte[] array) {
    int len = array.length;
    if (len < 2) {
      return;
    }
    byte[] aux = new byte[len];
    mergeSort(array, 0, len - 1, aux);
  }

  @Override
  public void sort(char[] array) {
    int len = array.length;
    if (len < 2) {
      return;
    }
    char[] aux = new char[len];
    mergeSort(array, 0, len - 1, aux);
  }

  @Override
  public void sort(short[] array) {
    int len = array.length;
    if (len < 2) {
      return;
    }
    short[] aux = new short[len];
    mergeSort(array, 0, len - 1, aux);
  }


  @Override
  public void sort(int[] array) {
    int len = array.length;
    if (len < 2) {
      return;
    }
    int[] aux = new int[len];
    mergeSort(array, 0, len - 1, aux);
  }


  @Override
  public void sort(long[] array) {
    int len = array.length;
    if (len < 2) {
      return;
    }
    long[] aux = new long[len];
    mergeSort(array, 0, len - 1, aux);
  }


  @Override
  public void sort(float[] array) {
    int len = array.length;
    if (len < 2) {
      return;
    }
    float[] aux = new float[len];
    mergeSort(array, 0, len - 1, aux);
  }


  @Override
  public void sort(double[] array) {
    int len = array.length;
    if (len < 2) {
      return;
    }
    double[] aux = new double[len];
    mergeSort(array, 0, len - 1, aux);
  }

  private <T extends Comparable<? super T>> void mergeSort(List<T> list, int low, int high,
      List<T> aux) {
    // 终止递归的条件
    if (low == high) {
      return;
    }
    int mid = (int) Math.floor((high - low) / 2) + low;
    mergeSort(list, low, mid, aux);  // 对左半边递归
    mergeSort(list, mid + 1, high, aux);  // 对右半边递归
    merge(list, low, mid, high, aux);  // 单趟合并
  }

  private void mergeSort(byte[] array, int low, int high, byte[] aux) {
    // 终止递归的条件
    if (low == high) {
      return;
    }
    int mid = (int) Math.floor((high - low) / 2) + low;
    mergeSort(array, low, mid, aux);  // 对左半边递归
    mergeSort(array, mid + 1, high, aux);  // 对右半边递归
    merge(array, low, mid, high, aux);  // 单趟合并
  }

  private void mergeSort(char[] array, int low, int high, char[] aux) {
    // 终止递归的条件
    if (low == high) {
      return;
    }
    int mid = (int) Math.floor((high - low) / 2) + low;
    mergeSort(array, low, mid, aux);  // 对左半边递归
    mergeSort(array, mid + 1, high, aux);  // 对右半边递归
    merge(array, low, mid, high, aux);  // 单趟合并
  }

  private void mergeSort(short[] array, int low, int high, short[] aux) {
    // 终止递归的条件
    if (low == high) {
      return;
    }
    int mid = (int) Math.floor((high - low) / 2) + low;
    mergeSort(array, low, mid, aux);  // 对左半边递归
    mergeSort(array, mid + 1, high, aux);  // 对右半边递归
    merge(array, low, mid, high, aux);  // 单趟合并
  }

  private void mergeSort(int[] array, int low, int high, int[] aux) {
    // 终止递归的条件
    if (low == high) {
      return;
    }
    int mid = (int) Math.floor((high - low) / 2) + low;
    mergeSort(array, low, mid, aux);  // 对左半边递归
    mergeSort(array, mid + 1, high, aux);  // 对右半边递归
    merge(array, low, mid, high, aux);  // 单趟合并
  }

  private void mergeSort(long[] array, int low, int high, long[] aux) {
    // 终止递归的条件
    if (low == high) {
      return;
    }
    int mid = (int) Math.floor((high - low) / 2) + low;
    mergeSort(array, low, mid, aux);  // 对左半边递归
    mergeSort(array, mid + 1, high, aux);  // 对右半边递归
    merge(array, low, mid, high, aux);  // 单趟合并
  }

  private void mergeSort(float[] array, int low, int high, float[] aux) {
    // 终止递归的条件
    if (low == high) {
      return;
    }
    int mid = (int) Math.floor((high - low) / 2) + low;
    mergeSort(array, low, mid, aux);  // 对左半边递归
    mergeSort(array, mid + 1, high, aux);  // 对右半边递归
    merge(array, low, mid, high, aux);  // 单趟合并
  }

  private void mergeSort(double[] array, int low, int high, double[] aux) {
    // 终止递归的条件
    if (low == high) {
      return;
    }
    int mid = (int) Math.floor((high - low) / 2) + low;
    mergeSort(array, low, mid, aux);  // 对左半边递归
    mergeSort(array, mid + 1, high, aux);  // 对右半边递归
    merge(array, low, mid, high, aux);  // 单趟合并
  }


  protected <T extends Comparable<? super T>> void merge(List<T> list, int low, int mid, int high,
      List<T> aux) {
    int i = low;
    int j = mid + 1;
    int k = low;
    while (i <= mid && j <= high) {
      T left = list.get(i);
      T right = list.get(j);
      if (left.compareTo(right) <= 0) {
        aux.set(k, left);
        i++;
      } else {
        aux.set(k, right);
        j++;
      }
      k++;
    }
    while (i <= mid) {
      aux.set(k, list.get(i));
      k++;
      i++;
    }
    while (j <= high) {
      aux.set(k, list.get(j));
      k++;
      j++;
    }
    // 将排好序的序列放回源队列
    for (int start = low; start <= high; start++) {
      list.set(start, aux.get(start));
    }
  }

  protected void merge(byte[] array, int low, int mid, int high, byte[] aux) {
    int i = low;
    int j = mid + 1;
    int k = low;
    while (i <= mid && j <= high) {
      byte left = array[i];
      byte right = array[j];
      if (left <= right) {
        aux[k] = left;
        i++;
      } else {
        aux[k] = right;
        j++;
      }
      k++;
    }
    while (i <= mid) {
      aux[k] = array[i];
      k++;
      i++;
    }
    while (j <= high) {
      aux[k] = array[j];
      k++;
      j++;
    }
    // 将排好序的序列放回源队列
    for (int start = low; start <= high; start++) {
      array[start] = aux[start];
    }
  }

  protected void merge(char[] array, int low, int mid, int high, char[] aux) {
    int i = low;
    int j = mid + 1;
    int k = low;
    while (i <= mid && j <= high) {
      char left = array[i];
      char right = array[j];
      if (left <= right) {
        aux[k] = left;
        i++;
      } else {
        aux[k] = right;
        j++;
      }
      k++;
    }
    while (i <= mid) {
      aux[k] = array[i];
      k++;
      i++;
    }
    while (j <= high) {
      aux[k] = array[j];
      k++;
      j++;
    }
    // 将排好序的序列放回源队列
    for (int start = low; start <= high; start++) {
      array[start] = aux[start];
    }
  }

  protected void merge(short[] array, int low, int mid, int high, short[] aux) {
    int i = low;
    int j = mid + 1;
    int k = low;
    while (i <= mid && j <= high) {
      short left = array[i];
      short right = array[j];
      if (left <= right) {
        aux[k] = left;
        i++;
      } else {
        aux[k] = right;
        j++;
      }
      k++;
    }
    while (i <= mid) {
      aux[k] = array[i];
      k++;
      i++;
    }
    while (j <= high) {
      aux[k] = array[j];
      k++;
      j++;
    }
    // 将排好序的序列放回源队列
    for (int start = low; start <= high; start++) {
      array[start] = aux[start];
    }
  }

  protected void merge(int[] array, int low, int mid, int high, int[] aux) {
    int i = low;
    int j = mid + 1;
    int k = low;
    while (i <= mid && j <= high) {
      int left = array[i];
      int right = array[j];
      if (left <= right) {
        aux[k] = left;
        i++;
      } else {
        aux[k] = right;
        j++;
      }
      k++;
    }
    while (i <= mid) {
      aux[k] = array[i];
      k++;
      i++;
    }
    while (j <= high) {
      aux[k] = array[j];
      k++;
      j++;
    }
    // 将排好序的序列放回源队列
    for (int start = low; start <= high; start++) {
      array[start] = aux[start];
    }
  }

  protected void merge(long[] array, int low, int mid, int high, long[] aux) {
    int i = low;
    int j = mid + 1;
    int k = low;
    while (i <= mid && j <= high) {
      long left = array[i];
      long right = array[j];
      if (left <= right) {
        aux[k] = left;
        i++;
      } else {
        aux[k] = right;
        j++;
      }
      k++;
    }
    while (i <= mid) {
      aux[k] = array[i];
      k++;
      i++;
    }
    while (j <= high) {
      aux[k] = array[j];
      k++;
      j++;
    }
    // 将排好序的序列放回源队列
    for (int start = low; start <= high; start++) {
      array[start] = aux[start];
    }
  }

  protected void merge(float[] array, int low, int mid, int high, float[] aux) {
    int i = low;
    int j = mid + 1;
    int k = low;
    while (i <= mid && j <= high) {
      float left = array[i];
      float right = array[j];
      if (left <= right) {
        aux[k] = left;
        i++;
      } else {
        aux[k] = right;
        j++;
      }
      k++;
    }
    while (i <= mid) {
      aux[k] = array[i];
      k++;
      i++;
    }
    while (j <= high) {
      aux[k] = array[j];
      k++;
      j++;
    }
    // 将排好序的序列放回源队列
    for (int start = low; start <= high; start++) {
      array[start] = aux[start];
    }
  }

  protected void merge(double[] array, int low, int mid, int high, double[] aux) {
    int i = low;
    int j = mid + 1;
    int k = low;
    while (i <= mid && j <= high) {
      double left = array[i];
      double right = array[j];
      if (left <= right) {
        aux[k] = left;
        i++;
      } else {
        aux[k] = right;
        j++;
      }
      k++;
    }
    while (i <= mid) {
      aux[k] = array[i];
      k++;
      i++;
    }
    while (j <= high) {
      aux[k] = array[j];
      k++;
      j++;
    }
    // 将排好序的序列放回源队列
    for (int start = low; start <= high; start++) {
      array[start] = aux[start];
    }
  }

}
