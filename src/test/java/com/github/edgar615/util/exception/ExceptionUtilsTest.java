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

package com.github.edgar615.util.exception;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by Edgar on 2016/3/31.
 *
 * @author Edgar  Date 2016/3/31
 */
public class ExceptionUtilsTest {

  @Test
  public void wrapNullToIllegal() {
    NullPointerException exception = new NullPointerException("foo");
    Assertions.assertThat(ExceptionUtils.wrap(exception, IllegalArgumentException.class))
        .isInstanceOf(IllegalArgumentException.class);
    System.out.println(ExceptionUtils.wrap(exception, IllegalArgumentException.class));
  }

  @Test
  public void wrapNullToNull() {
    NullPointerException exception = new NullPointerException();
    Assertions.assertThat(ExceptionUtils.wrap(exception, NullPointerException.class))
        .isInstanceOf(NullPointerException.class);
    System.out.println(ExceptionUtils.wrap(exception, NullPointerException.class));
  }
}
