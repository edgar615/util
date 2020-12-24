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
 * 快速排序的Hoare分区法
 */
public class HoareQuickSortAlgorithm implements SortAlgorithm {

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
    int i = left;
    int j = right;
    while (true) { /* 从表的两端交替向中间扫描 */
      while (j > i && list.get(j).compareTo(list.get(pivot)) > 0) {
        j--;
      }
      while (i < j && list.get(i).compareTo(list.get(pivot)) <= 0) {
        i++;
      }
      if (i >= j) {
        break;
      }
      SortUtils.swap(list, i, j); /* 将比基准记录大的记录交换到高端 */
    }
    SortUtils.swap(list, pivot, j); /* 交换基准值 */
    return j;
  }

  private int partition(byte[] array, int left, int right) {
    // 设定基准值（pivot）
    int pivot = left;
    int i = left;
    int j = right;
    while (true) { /* 从表的两端交替向中间扫描 */
      while (j > i && array[j] > array[pivot]) {
        j--;
      }
      while (i < j && array[i] <= array[pivot]) {
        i++;
      }
      if (i >= j) {
        break;
      }
      SortUtils.swap(array, i, j); /* 将比基准记录大的记录交换到高端 */
    }
    SortUtils.swap(array, pivot, j); /* 交换基准值 */
    return j;
  }

  private int partition(char[] array, int left, int right) {
    // 设定基准值（pivot）
    int pivot = left;
    int i = left;
    int j = right;
    while (true) { /* 从表的两端交替向中间扫描 */
      while (j > i && array[j] > array[pivot]) {
        j--;
      }
      while (i < j && array[i] <= array[pivot]) {
        i++;
      }
      if (i >= j) {
        break;
      }
      SortUtils.swap(array, i, j); /* 将比基准记录大的记录交换到高端 */
    }
    SortUtils.swap(array, pivot, j); /* 交换基准值 */
    return j;
  }

  private int partition(short[] array, int left, int right) {
    // 设定基准值（pivot）
    int pivot = left;
    int i = left;
    int j = right;
    while (true) { /* 从表的两端交替向中间扫描 */
      while (j > i && array[j] > array[pivot]) {
        j--;
      }
      while (i < j && array[i] <= array[pivot]) {
        i++;
      }
      if (i >= j) {
        break;
      }
      SortUtils.swap(array, i, j); /* 将比基准记录大的记录交换到高端 */
    }
    SortUtils.swap(array, pivot, j); /* 交换基准值 */
    return j;
  }

  private int partition(int[] array, int left, int right) {
    // 设定基准值（pivot）
    int pivot = left;
    int i = left;
    int j = right;
    while (true) { /* 从表的两端交替向中间扫描 */
      while (j > i && array[j] > array[pivot]) {
        j--;
      }
      while (i < j && array[i] <= array[pivot]) {
        i++;
      }
      if (i >= j) {
        break;
      }
      SortUtils.swap(array, i, j); /* 将比基准记录大的记录交换到高端 */
    }
    SortUtils.swap(array, pivot, j); /* 交换基准值 */
    return j;
  }

  private int partition(long[] array, int left, int right) {
    // 设定基准值（pivot）
    int pivot = left;
    int i = left;
    int j = right;
    while (true) { /* 从表的两端交替向中间扫描 */
      while (j > i && array[j] > array[pivot]) {
        j--;
      }
      while (i < j && array[i] <= array[pivot]) {
        i++;
      }
      if (i >= j) {
        break;
      }
      SortUtils.swap(array, i, j); /* 将比基准记录大的记录交换到高端 */
    }
    SortUtils.swap(array, pivot, j); /* 交换基准值 */
    return j;
  }

  private int partition(float[] array, int left, int right) {
    // 设定基准值（pivot）
    int pivot = left;
    int i = left;
    int j = right;
    while (true) { /* 从表的两端交替向中间扫描 */
      while (j > i && array[j] > array[pivot]) {
        j--;
      }
      while (i < j && array[i] <= array[pivot]) {
        i++;
      }
      if (i >= j) {
        break;
      }
      SortUtils.swap(array, i, j); /* 将比基准记录大的记录交换到高端 */
    }
    SortUtils.swap(array, pivot, j); /* 交换基准值 */
    return j;
  }

  private int partition(double[] array, int left, int right) {
    // 设定基准值（pivot）
    int pivot = left;
    int i = left;
    int j = right;
    while (true) { /* 从表的两端交替向中间扫描 */
      while (j > i && array[j] > array[pivot]) {
        j--;
      }
      while (i < j && array[i] <= array[pivot]) {
        i++;
      }
      if (i >= j) {
        break;
      }
      SortUtils.swap(array, i, j); /* 将比基准记录大的记录交换到高端 */
    }
    SortUtils.swap(array, pivot, j); /* 交换基准值 */
    return j;
  }

}
