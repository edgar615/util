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