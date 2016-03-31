package com.edgar.util.base;

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

  /**
   * 生成一个随机字符串.
   * 该方法会随机选择<code>base</code>里的字符来填充字符串。
   *
   * @param len  需要生成的字符串长度
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
}