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

public class SedgewickShellSortCodegen {

  @Test
  public void testShellCodegen() throws IOException {
    TypeSpec sortType = TypeSpec.classBuilder("Shell")
        .addModifiers(Modifier.PUBLIC)
        .addMethod(createMethod(byte[].class, byte.class))
        .addMethod(createMethod(char[].class, char.class))
        .addMethod(createMethod(short[].class, short.class))
        .addMethod(createMethod(int[].class, int.class))
        .addMethod(createMethod(long[].class, long.class))
        .addMethod(createMethod(float[].class, float.class))
        .addMethod(createMethod(double[].class, double.class))
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
        .addStatement("int[] gapArray = genGap(len)")
        .beginControlFlow("for (int s = gapArray.length - 1; s >= 0; s --)")
        .addStatement("int gap = gapArray[s]")
        .beginControlFlow("for (int i = gap; i < len; i++)")
        .addStatement(primitiveTypeStr + " tmp = array[i]")
        .addStatement("int j = i")
        .beginControlFlow("while (j >= gap && array[j - gap] > tmp)")
        .addStatement("array[j] = array[j - gap]")
        .addStatement("j -= gap")
        .endControlFlow()
        .beginControlFlow("if (i != j)")
        .addStatement("array[j] = tmp")
        .endControlFlow()
        .endControlFlow()
        .endControlFlow()
        .build();
  }
}
