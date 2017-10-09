package com.github.edgar615.util.base;

public class Utils {

  private static final boolean isWindows;

  public static String LINE_SEPARATOR = System.getProperty("line.separator");

  private Utils() {
    throw new AssertionError("Not instantiable: " + Utils.class);
  }

  /**
   * @return true, if running on Windows
   */
  public static boolean isWindows() {
    return isWindows;
  }

  static {
    String os = System.getProperty("os.name").toLowerCase();
    isWindows = os.contains("win");
  }

}