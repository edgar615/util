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

package com.github.edgar615.util.reflect;

import com.github.edgar615.util.collection.CompoundEnumeration;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Chains multiple class loaders.
 *
 * @author ypujante@linkedin.com
 */
public class ClassLoaderChain extends ClassLoader {

  public static final Logger log = LoggerFactory.getLogger(ClassLoaderChain.class.getName());

  private final Collection<ClassLoader> classLoaders;

  private ClassLoaderChain(Collection<ClassLoader> classLoaders) {
    this.classLoaders = classLoaders;
  }

  /**
   * Creates a chain of class loaders. Handles <code>null</code> properly. This will create a new
   * chain, even if there is only one classloader in the chain. The usage for this is that
   * ResourceBundle caches resource per classloader.
   *
   * @return a class loader (never <code>null</code>)
   */
  public static ClassLoader createNewChain(ClassLoader... classLoaders) {
    return createChain(Arrays.asList(classLoaders), true);
  }

  /**
   * Creates a chain of class loaders. Handles <code>null</code> properly.
   *
   * @return a class loader (never <code>null</code>)
   */
  public static ClassLoader createChain(ClassLoader... classLoaders) {
    return createChain(Arrays.asList(classLoaders), false);
  }

  /**
   * Creates a chain of class loaders. Handles <code>null</code> properly.
   *
   * @return a class loader (never <code>null</code>)
   */
  public static ClassLoader createChain(List<ClassLoader> classLoaders) {
    return createChain(classLoaders, false);
  }

  /**
   * Creates a chain of class loaders. Handles <code>null</code> properly.
   *
   * @param createNew whether to create new instance of classloaderchain even if there is only one
   * loader
   * @return a class loader (never <code>null</code>)
   */
  public static ClassLoader createChain(List<ClassLoader> classLoaders, Boolean createNew) {
    List<ClassLoader> list = new ArrayList<ClassLoader>(classLoaders.size());
    for (ClassLoader classLoader : classLoaders) {
// we skip null class loaders
      if (classLoader == null) {
        continue;
      }
// we skip null class loaders
      if (classLoader instanceof NullClassLoader) {
        continue;
      }
// we 'flatten' the chain... no need to have a chain of a chain...
      if (classLoader instanceof ClassLoaderChain) {
        ClassLoaderChain classLoaderChain = (ClassLoaderChain) classLoader;
        list.addAll(classLoaderChain.classLoaders);
      } else {
        list.add(classLoader);
      }
    }
    if (list.size() == 0) {
      return NullClassLoader.instance();
    }
// remove duplicates
    ClassLoader previous = null;
    Iterator<ClassLoader> iter = list.iterator();
    while (iter.hasNext()) {
      ClassLoader next = iter.next();
      if (next.equals(previous)) {
        iter.remove();
      }
      previous = next;
    }
//TODO: create a new entry point, and keeping old behavior unchanged.
    if (list.size() == 1 && !createNew) {
      return list.get(0);
    }
    return new ClassLoaderChain(list);
  }

  @Override
  public Class loadClass(String name) throws ClassNotFoundException {
    Class clazz = null;
    for (ClassLoader classLoader : classLoaders) {
      try {
        clazz = classLoader.loadClass(name);
        return clazz;
      } catch (ClassNotFoundException e) {
        log.debug("class not found in " + classLoader + "... moving to next one");
      }
    }
    throw new ClassNotFoundException(name);
  }

  @Override
  public URL getResource(String name) {
    URL url = null;
    for (ClassLoader classLoader : classLoaders) {
      url = classLoader.getResource(name);
      if (url != null) {
        return url;
      }
    }
    return url;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Enumeration<URL> getResources(String name) throws IOException {
    Enumeration<URL>[] enums = new Enumeration[classLoaders.size()];
    int i = 0;
    for (ClassLoader classLoader : classLoaders) {
      enums[i++] = classLoader.getResources(name);
    }
    return new CompoundEnumeration<URL>(enums);
  }
}
