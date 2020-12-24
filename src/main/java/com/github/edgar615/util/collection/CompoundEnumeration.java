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

import java.util.Enumeration;
import java.util.NoSuchElementException;

/**
 * 将Enumerration数组组合在一起.
 * <p>
 * 借鉴自linkedin的代码
 *
 * @author ypujante@linkedin.com, Edgar
 */
public class CompoundEnumeration<E> implements Enumeration<E> {

  private Enumeration<E>[] enums;

  private int index = 0;

  public CompoundEnumeration(Enumeration<E>[] enums) {
    this.enums = enums;
  }

  @Override
  public boolean hasMoreElements() {
    return next();
  }

  @Override
  public E nextElement() {
    if (next()) {
      return enums[index].nextElement();
    }
    throw new NoSuchElementException();
  }

  private boolean next() {
    while (index < enums.length) {
      if (enums[index] != null && enums[index].hasMoreElements()) {
        return true;
      }
      index++;
    }
    return false;
  }
}
