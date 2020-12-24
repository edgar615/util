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
 * 快速排序
 *
 * 快速排序使用分治法（Divide and conquer）策略来把一个串行（list）分为两个子串行（sub-lists）。快速排序又是一种分而治之思想在排序算法上的典型应用。本质上来看，快速排序应该算是在冒泡排序基础上的递归分治法。
 *
 * 算法步骤
 *
 * <ul>
 * <li>1. 从数列中挑出一个元素，称为 "基准"（pivot）;</li>
 * <li>2. 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；</li>
 * <li>3. 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序；</li>
 * </ul>
 *
 * <ul>
 * <li>平均时间复杂度：O(n log n)</li>
 * <li>最好情况：O(n log n)</li>
 * <li>最坏情况：O(n^n)</li>
 * <li>空间复杂度：O(log n)</li>
 * <li>内存占用：In-place：占用常数内存，不占用额外内存</li>
 * <li>稳定性：不稳定</li>
 * </ul>
 */
public class LomutoQuickSortAlgorithm implements SortAlgorithm {

  @Override
  public <T extends Comparable<? super T>> void sort(List<T> list) {
    quickSort(list, 0, list.size() - 1);
  }

  @Override
  public void sort(byte[] array) {
    quickSort(array, 0, array.length - 1);
  }

  @Override
  public void sort(char[] array) {
    quickSort(array, 0, array.length - 1);
  }

  @Override
  public void sort(short[] array) {
    quickSort(array, 0, array.length - 1);
  }

  @Override
  public void sort(int[] array) {
    quickSort(array, 0, array.length - 1);
  }

  @Override
  public void sort(long[] array) {
    quickSort(array, 0, array.length - 1);
  }

  @Override
  public void sort(float[] array) {
    quickSort(array, 0, array.length - 1);
  }

  @Override
  public void sort(double[] array) {
    quickSort(array, 0, array.length - 1);
  }

  private <T extends Comparable<? super T>> void quickSort(List<T> arr, int left, int right) {
    if (left < right) {
      int partitionIndex = partition(arr, left, right);
      quickSort(arr, left, partitionIndex - 1);
      quickSort(arr, partitionIndex + 1, right);
    }
  }

  private void quickSort(byte[] arr, int left, int right) {
    if (left < right) {
      int partitionIndex = partition(arr, left, right);
      quickSort(arr, left, partitionIndex - 1);
      quickSort(arr, partitionIndex + 1, right);
    }
  }

  private void quickSort(char[] arr, int left, int right) {
    if (left < right) {
      int partitionIndex = partition(arr, left, right);
      quickSort(arr, left, partitionIndex - 1);
      quickSort(arr, partitionIndex + 1, right);
    }
  }

  private void quickSort(short[] arr, int left, int right) {
    if (left < right) {
      int partitionIndex = partition(arr, left, right);
      quickSort(arr, left, partitionIndex - 1);
      quickSort(arr, partitionIndex + 1, right);
    }
  }

  private void quickSort(int[] arr, int left, int right) {
    if (left < right) {
      int partitionIndex = partition(arr, left, right);
      quickSort(arr, left, partitionIndex - 1);
      quickSort(arr, partitionIndex + 1, right);
    }
  }

  private void quickSort(long[] arr, int left, int right) {
    if (left < right) {
      int partitionIndex = partition(arr, left, right);
      quickSort(arr, left, partitionIndex - 1);
      quickSort(arr, partitionIndex + 1, right);
    }
  }

  private void quickSort(float[] arr, int left, int right) {
    if (left < right) {
      int partitionIndex = partition(arr, left, right);
      quickSort(arr, left, partitionIndex - 1);
      quickSort(arr, partitionIndex + 1, right);
    }
  }

  private void quickSort(double[] arr, int left, int right) {
    if (left < right) {
      int partitionIndex = partition(arr, left, right);
      quickSort(arr, left, partitionIndex - 1);
      quickSort(arr, partitionIndex + 1, right);
    }
  }

  private <T extends Comparable<? super T>> int partition(List<T> list, int left, int right) {
    // 设定基准值（pivot）
    int pivot = left;
    int index = pivot + 1;
    for (int i = index; i <= right; i++) {
      if (list.get(i).compareTo(list.get(pivot)) < 0) {
        SortUtils.swap(list, i, index);
        index++;
      }
    }
    SortUtils.swap(list, pivot, index - 1);
    return index - 1;
  }

  private int partition(byte[] arr, int left, int right) {
    // 设定基准值（pivot）
    int pivot = left;
    int index = pivot + 1;
    for (int i = index; i <= right; i++) {
      if (arr[i] < arr[pivot]) {
        SortUtils.swap(arr, i, index);
        index++;
      }
    }
    SortUtils.swap(arr, pivot, index - 1);
    return index - 1;
  }

  private int partition(char[] arr, int left, int right) {
    // 设定基准值（pivot）
    int pivot = left;
    int index = pivot + 1;
    for (int i = index; i <= right; i++) {
      if (arr[i] < arr[pivot]) {
        SortUtils.swap(arr, i, index);
        index++;
      }
    }
    SortUtils.swap(arr, pivot, index - 1);
    return index - 1;
  }

  private int partition(short[] arr, int left, int right) {
    // 设定基准值（pivot）
    int pivot = left;
    int index = pivot + 1;
    for (int i = index; i <= right; i++) {
      if (arr[i] < arr[pivot]) {
        SortUtils.swap(arr, i, index);
        index++;
      }
    }
    SortUtils.swap(arr, pivot, index - 1);
    return index - 1;
  }

  private int partition(int[] arr, int left, int right) {
    // 设定基准值（pivot）
    int pivot = left;
    int index = pivot + 1;
    for (int i = index; i <= right; i++) {
      if (arr[i] < arr[pivot]) {
        SortUtils.swap(arr, i, index);
        index++;
      }
    }
    SortUtils.swap(arr, pivot, index - 1);
    return index - 1;
  }

  private int partition(long[] arr, int left, int right) {
    // 设定基准值（pivot）
    int pivot = left;
    int index = pivot + 1;
    for (int i = index; i <= right; i++) {
      if (arr[i] < arr[pivot]) {
        SortUtils.swap(arr, i, index);
        index++;
      }
    }
    SortUtils.swap(arr, pivot, index - 1);
    return index - 1;
  }

  private int partition(float[] arr, int left, int right) {
    // 设定基准值（pivot）
    int pivot = left;
    int index = pivot + 1;
    for (int i = index; i <= right; i++) {
      if (arr[i] < arr[pivot]) {
        SortUtils.swap(arr, i, index);
        index++;
      }
    }
    SortUtils.swap(arr, pivot, index - 1);
    return index - 1;
  }

  private int partition(double[] arr, int left, int right) {
    // 设定基准值（pivot）
    int pivot = left;
    int index = pivot + 1;
    for (int i = index; i <= right; i++) {
      if (arr[i] < arr[pivot]) {
        SortUtils.swap(arr, i, index);
        index++;
      }
    }
    SortUtils.swap(arr, pivot, index - 1);
    return index - 1;
  }


}
