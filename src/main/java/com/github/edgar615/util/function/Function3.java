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

package com.github.edgar615.util.function;

import java.util.Objects;
import java.util.function.Function;

/**
 * Created by Edgar on 2016/8/1.
 *
 * @author Edgar  Date 2016/8/1
 */
@FunctionalInterface
public interface Function3<T1, T2, T3, R> {

  R apply(T1 t1, T2 t2, T3 t3);

  default <V> Function3<T1, T2, T3, V> andThen(Function<? super R, ? extends V> after) {
    Objects.requireNonNull(after);
    return (T1 t1, T2 t2, T3 t3) -> after.apply(apply(t1, t2, t3));
  }
}
