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

package com.github.edgar615.util.collection;

/**
 * 四元组.
 */
public class Tuple4<T1, T2, T3, T4> {

  private final T1 t1;

  private final T2 t2;

  private final T3 t3;

  private final T4 t4;

  private Tuple4(T1 t1, T2 t2, T3 t3, T4 t4) {
    this.t1 = t1;
    this.t2 = t2;
    this.t3 = t3;
    this.t4 = t4;
  }

  public static <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> create(T1 t1, T2 t2, T3 t3, T4 t4) {
    return new Tuple4<>(t1, t2, t3, t4);
  }

  @Override
  public int hashCode() {
    int result = t1 != null ? t1.hashCode() : 0;

    result = 31 * result + (t2 != null ? t2.hashCode() : 0);

    result = 31 * result + (t3 != null ? t3.hashCode() : 0);

    result = 31 * result + (t4 != null ? t4.hashCode() : 0);

    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    final Tuple4 tuple2 = (Tuple4) o;

    if (t1 != null ? !t1.equals(tuple2.t1) : tuple2.t1 != null) {
      return false;
    }

    if (t2 != null ? !t2.equals(tuple2.t2) : tuple2.t2 != null) {
      return false;
    }

    if (t3 != null ? !t3.equals(tuple2.t3) : tuple2.t3 != null) {
      return false;
    }

    if (t4 != null ? !t4.equals(tuple2.t4) : tuple2.t4 != null) {
      return false;
    }

    return true;
  }

  @Override
  public String toString() {
    return String.format("(%s, %s, %s, %s)", getT1(), getT2(), getT3(), getT4());
  }

  public T1 getT1() {
    return t1;
  }

  public T2 getT2() {
    return t2;
  }

  public T3 getT3() {
    return t3;
  }

  public T4 getT4() {
    return t4;
  }

}
