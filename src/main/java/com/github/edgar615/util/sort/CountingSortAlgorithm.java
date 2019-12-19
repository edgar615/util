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
 * 计数排序
 *
 * 计数排序是一种非基于比较的排序算法，其空间复杂度和时间复杂度均为`O(n+k)`，其中k是整数的范围。基于比较的排序算法时间复杂度最小是`O(nlogn)`的。
 *
 * 计数排序的核心在于将输入的数据值转化为键存储在额外开辟的数组空间中。<b>作为一种线性时间复杂度的排序，计数排序要求输入的数据必须是有确定范围的整数</b>。
 *
 * 算法的步骤：
 *
 * <ul>
 * <li>1. 找出待排序的数组中最大和最小的元素</li>
 * <li>2. 统计数组中每个值为i的元素出现的次数，存入数组C的第i项</li>
 * <li>3. 对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）</li>
 * <li>4. 反向填充目标数组：将每个元素i放在新数组的第C(i)项，每放一个元素就将C(i)减去1</li>
 * </ul>
 *
 * <ul>
 * <li>平均时间复杂度：O(n + k)</li>
 * <li>最好情况：O(n + k)</li>
 * <li>最坏情况：O(n + k)</li>
 * <li>空间复杂度：O(k)</li>
 * <li>内存占用：Out-place：占用额外内存</li>
 * <li>稳定性：稳定</li>
 * </ul>
 */
public class CountingSortAlgorithm implements SortAlgorithm {

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
      if (a > max) {
        max = a;
      }
      if (a < min) {
        min = a;
      }
    }
    // 申请一个计数数组
    int k = max - min + 1;
    int c[] = new int[k];
    for (int a : array) {
      c[a - min]++;
    }
    //然后为了保持排序稳定，我们需要做一次累加操作
    //这样做的目的，是为了标记出原始数组里面的该元素，前面有几个元素，这个值实际上就是其在原生数组里面的位置，如果有重复的元素，则会先会放置最右边的元素，这样就能保证，排序的稳定性
    for(int i = 1; i < c.length; ++i){
      c[i] = c[i] + c[i-1];
    }
    int len = array.length;
    int[] b = new int[len];
    for (int i = len - 1; i >= 0; --i) {
      //按存取的方式取出c的元素，每取出一个，减一
      b[--c[array[i] - min]] = array[i];
    }
    for (int i = 0; i < len; i ++) {
      array[i] = b[i];
    }
  }
}
