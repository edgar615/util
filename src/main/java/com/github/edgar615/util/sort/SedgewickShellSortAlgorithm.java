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
 * 使用Sedgewick增量的希尔排序
 */
public class SedgewickShellSortAlgorithm implements SortAlgorithm {

  @Override
  public <T extends Comparable<? super T>> void sort(List<T> list) {
    int len = list.size();
    int[] gapArray = genGap(len);

    for (int s = gapArray.length - 1; s >= 0; s --) {
      int gap = gapArray[s];
      for (int i = gap; i < len; i++) {
        T tmp = list.get(i);
        int j = i;
        while (j >= gap && list.get(j - gap).compareTo(tmp) > 0) {
          list.set(j, list.get(j - gap));
          j -= gap;
        }
        if (i != j) {
          list.set(j, tmp);
        }
      }
    }
  }

  /**
   * codegen by javapoet
   */
  @Override
  public void sort(byte[] array) {
    int len = array.length;
    int[] gapArray = genGap(len);
    for (int s = gapArray.length - 1; s >= 0; s --) {
      int gap = gapArray[s];
      for (int i = gap; i < len; i++) {
        byte tmp = array[i];
        int j = i;
        while (j >= gap && array[j - gap] > tmp) {
          array[j] = array[j - gap];
          j -= gap;
        }
        if (i != j) {
          array[j] = tmp;
        }
      }
    }
  }

  /**
   * codegen by javapoet
   */
  @Override
  public void sort(char[] array) {
    int len = array.length;
    int[] gapArray = genGap(len);
    for (int s = gapArray.length - 1; s >= 0; s --) {
      int gap = gapArray[s];
      for (int i = gap; i < len; i++) {
        char tmp = array[i];
        int j = i;
        while (j >= gap && array[j - gap] > tmp) {
          array[j] = array[j - gap];
          j -= gap;
        }
        if (i != j) {
          array[j] = tmp;
        }
      }
    }
  }

  /**
   * codegen by javapoet
   */
  @Override
  public void sort(short[] array) {
    int len = array.length;
    int[] gapArray = genGap(len);
    for (int s = gapArray.length - 1; s >= 0; s --) {
      int gap = gapArray[s];
      for (int i = gap; i < len; i++) {
        short tmp = array[i];
        int j = i;
        while (j >= gap && array[j - gap] > tmp) {
          array[j] = array[j - gap];
          j -= gap;
        }
        if (i != j) {
          array[j] = tmp;
        }
      }
    }
  }

  /**
   * codegen by javapoet
   */
  @Override
  public void sort(int[] array) {
    int len = array.length;
    int[] gapArray = genGap(len);
    for (int s = gapArray.length - 1; s >= 0; s --) {
      int gap = gapArray[s];
      for (int i = gap; i < len; i++) {
        int tmp = array[i];
        int j = i;
        while (j >= gap && array[j - gap] > tmp) {
          array[j] = array[j - gap];
          j -= gap;
        }
        if (i != j) {
          array[j] = tmp;
        }
      }
    }
  }

  /**
   * codegen by javapoet
   */
  @Override
  public void sort(long[] array) {
    int len = array.length;
    int[] gapArray = genGap(len);
    for (int s = gapArray.length - 1; s >= 0; s --) {
      int gap = gapArray[s];
      for (int i = gap; i < len; i++) {
        long tmp = array[i];
        int j = i;
        while (j >= gap && array[j - gap] > tmp) {
          array[j] = array[j - gap];
          j -= gap;
        }
        if (i != j) {
          array[j] = tmp;
        }
      }
    }
  }

  /**
   * codegen by javapoet
   */
  @Override
  public void sort(float[] array) {
    int len = array.length;
    int[] gapArray = genGap(len);
    for (int s = gapArray.length - 1; s >= 0; s --) {
      int gap = gapArray[s];
      for (int i = gap; i < len; i++) {
        float tmp = array[i];
        int j = i;
        while (j >= gap && array[j - gap] > tmp) {
          array[j] = array[j - gap];
          j -= gap;
        }
        if (i != j) {
          array[j] = tmp;
        }
      }
    }
  }

  /**
   * codegen by javapoet
   */
  @Override
  public void sort(double[] array) {
    int len = array.length;
    int[] gapArray = genGap(len);
    for (int s = gapArray.length - 1; s >= 0; s --) {
      int gap = gapArray[s];
      for (int i = gap; i < len; i++) {
        double tmp = array[i];
        int j = i;
        while (j >= gap && array[j - gap] > tmp) {
          array[j] = array[j - gap];
          j -= gap;
        }
        if (i != j) {
          array[j] = tmp;
        }
      }
    }
  }

  /**
   * 生成gap
   */
  public int[] genGap(int len) {
    int[] sedgewickArray = new int[len];
    int gapLen = 0;
    for (int n = 0, i = 0; n < len; n += 2, i++) {
      int even = 9 * ((int) Math.pow(4, i) - (int) Math.pow(2, i)) + 1;
      int odd = (int) Math.pow(4, i + 2) - 3 * (int) Math.pow(2, i + 2) + 1;
      if (even > len / 2) {
        break;
      }
      sedgewickArray[n] = even;
      gapLen ++;
      if (odd > len / 2) {
        break;
      }
      sedgewickArray[n + 1] = odd;
      gapLen ++;

    }
    int[] gapArray = new int[gapLen];
    // 仅仅复制有效步长，也就是n个有效的
    System.arraycopy(sedgewickArray, 0, gapArray, 0, gapLen);
    return gapArray;
  }

}
