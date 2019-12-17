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

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.lang.reflect.Type;
import javax.lang.model.element.Modifier;
import org.junit.Test;

public class SortsCodegen {

  @Test
  public void testBubbleCodegen() throws IOException {
    TypeSpec sortType = TypeSpec.classBuilder("Sorts")
        .addModifiers(Modifier.PUBLIC)
        .addMethod(createMethod("bubble", BubbleSortAlgorithm.class, byte[].class, "冒泡排序"))
        .addMethod(createMethod("bubble", BubbleSortAlgorithm.class, char[].class, "冒泡排序"))
        .addMethod(createMethod("bubble", BubbleSortAlgorithm.class, short[].class, "冒泡排序"))
        .addMethod(createMethod("bubble", BubbleSortAlgorithm.class, int[].class, "冒泡排序"))
        .addMethod(createMethod("bubble", BubbleSortAlgorithm.class, long[].class, "冒泡排序"))
        .addMethod(createMethod("bubble", BubbleSortAlgorithm.class, float[].class, "冒泡排序"))
        .addMethod(createMethod("bubble", BubbleSortAlgorithm.class, double[].class, "冒泡排序"))
        .build();

    JavaFile javaFile = JavaFile.builder("com.github.edgar615.util.sort", sortType)
        .build();

    javaFile.writeTo(System.out);
  }

  @Test
  public void testInsertCodegen() throws IOException {
    TypeSpec sortType = TypeSpec.classBuilder("Sorts")
        .addModifiers(Modifier.PUBLIC)
        .addMethod(createMethod("insert", InsertSortAlgorithm.class, byte[].class, "插入排序"))
        .addMethod(createMethod("insert", InsertSortAlgorithm.class, char[].class, "插入排序"))
        .addMethod(createMethod("insert", InsertSortAlgorithm.class, short[].class, "插入排序"))
        .addMethod(createMethod("insert", InsertSortAlgorithm.class, int[].class, "插入排序"))
        .addMethod(createMethod("insert", InsertSortAlgorithm.class, long[].class, "插入排序"))
        .addMethod(createMethod("insert", InsertSortAlgorithm.class, float[].class, "插入排序"))
        .addMethod(createMethod("insert", InsertSortAlgorithm.class, double[].class, "插入排序"))
        .build();

    JavaFile javaFile = JavaFile.builder("com.github.edgar615.util.sort", sortType)
        .build();

    javaFile.writeTo(System.out);
  }

  @Test
  public void testSelectionCodegen() throws IOException {
    TypeSpec sortType = TypeSpec.classBuilder("Sorts")
        .addModifiers(Modifier.PUBLIC)
        .addMethod(createMethod("selection", SelectionSortAlgorithm.class, byte[].class, "选择排序"))
        .addMethod(createMethod("selection", SelectionSortAlgorithm.class, char[].class, "选择排序"))
        .addMethod(createMethod("selection", SelectionSortAlgorithm.class, short[].class, "选择排序"))
        .addMethod(createMethod("selection", SelectionSortAlgorithm.class, int[].class, "选择排序"))
        .addMethod(createMethod("selection", SelectionSortAlgorithm.class, long[].class, "选择排序"))
        .addMethod(createMethod("selection", SelectionSortAlgorithm.class, float[].class, "选择排序"))
        .addMethod(createMethod("selection", SelectionSortAlgorithm.class, double[].class, "选择排序"))
        .build();

    JavaFile javaFile = JavaFile.builder("com.github.edgar615.util.sort", sortType)
        .build();

    javaFile.writeTo(System.out);
  }


  @Test
  public void testShellCodegen() throws IOException {
    TypeSpec sortType = TypeSpec.classBuilder("Sorts")
        .addModifiers(Modifier.PUBLIC)
        .addMethod(createMethod("shell", ShellSortAlgorithm.class, byte[].class, "希尔排序"))
        .addMethod(createMethod("shell", ShellSortAlgorithm.class, char[].class, "希尔排序"))
        .addMethod(createMethod("shell", ShellSortAlgorithm.class, short[].class, "希尔排序"))
        .addMethod(createMethod("shell", ShellSortAlgorithm.class, int[].class, "希尔排序"))
        .addMethod(createMethod("shell", ShellSortAlgorithm.class, long[].class, "希尔排序"))
        .addMethod(createMethod("shell", ShellSortAlgorithm.class, float[].class, "希尔排序"))
        .addMethod(createMethod("shell", ShellSortAlgorithm.class, double[].class, "希尔排序"))
        .build();

    JavaFile javaFile = JavaFile.builder("com.github.edgar615.util.sort", sortType)
        .build();

    javaFile.writeTo(System.out);
  }

  @Test
  public void testShellKnuthCodegen() throws IOException {
    TypeSpec sortType = TypeSpec.classBuilder("Sorts")
        .addModifiers(Modifier.PUBLIC)
        .addMethod(createMethod("shellKnuth", KnuthShellSortAlgorithm.class, byte[].class, "使用Knuth增量的希尔排序"))
        .addMethod(createMethod("shellKnuth", KnuthShellSortAlgorithm.class, char[].class, "使用Knuth增量的希尔排序"))
        .addMethod(createMethod("shellKnuth", KnuthShellSortAlgorithm.class, short[].class, "使用Knuth增量的希尔排序"))
        .addMethod(createMethod("shellKnuth", KnuthShellSortAlgorithm.class, int[].class, "使用Knuth增量的希尔排序"))
        .addMethod(createMethod("shellKnuth", KnuthShellSortAlgorithm.class, long[].class, "使用Knuth增量的希尔排序"))
        .addMethod(createMethod("shellKnuth", KnuthShellSortAlgorithm.class, float[].class, "使用Knuth增量的希尔排序"))
        .addMethod(createMethod("shellKnuth", KnuthShellSortAlgorithm.class, double[].class, "使用Knuth增量的希尔排序"))
        .build();

    JavaFile javaFile = JavaFile.builder("com.github.edgar615.util.sort", sortType)
        .build();

    javaFile.writeTo(System.out);
  }

  @Test
  public void testShellSedgewickCodegen() throws IOException {
    TypeSpec sortType = TypeSpec.classBuilder("Sorts")
        .addModifiers(Modifier.PUBLIC)
        .addMethod(createMethod("shellSedgewick", SedgewickShellSortAlgorithm.class, byte[].class, "使用Sedgewick增量的希尔排序"))
        .addMethod(createMethod("shellSedgewick", SedgewickShellSortAlgorithm.class, char[].class, "使用Sedgewick增量的希尔排序"))
        .addMethod(createMethod("shellSedgewick", SedgewickShellSortAlgorithm.class, short[].class, "使用Sedgewick增量的希尔排序"))
        .addMethod(createMethod("shellSedgewick", SedgewickShellSortAlgorithm.class, int[].class, "使用Sedgewick增量的希尔排序"))
        .addMethod(createMethod("shellSedgewick", SedgewickShellSortAlgorithm.class, long[].class, "使用Sedgewick增量的希尔排序"))
        .addMethod(createMethod("shellSedgewick", SedgewickShellSortAlgorithm.class, float[].class, "使用Sedgewick增量的希尔排序"))
        .addMethod(createMethod("shellSedgewick", SedgewickShellSortAlgorithm.class, double[].class, "使用Sedgewick增量的希尔排序"))
        .build();

    JavaFile javaFile = JavaFile.builder("com.github.edgar615.util.sort", sortType)
        .build();

    javaFile.writeTo(System.out);
  }

  @Test
  public void testShellHibbardCodegen() throws IOException {
    TypeSpec sortType = TypeSpec.classBuilder("Sorts")
        .addModifiers(Modifier.PUBLIC)
        .addMethod(createMethod("shellHibbard", HibbardShellSortAlgorithm.class, byte[].class, "使用Hibbard增量的希尔排序"))
        .addMethod(createMethod("shellHibbard", HibbardShellSortAlgorithm.class, char[].class, "使用Hibbard增量的希尔排序"))
        .addMethod(createMethod("shellHibbard", HibbardShellSortAlgorithm.class, short[].class, "使用Hibbard增量的希尔排序"))
        .addMethod(createMethod("shellHibbard", HibbardShellSortAlgorithm.class, int[].class, "使用Hibbard增量的希尔排序"))
        .addMethod(createMethod("shellHibbard", HibbardShellSortAlgorithm.class, long[].class, "使用Hibbard增量的希尔排序"))
        .addMethod(createMethod("shellHibbard", HibbardShellSortAlgorithm.class, float[].class, "使用Hibbard增量的希尔排序"))
        .addMethod(createMethod("shellHibbard", HibbardShellSortAlgorithm.class, double[].class, "使用Hibbard增量的希尔排序"))
        .build();

    JavaFile javaFile = JavaFile.builder("com.github.edgar615.util.sort", sortType)
        .build();

    javaFile.writeTo(System.out);
  }

  @Test
  public void testMergeSort() throws IOException {
    TypeSpec sortType = TypeSpec.classBuilder("Sorts")
        .addModifiers(Modifier.PUBLIC)
        .addMethod(createMethod("merge", MergeSortAlgorithm.class, byte[].class, "递归方式的归并排序"))
        .addMethod(createMethod("merge", MergeSortAlgorithm.class, char[].class, "递归方式的归并排序"))
        .addMethod(createMethod("merge", MergeSortAlgorithm.class, short[].class, "递归方式的归并排序"))
        .addMethod(createMethod("merge", MergeSortAlgorithm.class, int[].class, "递归方式的归并排序"))
        .addMethod(createMethod("merge", MergeSortAlgorithm.class, long[].class, "递归方式的归并排序"))
        .addMethod(createMethod("merge", MergeSortAlgorithm.class, float[].class, "递归方式的归并排序"))
        .addMethod(createMethod("merge", MergeSortAlgorithm.class, double[].class, "递归方式的归并排序"))
        .build();

    JavaFile javaFile = JavaFile.builder("com.github.edgar615.util.sort", sortType)
        .build();

    javaFile.writeTo(System.out);
  }

  @Test
  public void testMergeIterator() throws IOException {
    TypeSpec sortType = TypeSpec.classBuilder("Sorts")
        .addModifiers(Modifier.PUBLIC)
        .addMethod(createMethod("mergeIterator", IteratorMergeSortAlgorithm.class, byte[].class, "迭代方式的归并排序"))
        .addMethod(createMethod("mergeIterator", IteratorMergeSortAlgorithm.class, char[].class, "迭代方式的归并排序"))
        .addMethod(createMethod("mergeIterator", IteratorMergeSortAlgorithm.class, short[].class, "迭代方式的归并排序"))
        .addMethod(createMethod("mergeIterator", IteratorMergeSortAlgorithm.class, int[].class, "迭代方式的归并排序"))
        .addMethod(createMethod("mergeIterator", IteratorMergeSortAlgorithm.class, long[].class, "迭代方式的归并排序"))
        .addMethod(createMethod("mergeIterator", IteratorMergeSortAlgorithm.class, float[].class, "迭代方式的归并排序"))
        .addMethod(createMethod("mergeIterator", IteratorMergeSortAlgorithm.class, double[].class, "迭代方式的归并排序"))
        .build();

    JavaFile javaFile = JavaFile.builder("com.github.edgar615.util.sort", sortType)
        .build();

    javaFile.writeTo(System.out);
  }

  private MethodSpec createMethod(String methodName, Type sortClassType, Type arrayType, String comment) {
    return MethodSpec.methodBuilder(methodName)
        .addModifiers(Modifier.PUBLIC,Modifier.STATIC)
        .returns(void.class)
        .addJavadoc(comment)
        .addJavadoc("\ncodegen by javapoet\n")
        .addParameter(arrayType, "array")
        .addStatement("new $T().sort(array)", sortClassType)
        .build();
  }
}
