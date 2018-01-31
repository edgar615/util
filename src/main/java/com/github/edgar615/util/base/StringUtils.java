package com.github.edgar615.util.base;

import com.google.common.base.Strings;

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

  /**
   * <p>Checks if the String contains only Unicode letters.</p>
   *
   * <p>{@code null} will return {@code false}.
   * An empty String (length()=0) will return {@code false}.</p>
   *
   * <pre>
   * StringUtils.isAlpha(null)   = false
   * StringUtils.isAlpha("")     = false
   * StringUtils.isAlpha("  ")   = false
   * StringUtils.isAlpha("abc")  = true
   * StringUtils.isAlpha("ab2c") = false
   * StringUtils.isAlpha("ab-c") = false
   * </pre>
   *从commons-lang中复制
   * @param cs  the String to check, may be null
   * @return {@code true} if only contains letters, and is non-null
   * @since 3.0 Changed signature from isAlpha(String) to isAlpha(String)
   * @since 3.0 Changed "" to return false and not true
   */
  public static boolean isAlpha(final String cs) {
    if (Strings.isNullOrEmpty(cs)) {
      return false;
    }
    final int sz = cs.length();
    for (int i = 0; i < sz; i++) {
      if (!Character.isLetter(cs.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  /**
   * <p>Checks if the String contains only Unicode digits.
   * A decimal point is not a Unicode digit and returns false.</p>
   * <p>
   * <p>{@code null} will return {@code false}.
   * An empty String (length()=0) will return {@code false}.</p>
   * <p>
   * <p>Note that the method does not allow for a leading sign, either positive or negative.
   * Also, if a String passes the numeric test, it may still generate a NumberFormatException
   * when parsed by Integer.parseInt or Long.parseLong, e.g. if the value is outside the range
   * for int or long respectively.</p>
   * <p>
   * <pre>
   * StringUtils.isNumeric(null)   = false
   * StringUtils.isNumeric("")     = false
   * StringUtils.isNumeric("  ")   = false
   * StringUtils.isNumeric("123")  = true
   * StringUtils.isNumeric("\u0967\u0968\u0969")  = true
   * StringUtils.isNumeric("12 3") = false
   * StringUtils.isNumeric("ab2c") = false
   * StringUtils.isNumeric("12-3") = false
   * StringUtils.isNumeric("12.3") = false
   * StringUtils.isNumeric("-123") = false
   * StringUtils.isNumeric("+123") = false
   * </pre>
   *从commons-lang中复制
   * @param cs the String to check, may be null
   * @return {@code true} if only contains digits, and is non-null
   * @since 3.0 Changed "" to return false and not true
   */
  public static boolean isNumeric(final String cs) {
    if (Strings.isNullOrEmpty(cs)) {
      return false;
    }
    final int sz = cs.length();
    for (int i = 0; i < sz; i++) {
      if (!Character.isDigit(cs.charAt(i))) {
        return false;
      }
    }
    return true;
  }
}
