package com.edgar.util.reflect;

import java.net.URL;

/**
 * Created by Edgar on 2016/2/19.
 *
 * @author Edgar  Date 2016/2/19
 */
public class MyClassloader extends ClassLoader {

  @Override
  public Class<?> loadClass(String name) throws ClassNotFoundException {
    throw new ClassNotFoundException(name);
  }

  @Override
  public URL getResource(String name) {
    return null;
  }
}
