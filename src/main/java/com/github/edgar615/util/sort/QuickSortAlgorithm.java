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
 * 优化后的快速排序
 *
 * 基准采用三数取中，过滤不必要的交换
 */
public class QuickSortAlgorithm implements SortAlgorithm {

  private static final int MAX_LENGTH_INSERT_SORT = 20;

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
    if (right - left < MAX_LENGTH_INSERT_SORT) {
      new InsertSortAlgorithm().sort(arr);
    } else {
      while (left < right) {
        int partitionIndex = partition(arr, left, right);
        quickSort(arr, left, partitionIndex - 1);
        // 尾递归
        left = partitionIndex + 1;
      }
    }
  }

  private void quickSort(byte[] arr, int left, int right) {
    if (right - left < MAX_LENGTH_INSERT_SORT) {
      new InsertSortAlgorithm().sort(arr);
    } else {
      while (left < right) {
        int partitionIndex = partition(arr, left, right);
        quickSort(arr, left, partitionIndex - 1);
        // 尾递归
        left = partitionIndex + 1;
      }
    }
  }

  private void quickSort(char[] arr, int left, int right) {
    if (right - left < MAX_LENGTH_INSERT_SORT) {
      new InsertSortAlgorithm().sort(arr);
    } else {
      while (left < right) {
        int partitionIndex = partition(arr, left, right);
        quickSort(arr, left, partitionIndex - 1);
        // 尾递归
        left = partitionIndex + 1;
      }
    }
  }

  private void quickSort(short[] arr, int left, int right) {
    if (right - left < MAX_LENGTH_INSERT_SORT) {
      new InsertSortAlgorithm().sort(arr);
    } else {
      while (left < right) {
        int partitionIndex = partition(arr, left, right);
        quickSort(arr, left, partitionIndex - 1);
        // 尾递归
        left = partitionIndex + 1;
      }
    }
  }

  private void quickSort(int[] arr, int left, int right) {
    if (right - left < MAX_LENGTH_INSERT_SORT) {
      new InsertSortAlgorithm().sort(arr);
    } else {
      while (left < right) {
        int partitionIndex = partition(arr, left, right);
        quickSort(arr, left, partitionIndex - 1);
        // 尾递归
        left = partitionIndex + 1;
      }
    }
  }

  private void quickSort(long[] arr, int left, int right) {
    if (right - left < MAX_LENGTH_INSERT_SORT) {
      new InsertSortAlgorithm().sort(arr);
    } else {
      while (left < right) {
        int partitionIndex = partition(arr, left, right);
        quickSort(arr, left, partitionIndex - 1);
        // 尾递归
        left = partitionIndex + 1;
      }
    }
  }

  private void quickSort(float[] arr, int left, int right) {
    if (right - left < MAX_LENGTH_INSERT_SORT) {
      new InsertSortAlgorithm().sort(arr);
    } else {
      while (left < right) {
        int partitionIndex = partition(arr, left, right);
        quickSort(arr, left, partitionIndex - 1);
        // 尾递归
        left = partitionIndex + 1;
      }
    }
  }

  private void quickSort(double[] arr, int left, int right) {
    if (right - left < MAX_LENGTH_INSERT_SORT) {
      new InsertSortAlgorithm().sort(arr);
    } else {
      while (left < right) {
        int partitionIndex = partition(arr, left, right);
        quickSort(arr, left, partitionIndex - 1);
        // 尾递归
        left = partitionIndex + 1;
      }
    }
  }

  private <T extends Comparable<? super T>> int partition(List<T> list, int left, int right) {
    // 设定基准值（pivot）
    int mid = left + (right - left) / 2;
    if (list.get(left).compareTo(list.get(right)) > 0) {
      SortUtils.swap(list, left, right);  /* 交换左端和右端数据，保证左端较小 */
    }
    if (list.get(mid).compareTo(list.get(right)) > 0) {
      SortUtils.swap(list, mid, right); /* 交换中间和右端数据，保证中间较小 */
    }
    if (list.get(left).compareTo(list.get(mid)) > 0) {
      SortUtils.swap(list, left, mid);  /* 交换左端和中间的数据，保证左端较小 */
    }
    T pivotValue = list.get(left);
    int i = left;
    int j = right;
    while (i < j) { /* 从表的两端交替向中间扫描 */
      while (j > i && list.get(j).compareTo(pivotValue) >= 0) {
        j--;
      }//出了循环说明要进行移动
      // 采用替换而不是交换的方式进行操作
      list.set(i, list.get(j));
      while (i < j && list.get(i).compareTo(pivotValue) <= 0) {
        i++;
      }
      list.set(j, list.get(i));
    }
    list.set(i, pivotValue);
    return j;
  }

  private int partition(byte[] array, int left, int right) {
    int mid = left + (right - left) / 2;
    if (array[left] > array[right]) {
      SortUtils.swap(array, left, right);  /* 交换左端和右端数据，保证左端较小 */
    }
    if (array[mid] > array[right]) {
      SortUtils.swap(array, mid, right); /* 交换中间和右端数据，保证中间较小 */
    }
    if (array[left] > array[mid]) {
      SortUtils.swap(array, left, mid);  /* 交换左端和中间的数据，保证左端较小 */
    }
    byte pivotValue = array[left];
    int i = left;
    int j = right;
    while (i < j) {
      while (j > i && array[j] >= pivotValue) {
        j--;
      }//出了循环说明要进行移动
      // 采用替换而不是交换的方式进行操作
      array[i] = array[j];

      while (i < j && array[i] <= pivotValue) {
        i++;
      }
      array[j] = array[i];
    }
    array[i] = pivotValue;
    return i;
  }

  private int partition(char[] array, int left, int right) {
    int mid = left + (right - left) / 2;
    if (array[left] > array[right]) {
      SortUtils.swap(array, left, right);  /* 交换左端和右端数据，保证左端较小 */
    }
    if (array[mid] > array[right]) {
      SortUtils.swap(array, mid, right); /* 交换中间和右端数据，保证中间较小 */
    }
    if (array[left] > array[mid]) {
      SortUtils.swap(array, left, mid);  /* 交换左端和中间的数据，保证左端较小 */
    }
    char pivotValue = array[left];
    int i = left;
    int j = right;
    while (i < j) {
      while (j > i && array[j] >= pivotValue) {
        j--;
      }//出了循环说明要进行移动
      // 采用替换而不是交换的方式进行操作
      array[i] = array[j];

      while (i < j && array[i] <= pivotValue) {
        i++;
      }
      array[j] = array[i];
    }
    array[i] = pivotValue;
    return i;
  }

  private int partition(short[] array, int left, int right) {
    int mid = left + (right - left) / 2;
    if (array[left] > array[right]) {
      SortUtils.swap(array, left, right);  /* 交换左端和右端数据，保证左端较小 */
    }
    if (array[mid] > array[right]) {
      SortUtils.swap(array, mid, right); /* 交换中间和右端数据，保证中间较小 */
    }
    if (array[left] > array[mid]) {
      SortUtils.swap(array, left, mid);  /* 交换左端和中间的数据，保证左端较小 */
    }
    short pivotValue = array[left];
    int i = left;
    int j = right;
    while (i < j) {
      while (j > i && array[j] >= pivotValue) {
        j--;
      }//出了循环说明要进行移动
      // 采用替换而不是交换的方式进行操作
      array[i] = array[j];

      while (i < j && array[i] <= pivotValue) {
        i++;
      }
      array[j] = array[i];
    }
    array[i] = pivotValue;
    return i;
  }

  private int partition(int[] array, int left, int right) {
    int mid = left + (right - left) / 2;
    if (array[left] > array[right]) {
      SortUtils.swap(array, left, right);  /* 交换左端和右端数据，保证左端较小 */
    }
    if (array[mid] > array[right]) {
      SortUtils.swap(array, mid, right); /* 交换中间和右端数据，保证中间较小 */
    }
    if (array[left] > array[mid]) {
      SortUtils.swap(array, left, mid);  /* 交换左端和中间的数据，保证左端较小 */
    }
    int pivotValue = array[left];
    int i = left;
    int j = right;
    while (i < j) {
      while (j > i && array[j] >= pivotValue) {
        j--;
      }//出了循环说明要进行移动
      // 采用替换而不是交换的方式进行操作
      array[i] = array[j];

      while (i < j && array[i] <= pivotValue) {
        i++;
      }
      array[j] = array[i];
    }
    array[i] = pivotValue;
    return i;
  }

  private int partition(long[] array, int left, int right) {
    int mid = left + (right - left) / 2;
    if (array[left] > array[right]) {
      SortUtils.swap(array, left, right);  /* 交换左端和右端数据，保证左端较小 */
    }
    if (array[mid] > array[right]) {
      SortUtils.swap(array, mid, right); /* 交换中间和右端数据，保证中间较小 */
    }
    if (array[left] > array[mid]) {
      SortUtils.swap(array, left, mid);  /* 交换左端和中间的数据，保证左端较小 */
    }
    long pivotValue = array[left];
    int i = left;
    int j = right;
    while (i < j) {
      while (j > i && array[j] >= pivotValue) {
        j--;
      }//出了循环说明要进行移动
      // 采用替换而不是交换的方式进行操作
      array[i] = array[j];

      while (i < j && array[i] <= pivotValue) {
        i++;
      }
      array[j] = array[i];
    }
    array[i] = pivotValue;
    return i;
  }

  private int partition(float[] array, int left, int right) {
    int mid = left + (right - left) / 2;
    if (array[left] > array[right]) {
      SortUtils.swap(array, left, right);  /* 交换左端和右端数据，保证左端较小 */
    }
    if (array[mid] > array[right]) {
      SortUtils.swap(array, mid, right); /* 交换中间和右端数据，保证中间较小 */
    }
    if (array[left] > array[mid]) {
      SortUtils.swap(array, left, mid);  /* 交换左端和中间的数据，保证左端较小 */
    }
    float pivotValue = array[left];
    int i = left;
    int j = right;
    while (i < j) {
      while (j > i && array[j] >= pivotValue) {
        j--;
      }//出了循环说明要进行移动
      // 采用替换而不是交换的方式进行操作
      array[i] = array[j];

      while (i < j && array[i] <= pivotValue) {
        i++;
      }
      array[j] = array[i];
    }
    array[i] = pivotValue;
    return i;
  }

  private int partition(double[] array, int left, int right) {
    int mid = left + (right - left) / 2;
    if (array[left] > array[right]) {
      SortUtils.swap(array, left, right);  /* 交换左端和右端数据，保证左端较小 */
    }
    if (array[mid] > array[right]) {
      SortUtils.swap(array, mid, right); /* 交换中间和右端数据，保证中间较小 */
    }
    if (array[left] > array[mid]) {
      SortUtils.swap(array, left, mid);  /* 交换左端和中间的数据，保证左端较小 */
    }
    double pivotValue = array[left];
    int i = left;
    int j = right;
    while (i < j) {
      while (j > i && array[j] >= pivotValue) {
        j--;
      }//出了循环说明要进行移动
      // 采用替换而不是交换的方式进行操作
      array[i] = array[j];

      while (i < j && array[i] <= pivotValue) {
        i++;
      }
      array[j] = array[i];
    }
    array[i] = pivotValue;
    return i;
  }

}
