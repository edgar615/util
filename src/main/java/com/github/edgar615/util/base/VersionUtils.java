package com.github.edgar615.util.base;

import com.google.common.base.Preconditions;

/**
 * Created by Edgar on 2016/12/5.
 *
 * @author Edgar  Date 2016/12/5
 */
public class VersionUtils {

  public static int compareVersion(String version1, String version2) {
    Preconditions.checkNotNull(version1);
    Preconditions.checkNotNull(version2);
    if (version1.equals(version2)) {
      return 0;
    }
    if (version1.startsWith("v") || version1.startsWith("V")) {
      version1 = version1.substring(1);
    }
    if (version2.startsWith("v") || version2.startsWith("V")) {
      version2 = version2.substring(1);
    }

    String[] version1Array = version1.split("\\.");
    String[] version2Array = version2.split("\\.");

    int index = 0;
    int minLen = Math.min(version1Array.length, version2Array.length);
    int diff = 0;

    while (index < minLen &&
           (diff = Integer.parseInt(version1Array[index]) - Integer.parseInt(version2Array[index]))
           == 0) {
      index++;
    }

    if (diff == 0) {
      for (int i = index; i < version1Array.length; i++) {
        if (Integer.parseInt(version1Array[i]) > 0) {
          return 1;
        }
      }

      for (int i = index; i < version2Array.length; i++) {
        if (Integer.parseInt(version2Array[i]) > 0) {
          return -1;
        }
      }

      return 0;
    } else {
      return diff > 0 ? 1 : -1;
    }
  }
}
