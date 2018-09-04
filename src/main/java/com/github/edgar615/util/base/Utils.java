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