package com.edgar.util.base;

/**
 * Created by Edgar on 2017/5/18.
 *
 * @author Edgar  Date 2017/5/18
 */
public class StringUtils {
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
}
