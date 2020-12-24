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

import java.lang.reflect.Constructor;

/**
 * 异常的工具类.
 * <p>
 * 该类的代码借鉴了Facebook.
 *
 * @author Edgar
 */
public class ExceptionUtils {

  private ExceptionUtils() {
    throw new AssertionError("Not instantiable: " + ExceptionUtils.class);
  }

  /**
   * 将异常封装为另一个异常
   *
   * @param e 需要封装的异常
   * @param clazz 希望被封装为哪种异常
   * @param <T> 封装后的异常，泛型，继承自Exception
   * @param <S> 被封装的异常，继承自Exception
   * @return 封装后的异常
   */
  public static <T extends Exception, S extends Exception> T wrap(S e, Class<T> clazz) {
    if (clazz.isAssignableFrom(e.getClass())) {
      return (T) e;
    }

    try {
      Constructor<T> constructor = clazz.getConstructor(Throwable.class);

      // get the exception constructor with one argument
      return constructor.newInstance(e);
    } catch (RuntimeException exception) {
      throw exception;
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    }
  }
}
