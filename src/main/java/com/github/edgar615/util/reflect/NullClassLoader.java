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

package com.github.edgar615.util.reflect;

import java.net.URL;

/**
 * Null pattern for class loader...
 *
 * @author ypujante@linkedin.com
 */
public class NullClassLoader extends ClassLoader {

  private static final NullClassLoader INSTANCE = new NullClassLoader();

  /**
   * Constructor
   */
  private NullClassLoader() {
  }

  public static NullClassLoader instance() {
    return INSTANCE;
  }

  @Override
  public Class<?> loadClass(String name) throws ClassNotFoundException {
    throw new ClassNotFoundException(name);
  }

  @Override
  public URL getResource(String name) {
    return null;
  }
}
