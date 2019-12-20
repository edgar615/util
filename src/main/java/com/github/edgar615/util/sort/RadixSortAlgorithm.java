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
 * 基数排序
 *
 * **基数排序**（英语：Radix sort）是一种非比较型整数[排序算法，其原理是将整数按位数切割成不同的数字，然后按每个位数分别比较。
 * 由于整数也可以表达字符串（比如名字或日期）和特定格式的浮点数，所以基数排序也不是只能使用于整数。
 *
 * 算法步骤：将所有待比较数值（正整数）统一为同样的数字长度，数字较短的数前面补零。然后，从最低位开始，依次进行一次排序。这样从最低位排序一直到最高位排序完成以后，数列就变成一个有序序列。
 * <ul>
 * <li>平均时间复杂度：O(n * k)</li>
 * <li>最好情况：O(n * k)</li>
 * <li>最坏情况：O(n * n)</li>
 * <li>空间复杂度：O(n + k)</li>
 * <li>内存占用：Out-place：占用额外内存</li>
 * <li>稳定性：稳定</li>
 * </ul>
 */
public class RadixSortAlgorithm implements SortAlgorithm {

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
    int maximumNumber = findMaximumNumberIn(array);

    int numberOfDigits = calculateNumberOfDigitsIn(maximumNumber);

    int placeValue = 1;

    while (numberOfDigits-- > 0) {
      applyCountingSortOn(array, placeValue);
      placeValue *= 10;
    }
  }

  private void applyCountingSortOn(int[] numbers, int placeValue) {
    int range = 10;

    int length = numbers.length;
    int[] frequency = new int[range];
    int[] sortedValues = new int[length];

    for (int i = 0; i < length; i++) {
      int digit = (numbers[i] / placeValue) % range;
      frequency[digit]++;
    }

    for (int i = 1; i < range; i++) {
      frequency[i] += frequency[i - 1];
    }

    for (int i = length - 1; i >= 0; i--) {
      int digit = (numbers[i] / placeValue) % range;
      sortedValues[frequency[digit] - 1] = numbers[i];
      frequency[digit]--;
    }

    System.arraycopy(sortedValues, 0, numbers, 0, length);
  }

  private int calculateNumberOfDigitsIn(int number) {
    return (int) Math.log10(number) + 1;
  }

  private int findMaximumNumberIn(int[] arr) {
    return Arrays.stream(arr).max().getAsInt();
  }

}
