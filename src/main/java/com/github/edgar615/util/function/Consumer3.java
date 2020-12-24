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

/**
 * Created by Edgar on 2016/8/1.
 *
 * @author Edgar  Date 2016/8/1
 */
@FunctionalInterface
public interface Consumer3<T1, T2, T3> {

  void accept(T1 t1, T2 t2, T3 t3);

  default Consumer3<T1, T2, T3> andThen(Consumer3<T1, T2, T3> after) {
    Objects.requireNonNull(after);

    return (t1, t2, t3) -> {
      accept(t1, t2, t3);
      after.accept(t1, t2, t3);
    };
  }
}
