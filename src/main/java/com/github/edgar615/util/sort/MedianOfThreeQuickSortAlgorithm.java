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
 * 快速排序的三数取中
 *
 * 一般是分别取出数组的头部元素，尾部元素和中部元素， 在这三个数中取出中位数，作为基准元素。
 *
 * 最佳的划分是将待排序的序列分成等长的子序列，最佳的状态我们可以使用序列的中间的值，也就是第N/2个数。可是，这很难算出来，并且会明显减慢快速排序的速度。
 * 这样的中值的估计可以通过随机选取三个元素并用它们的中值作为枢纽元而得到。
 * 事实上，随机性并没有多大的帮助，因此一般的做法是使用左端、右端和中心位置上的三个元素的中值作为枢纽元。
 * 显然使用三数中值分割法消除了预排序输入的不好情形。（简单来说，就是随机取三个数，取中位数）。
 */
public class MedianOfThreeQuickSortAlgorithm implements SortAlgorithm {

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
    int mid = left + (right - left) / 2;
    if (list.get(left).compareTo(list.get(right)) > 0) {
      SortUtils.swap(list, left, right);	/* 交换左端和右端数据，保证左端较小 */
    }
    if (list.get(mid).compareTo(list.get(right)) > 0) {
      SortUtils.swap(list, mid, right); /* 交换中间和右端数据，保证中间较小 */
    }
    if (list.get(left).compareTo(list.get(mid)) > 0) {
      SortUtils.swap(list, left, mid);	/* 交换左端和中间的数据，保证左端较小 */
    }
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
    int mid = left + (right - left) / 2;
    if (array[left] > array[right]) {
      SortUtils.swap(array, left, right);	/* 交换左端和右端数据，保证左端较小 */
    }
    if (array[mid] > array[right]) {
      SortUtils.swap(array, mid, right); /* 交换中间和右端数据，保证中间较小 */
    }
    if (array[left] > array[mid]) {
      SortUtils.swap(array, left, mid);	/* 交换左端和中间的数据，保证左端较小 */
    }
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
    int mid = left + (right - left) / 2;
    if (array[left] > array[right]) {
      SortUtils.swap(array, left, right);	/* 交换左端和右端数据，保证左端较小 */
    }
    if (array[mid] > array[right]) {
      SortUtils.swap(array, mid, right); /* 交换中间和右端数据，保证中间较小 */
    }
    if (array[left] > array[mid]) {
      SortUtils.swap(array, left, mid);	/* 交换左端和中间的数据，保证左端较小 */
    }
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
    int mid = left + (right - left) / 2;
    if (array[left] > array[right]) {
      SortUtils.swap(array, left, right);	/* 交换左端和右端数据，保证左端较小 */
    }
    if (array[mid] > array[right]) {
      SortUtils.swap(array, mid, right); /* 交换中间和右端数据，保证中间较小 */
    }
    if (array[left] > array[mid]) {
      SortUtils.swap(array, left, mid);	/* 交换左端和中间的数据，保证左端较小 */
    }
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
    int mid = left + (right - left) / 2;
    if (array[left] > array[right]) {
      SortUtils.swap(array, left, right);	/* 交换左端和右端数据，保证左端较小 */
    }
    if (array[mid] > array[right]) {
      SortUtils.swap(array, mid, right); /* 交换中间和右端数据，保证中间较小 */
    }
    if (array[left] > array[mid]) {
      SortUtils.swap(array, left, mid);	/* 交换左端和中间的数据，保证左端较小 */
    }
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
    int mid = left + (right - left) / 2;
    if (array[left] > array[right]) {
      SortUtils.swap(array, left, right);	/* 交换左端和右端数据，保证左端较小 */
    }
    if (array[mid] > array[right]) {
      SortUtils.swap(array, mid, right); /* 交换中间和右端数据，保证中间较小 */
    }
    if (array[left] > array[mid]) {
      SortUtils.swap(array, left, mid);	/* 交换左端和中间的数据，保证左端较小 */
    }
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
    int mid = left + (right - left) / 2;
    if (array[left] > array[right]) {
      SortUtils.swap(array, left, right);	/* 交换左端和右端数据，保证左端较小 */
    }
    if (array[mid] > array[right]) {
      SortUtils.swap(array, mid, right); /* 交换中间和右端数据，保证中间较小 */
    }
    if (array[left] > array[mid]) {
      SortUtils.swap(array, left, mid);	/* 交换左端和中间的数据，保证左端较小 */
    }
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
    int mid = left + (right - left) / 2;
    if (array[left] > array[right]) {
      SortUtils.swap(array, left, right);	/* 交换左端和右端数据，保证左端较小 */
    }
    if (array[mid] > array[right]) {
      SortUtils.swap(array, mid, right); /* 交换中间和右端数据，保证中间较小 */
    }
    if (array[left] > array[mid]) {
      SortUtils.swap(array, left, mid);	/* 交换左端和中间的数据，保证左端较小 */
    }
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
