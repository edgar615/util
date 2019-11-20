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
 * 二元组.
 */
public class Tuple2<T1, T2> {

  private final T1 t1;

  private final T2 t2;

  private Tuple2(T1 t1, T2 t2) {
    this.t1 = t1;
    this.t2 = t2;
  }

  public static <T1, T2> Tuple2<T1, T2> create(T1 t1, T2 t2) {
    return new Tuple2<>(t1, t2);
  }

  @Override
  public int hashCode() {
    int result = t1 != null ? t1.hashCode() : 0;

    result = 31 * result + (t2 != null ? t2.hashCode() : 0);

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

    final Tuple2 tuple2 = (Tuple2) o;

    if (t1 != null ? !t1.equals(tuple2.t1) : tuple2.t1 != null) {
      return false;
    }

    if (t2 != null ? !t2.equals(tuple2.t2) : tuple2.t2 != null) {
      return false;
    }

    return true;
  }

  @Override
  public String toString() {
    return String.format("(%s, %s)", getT1(), getT2());
  }

  public T1 getT1() {
    return t1;
  }

  public T2 getT2() {
    return t2;
  }

}
