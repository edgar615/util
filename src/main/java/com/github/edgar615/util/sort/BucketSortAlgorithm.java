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
