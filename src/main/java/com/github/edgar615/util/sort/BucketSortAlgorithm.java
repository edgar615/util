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

import java.util.Arrays;
import java.util.List;

/**
 * 桶排序
 *
 * 桶排序（Bucket sort）或所谓的箱排序，是一个排序算法，工作的原理是将数组分到有限数量的桶里。每个桶再个别排序（有可能再使用别的排序算法或是以递归方式继续使用桶排序进行排序）。
 * 桶排序是鸽巢排序的一种归纳结果。当要被排序的数组内的数值是均匀分配的时候，桶排序使用线性时间O( n )。但桶排序并不是比较排序，他不受到` O ( n log n )下限的影响。
 *
 * 算法步骤
 *
 * <ul>
 *   <li>1.找到最大元素与最小元素</li>
 *   <li>2.根据桶的个数计算每个桶的范围</li>
 *   <li>3.根据数值大小分配到不同的桶</li>
 *   <li>4.对桶内元素进行排序</li>
 *   <li>5.按照桶范围依次输出桶内元素</li>
 * </ul>
 *
 * <ul>
 * <li>平均时间复杂度：O(n + k)</li>
 * <li>最好情况：O(n + k)</li>
 * <li>最坏情况：O(n ^ n)</li>
 * <li>空间复杂度：O(n + k)</li>
 * <li>内存占用：Out-place：占用额外内存</li>
 * <li>稳定性：稳定</li>
 * </ul>
 */
public class BucketSortAlgorithm implements SortAlgorithm {

  private final int bucketStep;

  public BucketSortAlgorithm(int bucketStep) {
    this.bucketStep = bucketStep;
  }

  @Override
  public <T extends Comparable<? super T>> void sort(List<T> list) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void sort(byte[] array) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void sort(char[] array) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void sort(short[] array) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void sort(long[] array) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void sort(float[] array) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void sort(double[] array) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void sort(int[] array) {
    int max = array[0], min = array[0];
    for (int a : array) {
      if (max < a) {
        max = a;
      }
      if (min > a) {
        min = a;
      }
    }
    //根据最大值与最小值，将不同大小的数分配到不同的桶中
    int bucketCount = (int) Math.floor((max - min) / bucketStep) + 1;
    int[][] buckets = new int[bucketCount][0];
    // 利用映射函数将数据分配到各个桶中
    for (int i = 0; i < array.length; i++) {
      //计算该元素所属范围，放置到对应的桶里面
      int index = (int) Math.floor((array[i] - min) / bucketStep);
      buckets[index] = arrAppend(buckets[index], array[i]);
    }

    int arrIndex = 0;
    for (int[] bucket : buckets) {
      if (bucket.length <= 0) {
        continue;
      }
      // 对每个桶进行排序，这里使用了插入排序
      new InsertSortAlgorithm().sort(bucket);
      for (int value : bucket) {
        array[arrIndex++] = value;
      }
    }
  }

  private int[] arrAppend(int[] arr, int value) {
    arr = Arrays.copyOf(arr, arr.length + 1);
    arr[arr.length - 1] = value;
    return arr;
  }

}
