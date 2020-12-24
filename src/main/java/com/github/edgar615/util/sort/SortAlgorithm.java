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
 * 排序算法
 *
 * 基础类型的方法都是通过javapoet生成的
 */
public interface SortAlgorithm {

  <T extends Comparable<? super T>> void sort(List<T> list);

  default void sort(byte[] array) {}

  default void sort(char[] array) {}

  default void sort(short[] array) {}

  default void sort(int[] array) {}

  default void sort(long[] array) {}

  default void sort(float[] array) {}

  default void sort(double[] array) {}
}
