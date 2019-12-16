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

public class InsertSortCodegen {

  @Test
  public void testInsertCodegen() throws IOException {
    TypeSpec sortType = TypeSpec.classBuilder("Insert")
        .addModifiers(Modifier.PUBLIC)
        .addMethod(createMethod(byte[].class, byte.class))
        .addMethod(createMethod(char[].class, char.class))
        .addMethod(createMethod(short[].class, short.class))
        .addMethod(createMethod(int[].class, int.class))
        .addMethod(createMethod(long[].class, long.class))
        .addMethod(createMethod(float[].class, float.class))
        .addMethod(createMethod(double[].class, double.class))
        .addMethod(createBinaraySearch(byte[].class, byte.class))
        .addMethod(createBinaraySearch(char[].class, char.class))
        .addMethod(createBinaraySearch(short[].class, short.class))
        .addMethod(createBinaraySearch(int[].class, int.class))
        .addMethod(createBinaraySearch(long[].class, long.class))
        .addMethod(createBinaraySearch(float[].class, float.class))
        .addMethod(createBinaraySearch(double[].class, double.class))
        .build();

    JavaFile javaFile = JavaFile.builder("com.github.edgar615.util.sort", sortType)
        .build();

    javaFile.writeTo(System.out);
  }

  private MethodSpec createMethod(Type arrayType, Type primitiveType) {
    String primitiveTypeStr = primitiveType.getTypeName();
    return MethodSpec.methodBuilder("sort")
        .addModifiers(Modifier.PUBLIC)
        .returns(void.class)
        .addAnnotation(Override.class)
        .addJavadoc("codegen by javapoet\n")
        .addParameter(arrayType, "array")
        .addStatement("int len = array.length")
        .beginControlFlow("for (int i = 1; i < len; i++)")
        .addStatement(primitiveTypeStr + " insertion = array[i]")
        .addStatement("int low = binaraySearch(array, i - 1, insertion)")
        .beginControlFlow("for (int j = i; j > low; j--)")
        .addStatement("array[j] = array[j - 1]")
        .endControlFlow()
        .addStatement("array[low] = insertion")
        .endControlFlow()
        .build();
  }

  private MethodSpec createBinaraySearch(Type arrayType, Type primitiveType) {
    String primitiveTypeStr = primitiveType.getTypeName();
    return MethodSpec.methodBuilder("binaraySearch")
        .addModifiers(Modifier.PUBLIC)
        .returns(int.class)
        .addJavadoc("codegen by javapoet\n")
        .addParameter(arrayType, "array")
        .addParameter(int.class, "high")
        .addParameter(primitiveType, "target")
        .addStatement("int low = 0")
        .beginControlFlow("while (low <= high)")
        .addStatement("int middle = low + (high - low) / 2")
        .addStatement(primitiveTypeStr + " obj = array[middle]")
        .beginControlFlow("if (target > obj)")
        .addStatement("low = middle + 1")
        .nextControlFlow("else")
        .addStatement("high = middle - 1")
        .endControlFlow()
        .endControlFlow()
        .addStatement("return low")
        .build();
  }
}
