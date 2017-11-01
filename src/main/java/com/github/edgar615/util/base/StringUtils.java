package com.github.edgar615.util.base;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Edgar on 2017/5/18.
 *
 * @author Edgar  Date 2017/5/18
 */
public class StringUtils {
  private static final String WITH_DELIMITER = "((?<=%1$s)|(?=%1$s))";

  private StringUtils() {
    throw new AssertionError("Not instantiable: " + MorePreconditions.class);
  }

  public static String underscoreName(String name) {
    StringBuilder result = new StringBuilder();
    result.append(name.substring(0, 1).toLowerCase());
    for (int i = 1; i < name.length(); i++) {
      String s = name.substring(i, i + 1);
      String slc = s.toLowerCase();
      if (!s.equals(slc)) {
        result.append("_").append(slc);
      } else {
        result.append(s);
      }
    }
    return result.toString();
  }

  /**
   * https://stackoverflow.com/questions/2206378/how-to-split-a-string-but-also-keep-the-delimiters
   * <p>
   * System.out.println(Arrays.toString("a;b;c;d".split("(?<=;)")));
   * System.out.println(Arrays.toString("a;b;c;d".split("(?=;)")));
   * System.out.println(Arrays.toString("a;b;c;d".split("((?<=;)|(?=;))")));
   * <p>
   * And you will get:
   * <p>
   * [a;, b;, c;, d]
   * [a, ;b, ;c, ;d]
   * [a, ;, b, ;, c, ;, d]
   * <p>
   * The last one is what you want.
   * <p>
   * ((?<=;)|(?=;)) equals to select an empty character before ; or after ;.
   *
   * @param string
   * @param separator
   */
  public static List<String> splitRemainDelimiter(String string, String separator) {
    return Arrays.asList(string.split(String.format(WITH_DELIMITER, separator)));
  }
}
