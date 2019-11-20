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

import com.google.common.base.Preconditions;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机数工具类.
 *
 * @author Edgar
 * @version 1.0
 */
public class Randoms {

  private static final String NUM = "123456789";

  private static final String LOWER_ALPHABET = "abcdefghijklmnopqrstuvwxyz";

  private static final String UPPER_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

  private static final String ALPHABET = LOWER_ALPHABET + UPPER_ALPHABET;

  private static final String ALPHABET_AND_NUM = LOWER_ALPHABET + UPPER_ALPHABET + NUM;

  private Randoms() {
    throw new AssertionError("Not instantiable: " + Randoms.class);
  }

  /**
   * 生成一个随机字符串. 该方法会随机选择<code>base</code>里的字符来填充字符串。
   *
   * @param len 需要生成的字符串长度
   * @param base 基础字符串
   * @return 随机字符串
   */
  public static String randomString(int len, String base) {
    Preconditions.checkNotNull(base);
    StringBuilder sb = new StringBuilder(len);
    Random random = ThreadLocalRandom.current();
    int range = base.length();
    for (int i = 0; i < len; i++) {
      sb.append(base.charAt(random.nextInt(range)));
    }
    return sb.toString();
  }

  /**
   * 使用数字生成一个随机字符串.
   *
   * @param len 需要生成的字符串长度
   * @return 随机字符串
   */
  public static String randomNumber(int len) {
    return randomString(len, NUM);
  }

  /**
   * 使用小写字母生成一个随机字符串.
   *
   * @param len 需要生成的字符串长度
   * @return 随机字符串
   */
  public static String randomLowerAlphabet(int len) {
    return randomString(len, LOWER_ALPHABET);
  }

  /**
   * 使用大写字母生成一个随机字符串.
   *
   * @param len 需要生成的字符串长度
   * @return 随机字符串
   */
  public static String randomUpperAlphabet(int len) {
    return randomString(len, UPPER_ALPHABET);
  }

  /**
   * 使用字母生成一个随机字符串.
   *
   * @param len 需要生成的字符串长度
   * @return 随机字符串
   */
  public static String randomAlphabet(int len) {
    return randomString(len, ALPHABET);
  }

  /**
   * 使用字母和数字生成一个随机字符串.
   *
   * @param len 需要生成的字符串长度
   * @return 随机字符串
   */
  public static String randomAlphabetAndNum(int len) {
    return randomString(len, ALPHABET_AND_NUM);
  }
}
