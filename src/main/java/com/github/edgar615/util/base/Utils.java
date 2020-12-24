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

package com.github.edgar615.util.base;

public class Utils {

  private static final boolean IS_WINDOWS;

  public static String LINE_SEPARATOR = System.getProperty("line.separator");

  static {
    String os = System.getProperty("os.name").toLowerCase();
    IS_WINDOWS = os.contains("win");
  }

  private Utils() {
    throw new AssertionError("Not instantiable: " + Utils.class);
  }

  /**
   * @return true, if running on Windows
   */
  public static boolean isWindows() {
    return IS_WINDOWS;
  }

}
