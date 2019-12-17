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
 *
 */
public class Sorts {

  /**
   * 冒泡排序
   */
  public static <T extends Comparable<? super T>> void bubble(List<T> list) {
    new BubbleSortAlgorithm().sort(list);
  }

  /**
   * 冒泡排序
   * codegen by javapoet
   */
  public static void bubble(byte[] array) {
    new BubbleSortAlgorithm().sort(array);
  }

  /**
   * 冒泡排序
   * codegen by javapoet
   */
  public static void bubble(char[] array) {
    new BubbleSortAlgorithm().sort(array);
  }

  /**
   * 冒泡排序
   * codegen by javapoet
   */
  public static void bubble(short[] array) {
    new BubbleSortAlgorithm().sort(array);
  }

  /**
   * 冒泡排序
   * codegen by javapoet
   */
  public static void bubble(int[] array) {
    new BubbleSortAlgorithm().sort(array);
  }

  /**
   * 冒泡排序
   * codegen by javapoet
   */
  public static void bubble(long[] array) {
    new BubbleSortAlgorithm().sort(array);
  }

  /**
   * 冒泡排序
   * codegen by javapoet
   */
  public static void bubble(float[] array) {
    new BubbleSortAlgorithm().sort(array);
  }

  /**
   * 冒泡排序
   * codegen by javapoet
   */
  public static void bubble(double[] array) {
    new BubbleSortAlgorithm().sort(array);
  }

  /**
   * 插入排序
   */
  public static <T extends Comparable<? super T>> void insert(List<T> list) {
    new InsertSortAlgorithm().sort(list);
  }

  /**
   * 插入排序
   * codegen by javapoet
   */
  public static void insert(byte[] array) {
    new InsertSortAlgorithm().sort(array);
  }

  /**
   * 插入排序
   * codegen by javapoet
   */
  public static void insert(char[] array) {
    new InsertSortAlgorithm().sort(array);
  }

  /**
   * 插入排序
   * codegen by javapoet
   */
  public static void insert(short[] array) {
    new InsertSortAlgorithm().sort(array);
  }

  /**
   * 插入排序
   * codegen by javapoet
   */
  public static void insert(int[] array) {
    new InsertSortAlgorithm().sort(array);
  }

  /**
   * 插入排序
   * codegen by javapoet
   */
  public static void insert(long[] array) {
    new InsertSortAlgorithm().sort(array);
  }

  /**
   * 插入排序
   * codegen by javapoet
   */
  public static void insert(float[] array) {
    new InsertSortAlgorithm().sort(array);
  }

  /**
   * 插入排序
   * codegen by javapoet
   */
  public static void insert(double[] array) {
    new InsertSortAlgorithm().sort(array);
  }

  /**
   * 选择排序
   */
  public static <T extends Comparable<? super T>> void selection(List<T> list) {
    new SelectionSortAlgorithm().sort(list);
  }

  /**
   * 选择排序
   * codegen by javapoet
   */
  public static void selection(byte[] array) {
    new SelectionSortAlgorithm().sort(array);
  }

  /**
   * 选择排序
   * codegen by javapoet
   */
  public static void selection(char[] array) {
    new SelectionSortAlgorithm().sort(array);
  }

  /**
   * 选择排序
   * codegen by javapoet
   */
  public static void selection(short[] array) {
    new SelectionSortAlgorithm().sort(array);
  }

  /**
   * 选择排序
   * codegen by javapoet
   */
  public static void selection(int[] array) {
    new SelectionSortAlgorithm().sort(array);
  }

  /**
   * 选择排序
   * codegen by javapoet
   */
  public static void selection(long[] array) {
    new SelectionSortAlgorithm().sort(array);
  }

  /**
   * 选择排序
   * codegen by javapoet
   */
  public static void selection(float[] array) {
    new SelectionSortAlgorithm().sort(array);
  }

  /**
   * 选择排序
   * codegen by javapoet
   */
  public static void selection(double[] array) {
    new SelectionSortAlgorithm().sort(array);
  }

  /**
   * 使用Sedgewick增量的希尔排序
   */
  public static <T extends Comparable<? super T>> void shell(List<T> list) {
    new SedgewickShellSortAlgorithm().sort(list);
  }

  /**
   * 使用Sedgewick增量的希尔排序
   * codegen by javapoet
   */
  public static void shell(byte[] array) {
    new SedgewickShellSortAlgorithm().sort(array);
  }

  /**
   * 使用Sedgewick增量的希尔排序
   * codegen by javapoet
   */
  public static void shell(char[] array) {
    new SedgewickShellSortAlgorithm().sort(array);
  }

  /**
   * 使用Sedgewick增量的希尔排序
   * codegen by javapoet
   */
  public static void shell(short[] array) {
    new SedgewickShellSortAlgorithm().sort(array);
  }

  /**
   * 使用Sedgewick增量的希尔排序
   * codegen by javapoet
   */
  public static void shell(int[] array) {
    new SedgewickShellSortAlgorithm().sort(array);
  }

  /**
   * 使用Sedgewick增量的希尔排序
   * codegen by javapoet
   */
  public static void shell(long[] array) {
    new SedgewickShellSortAlgorithm().sort(array);
  }

  /**
   * 使用Sedgewick增量的希尔排序
   * codegen by javapoet
   */
  public static void shell(float[] array) {
    new SedgewickShellSortAlgorithm().sort(array);
  }

  /**
   * 使用Sedgewick增量的希尔排序
   * codegen by javapoet
   */
  public static void shell(double[] array) {
    new SedgewickShellSortAlgorithm().sort(array);
  }

  /**
   * 迭代方式的归并排序
   */
  public static <T extends Comparable<? super T>> void merge(List<T> list) {
    new IteratorMergeSortAlgorithm().sort(list);
  }

  /**
   * 迭代方式的归并排序
   * codegen by javapoet
   */
  public static void merge(byte[] array) {
    new IteratorMergeSortAlgorithm().sort(array);
  }

  /**
   * 迭代方式的归并排序
   * codegen by javapoet
   */
  public static void merge(char[] array) {
    new IteratorMergeSortAlgorithm().sort(array);
  }

  /**
   * 迭代方式的归并排序
   * codegen by javapoet
   */
  public static void merge(short[] array) {
    new IteratorMergeSortAlgorithm().sort(array);
  }

  /**
   * 迭代方式的归并排序
   * codegen by javapoet
   */
  public static void merge(int[] array) {
    new IteratorMergeSortAlgorithm().sort(array);
  }

  /**
   * 迭代方式的归并排序
   * codegen by javapoet
   */
  public static void merge(long[] array) {
    new IteratorMergeSortAlgorithm().sort(array);
  }

  /**
   * 迭代方式的归并排序
   * codegen by javapoet
   */
  public static void merge(float[] array) {
    new IteratorMergeSortAlgorithm().sort(array);
  }

  /**
   * 迭代方式的归并排序
   * codegen by javapoet
   */
  public static void merge(double[] array) {
    new IteratorMergeSortAlgorithm().sort(array);
  }

  /**
   * 快速排序
   */
  public static <T extends Comparable<? super T>> void quick(List<T> list) {
    new QuickSortAlgorithm().sort(list);
  }

  /**
   * 快速排序
   * codegen by javapoet
   */
  public static void quick(byte[] array) {
    new QuickSortAlgorithm().sort(array);
  }

  /**
   * 快速排序
   * codegen by javapoet
   */
  public static void quick(char[] array) {
    new QuickSortAlgorithm().sort(array);
  }

  /**
   * 快速排序
   * codegen by javapoet
   */
  public static void quick(short[] array) {
    new QuickSortAlgorithm().sort(array);
  }

  /**
   * 快速排序
   * codegen by javapoet
   */
  public static void quick(int[] array) {
    new QuickSortAlgorithm().sort(array);
  }

  /**
   * 快速排序
   * codegen by javapoet
   */
  public static void quick(long[] array) {
    new QuickSortAlgorithm().sort(array);
  }

  /**
   * 快速排序
   * codegen by javapoet
   */
  public static void quick(float[] array) {
    new QuickSortAlgorithm().sort(array);
  }

  /**
   * 快速排序
   * codegen by javapoet
   */
  public static void quick(double[] array) {
    new QuickSortAlgorithm().sort(array);
  }

}
