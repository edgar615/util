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
   * 使用默认增量的希尔排序
   */
  public static <T extends Comparable<? super T>> void shell(List<T> list) {
    new ShellSortAlgorithm().sort(list);
  }

  /**
   * 希尔排序
   * codegen by javapoet
   */
  public static void shell(byte[] array) {
    new ShellSortAlgorithm().sort(array);
  }

  /**
   * 希尔排序
   * codegen by javapoet
   */
  public static void shell(char[] array) {
    new ShellSortAlgorithm().sort(array);
  }

  /**
   * 希尔排序
   * codegen by javapoet
   */
  public static void shell(short[] array) {
    new ShellSortAlgorithm().sort(array);
  }

  /**
   * 希尔排序
   * codegen by javapoet
   */
  public static void shell(int[] array) {
    new ShellSortAlgorithm().sort(array);
  }

  /**
   * 希尔排序
   * codegen by javapoet
   */
  public static void shell(long[] array) {
    new ShellSortAlgorithm().sort(array);
  }

  /**
   * 希尔排序
   * codegen by javapoet
   */
  public static void shell(float[] array) {
    new ShellSortAlgorithm().sort(array);
  }

  /**
   * 希尔排序
   * codegen by javapoet
   */
  public static void shell(double[] array) {
    new ShellSortAlgorithm().sort(array);
  }

  /**
   * 使用Sedgewick增量的希尔排序
   */
  public static <T extends Comparable<? super T>> void shellSedgewick(List<T> list) {
    new SedgewickShellSortAlgorithm().sort(list);
  }

  /**
   * 使用Sedgewick增量的希尔排序
   * codegen by javapoet
   */
  public static void shellSedgewick(byte[] array) {
    new SedgewickShellSortAlgorithm().sort(array);
  }

  /**
   * 使用Sedgewick增量的希尔排序
   * codegen by javapoet
   */
  public static void shellSedgewick(char[] array) {
    new SedgewickShellSortAlgorithm().sort(array);
  }

  /**
   * 使用Sedgewick增量的希尔排序
   * codegen by javapoet
   */
  public static void shellSedgewick(short[] array) {
    new SedgewickShellSortAlgorithm().sort(array);
  }

  /**
   * 使用Sedgewick增量的希尔排序
   * codegen by javapoet
   */
  public static void shellSedgewick(int[] array) {
    new SedgewickShellSortAlgorithm().sort(array);
  }

  /**
   * 使用Sedgewick增量的希尔排序
   * codegen by javapoet
   */
  public static void shellSedgewick(long[] array) {
    new SedgewickShellSortAlgorithm().sort(array);
  }

  /**
   * 使用Sedgewick增量的希尔排序
   * codegen by javapoet
   */
  public static void shellSedgewick(float[] array) {
    new SedgewickShellSortAlgorithm().sort(array);
  }

  /**
   * 使用Sedgewick增量的希尔排序
   * codegen by javapoet
   */
  public static void shellSedgewick(double[] array) {
    new SedgewickShellSortAlgorithm().sort(array);
  }

  /**
   * 使用Hibbard增量的希尔排序
   */
  public static <T extends Comparable<? super T>> void shellHibbard(List<T> list) {
    new HibbardShellSortAlgorithm().sort(list);
  }

  /**
   * 使用Hibbard增量的希尔排序
   * codegen by javapoet
   */
  public static void shellHibbard(byte[] array) {
    new HibbardShellSortAlgorithm().sort(array);
  }

  /**
   * 使用Hibbard增量的希尔排序
   * codegen by javapoet
   */
  public static void shellHibbard(char[] array) {
    new HibbardShellSortAlgorithm().sort(array);
  }

  /**
   * 使用Hibbard增量的希尔排序
   * codegen by javapoet
   */
  public static void shellHibbard(short[] array) {
    new HibbardShellSortAlgorithm().sort(array);
  }

  /**
   * 使用Hibbard增量的希尔排序
   * codegen by javapoet
   */
  public static void shellHibbard(int[] array) {
    new HibbardShellSortAlgorithm().sort(array);
  }

  /**
   * 使用Hibbard增量的希尔排序
   * codegen by javapoet
   */
  public static void shellHibbard(long[] array) {
    new HibbardShellSortAlgorithm().sort(array);
  }

  /**
   * 使用Hibbard增量的希尔排序
   * codegen by javapoet
   */
  public static void shellHibbard(float[] array) {
    new HibbardShellSortAlgorithm().sort(array);
  }

  /**
   * 使用Hibbard增量的希尔排序
   * codegen by javapoet
   */
  public static void shellHibbard(double[] array) {
    new HibbardShellSortAlgorithm().sort(array);
  }

  /**
   * 使用Knuth增量的希尔排序
   */
  public static <T extends Comparable<? super T>> void shellKnuth(List<T> list) {
    new KnuthShellSortAlgorithm().sort(list);
  }

  /**
   * 使用Knuth增量的希尔排序
   * codegen by javapoet
   */
  public static void shellKnuth(byte[] array) {
    new KnuthShellSortAlgorithm().sort(array);
  }

  /**
   * 使用Knuth增量的希尔排序
   * codegen by javapoet
   */
  public static void shellKnuth(char[] array) {
    new KnuthShellSortAlgorithm().sort(array);
  }

  /**
   * 使用Knuth增量的希尔排序
   * codegen by javapoet
   */
  public static void shellKnuth(short[] array) {
    new KnuthShellSortAlgorithm().sort(array);
  }

  /**
   * 使用Knuth增量的希尔排序
   * codegen by javapoet
   */
  public static void shellKnuth(int[] array) {
    new KnuthShellSortAlgorithm().sort(array);
  }

  /**
   * 使用Knuth增量的希尔排序
   * codegen by javapoet
   */
  public static void shellKnuth(long[] array) {
    new KnuthShellSortAlgorithm().sort(array);
  }

  /**
   * 使用Knuth增量的希尔排序
   * codegen by javapoet
   */
  public static void shellKnuth(float[] array) {
    new KnuthShellSortAlgorithm().sort(array);
  }

  /**
   * 使用Knuth增量的希尔排序
   * codegen by javapoet
   */
  public static void shellKnuth(double[] array) {
    new KnuthShellSortAlgorithm().sort(array);
  }

  /**
   * 递归方式的归并排序
   */
  public static <T extends Comparable<? super T>> void merge(List<T> list) {
    new MergeSortAlgorithm().sort(list);
  }

  /**
   * 迭代方式的归并排序
   */
  public static <T extends Comparable<? super T>> void mergeIterator(List<T> list) {
    new IteratorMergeSortAlgorithm().sort(list);
  }
}
