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
 * 堆排序
 *
 * 堆排序（英语：Heapsort）是指利用堆这种数据结构所设计的一种排序算法。堆是一个近似完全二叉树的结构，并同时满足堆积的性质：即子节点的键值或索引总是小于（或者大于）它的父节点。
 *
 * 堆是一种特殊的树。只要满足这两点，它就是一个堆。
 *
 * <ul>
 *   <li>堆是一个完全二叉树</li>
 *   <li>堆中每一个节点的值都必须大于等于（或小于等于）其子树中每个节点的值</li>
 * </ul>
 *
 * 算法步骤
 *
 * <ul>
 *   <li>1. 将待排序序列构造成一个大顶堆，此时，整个序列的最大值就是堆顶的根节点。（一般升序采用大顶堆，降序采用小顶堆)</li>
 *   <li>2. 将堆顶与末尾元素进行交换，此时末尾就为最大值</li>
 *   <li>3. 然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值</li>
 *   <li>4. 如此反复执行，便能得到一个有序序列了</li>
 * </ul>
 *
 * <ul>
 * <li>平均时间复杂度：O(n log n)</li>
 * <li>最好情况：O(n log n)</li>
 * <li>最坏情况：O(n log n)</li>
 * <li>空间复杂度：O(1)</li>
 * <li>内存占用：In-place：占用常数内存，不占用额外内存</li>
 * <li>稳定性：不稳定</li>
 * </ul>
 *
 */
public class HeapSortAlgorithm implements SortAlgorithm {

  @Override
  public <T extends Comparable<? super T>> void sort(List<T> list) {
    buildHeap(list, list.size());
    int k = list.size();
    while (k > 0) {
      SortUtils.swap(list, 0, k - 1);
      --k;
      heapify(list, 0, k);
    }
  }

  @Override
  public void sort(byte[] array) {
    buildHeap(array, array.length);
    int k = array.length;
    while (k > 0) {
      SortUtils.swap(array, 0, k - 1);
      --k;
      heapify(array, 0, k);
    }
  }

  @Override
  public void sort(char[] array) {
    buildHeap(array, array.length);
    int k = array.length;
    while (k > 0) {
      SortUtils.swap(array, 0, k - 1);
      --k;
      heapify(array, 0, k);
    }
  }

  @Override
  public void sort(short[] array) {
    buildHeap(array, array.length);
    int k = array.length;
    while (k > 0) {
      SortUtils.swap(array, 0, k - 1);
      --k;
      heapify(array, 0, k);
    }
  }

  @Override
  public void sort(int[] array) {
    buildHeap(array, array.length);
    int k = array.length;
    while (k > 0) {
      SortUtils.swap(array, 0, k - 1);
      --k;
      heapify(array, 0, k);
    }
  }

  @Override
  public void sort(long[] array) {
    buildHeap(array, array.length);
    int k = array.length;
    while (k > 0) {
      SortUtils.swap(array, 0, k - 1);
      --k;
      heapify(array, 0, k);
    }
  }

  @Override
  public void sort(float[] array) {
    buildHeap(array, array.length);
    int k = array.length;
    while (k > 0) {
      SortUtils.swap(array, 0, k - 1);
      --k;
      heapify(array, 0, k);
    }
  }

  @Override
  public void sort(double[] array) {
    buildHeap(array, array.length);
    int k = array.length;
    while (k > 0) {
      SortUtils.swap(array, 0, k - 1);
      --k;
      heapify(array, 0, k);
    }
  }

  private <T extends Comparable<? super T>> void buildHeap(List<T> list, int len) {
    for (int i = (int) Math.floor(len / 2) - 1; i >= 0; i--) {
      heapify(list, i, len);
    }
  }

  private void buildHeap(byte[] array, int len) {
    for (int i = (int) Math.floor(len / 2) - 1; i >= 0; i--) {
      heapify(array, i, len);
    }
  }

  private void buildHeap(char[] array, int len) {
    for (int i = (int) Math.floor(len / 2) - 1; i >= 0; i--) {
      heapify(array, i, len);
    }
  }

  private void buildHeap(short[] array, int len) {
    for (int i = (int) Math.floor(len / 2) - 1; i >= 0; i--) {
      heapify(array, i, len);
    }
  }

  private void buildHeap(int[] array, int len) {
    for (int i = (int) Math.floor(len / 2) - 1; i >= 0; i--) {
      heapify(array, i, len);
    }
  }

  private void buildHeap(long[] array, int len) {
    for (int i = (int) Math.floor(len / 2) - 1; i >= 0; i--) {
      heapify(array, i, len);
    }
  }

  private void buildHeap(float[] array, int len) {
    for (int i = (int) Math.floor(len / 2) - 1; i >= 0; i--) {
      heapify(array, i, len);
    }
  }

  private void buildHeap(double[] array, int len) {
    for (int i = (int) Math.floor(len / 2) - 1; i >= 0; i--) {
      heapify(array, i, len);
    }
  }

  private <T extends Comparable<? super T>> void heapify(List<T> list, int i, int len) {
    while (true) {
      int maxPos = i;
      int left = 2 * i + 1;
      int right = 2 * i + 2;
      if (left < len && list.get(i).compareTo(list.get(left)) < 0) {
        maxPos = left;
      }
      ;
      if (right < len && list.get(maxPos).compareTo(list.get(right)) < 0) {
        maxPos = right;
      }
      ;
      if (maxPos == i) {
        break;
      }
      ;
      SortUtils.swap(list, i, maxPos);
      i = maxPos;
    }
  }

  private void heapify(byte[] array, int i, int len) {
    while (true) {
      int maxPos = i;
      int left = 2 * i + 1;
      int right = 2 * i + 2;
      if (left < len && array[i] < array[left]) {
        maxPos = left;
      }
      ;
      if (right < len && array[maxPos] < array[right]) {
        maxPos = right;
      }
      ;
      if (maxPos == i) {
        break;
      }
      ;
      SortUtils.swap(array, i, maxPos);
      i = maxPos;
    }
  }

  private void heapify(char[] array, int i, int len) {
    while (true) {
      int maxPos = i;
      int left = 2 * i + 1;
      int right = 2 * i + 2;
      if (left < len && array[i] < array[left]) {
        maxPos = left;
      }
      ;
      if (right < len && array[maxPos] < array[right]) {
        maxPos = right;
      }
      ;
      if (maxPos == i) {
        break;
      }
      ;
      SortUtils.swap(array, i, maxPos);
      i = maxPos;
    }
  }

  private void heapify(short[] array, int i, int len) {
    while (true) {
      int maxPos = i;
      int left = 2 * i + 1;
      int right = 2 * i + 2;
      if (left < len && array[i] < array[left]) {
        maxPos = left;
      }
      ;
      if (right < len && array[maxPos] < array[right]) {
        maxPos = right;
      }
      ;
      if (maxPos == i) {
        break;
      }
      ;
      SortUtils.swap(array, i, maxPos);
      i = maxPos;
    }
  }

  private void heapify(int[] array, int i, int len) {
    while (true) {
      int maxPos = i;
      int left = 2 * i + 1;
      int right = 2 * i + 2;
      if (left < len && array[i] < array[left]) {
        maxPos = left;
      }
      ;
      if (right < len && array[maxPos] < array[right]) {
        maxPos = right;
      }
      ;
      if (maxPos == i) {
        break;
      }
      ;
      SortUtils.swap(array, i, maxPos);
      i = maxPos;
    }
  }

  private void heapify(long[] array, int i, int len) {
    while (true) {
      int maxPos = i;
      int left = 2 * i + 1;
      int right = 2 * i + 2;
      if (left < len && array[i] < array[left]) {
        maxPos = left;
      }
      ;
      if (right < len && array[maxPos] < array[right]) {
        maxPos = right;
      }
      ;
      if (maxPos == i) {
        break;
      }
      ;
      SortUtils.swap(array, i, maxPos);
      i = maxPos;
    }
  }

  private void heapify(float[] array, int i, int len) {
    while (true) {
      int maxPos = i;
      int left = 2 * i + 1;
      int right = 2 * i + 2;
      if (left < len && array[i] < array[left]) {
        maxPos = left;
      }
      ;
      if (right < len && array[maxPos] < array[right]) {
        maxPos = right;
      }
      ;
      if (maxPos == i) {
        break;
      }
      ;
      SortUtils.swap(array, i, maxPos);
      i = maxPos;
    }
  }

  private void heapify(double[] array, int i, int len) {
    while (true) {
      int maxPos = i;
      int left = 2 * i + 1;
      int right = 2 * i + 2;
      if (left < len && array[i] < array[left]) {
        maxPos = left;
      }
      ;
      if (right < len && array[maxPos] < array[right]) {
        maxPos = right;
      }
      ;
      if (maxPos == i) {
        break;
      }
      ;
      SortUtils.swap(array, i, maxPos);
      i = maxPos;
    }
  }
}
