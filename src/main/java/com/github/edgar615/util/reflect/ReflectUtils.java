package com.github.edgar615.util.reflect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 * 反射的工具类，大部分是网上的代码.
 */
public class ReflectUtils {

  public static final Logger log = LoggerFactory.getLogger(ReflectUtils.class.getName());

  /**
   * Constructor
   */
  private ReflectUtils() {
    throw new AssertionError("Not instantiable: " + ReflectUtils.class);
  }

  /**
   * Given a proxy returns the object proxied. If the object is not a proxy, then return the object
   * itself.
   *
   * @param proxy
   * @return the proxied object (or proxy if not an object proxy)
   */
  @SuppressWarnings("unchecked")
  public static Object getProxiedObject(Object proxy) {
    if (Proxy.isProxyClass(proxy.getClass())) {
      InvocationHandler invocationHandler = Proxy.getInvocationHandler(proxy);
      if (invocationHandler instanceof ObjectProxy) {
        ObjectProxy objectProxy = (ObjectProxy) invocationHandler;
// recursively fetch the proxy
        return getProxiedObject(objectProxy.getProxiedObject());
      } else { return proxy; }
    } else { return proxy; }
  }

  /**
   * Return a the default class loader (the one associated to the current thread or the one from
   * this class)
   *
   * @return the default ClassLoader (never <code>null</code>)
   * @see java.lang.Thread#getContextClassLoader()
   */
  public static ClassLoader getDefaultClassLoader() {
    ClassLoader cl = null;
    try {
      cl = Thread.currentThread().getContextClassLoader();
    } catch (Throwable ex) {
      log.debug("Cannot access thread context ClassLoader - falling back to system class loader",
                ex);
    }
    if (cl == null) {
// No thread context class loader -> use class loader of this class.
      cl = ReflectUtils.class.getClassLoader();
    }
    return cl;
  }

  /**
   * Wraps <code>Class.forName</code> to be context class loader aware.
   *
   * @param name the name of the Class
   * @return Class instance for the supplied name
   * @throws ClassNotFoundException if the class was not found
   * @throws LinkageError           if the class file could not be loaded
   * @see Class#forName(String, boolean, ClassLoader)
   * @see #getDefaultClassLoader()
   */
  public static Class forName(String name) throws ClassNotFoundException, LinkageError {
    return forName(name, getDefaultClassLoader());
  }

  /**
   * Wraps <code>Class.forName</code> to be context class loader aware.
   *
   * @param name        the name of the Class
   * @param classLoader the class loader to use (may be <code>null</code>, which indicates the
   *                    default class loader)
   * @return Class instance for the supplied name
   * @throws ClassNotFoundException if the class was not found
   * @throws LinkageError           if the class file could not be loaded
   * @see Class#forName(String, boolean, ClassLoader)
   */
  public static Class forName(String name, ClassLoader classLoader)
          throws ClassNotFoundException, LinkageError {
    if (classLoader == null) {
      classLoader = getDefaultClassLoader();
    }
    return Class.forName(name, true, classLoader);
  }

  /**
   * Convenient call to return a class in a different class loader... note that it will handle
   * correctly a class in the same class loader
   *
   * @param clazz       the clazz you want in a different class loader
   * @param classLoader the class loader to use (may be <code>null</code>, which indicates the
   *                    default class loader)
   * @return Class instance for the supplied name
   * @throws ClassNotFoundException if the class was not found
   * @throws LinkageError           if the class file could not be loaded
   * @see Class#forName(String, boolean, ClassLoader)
   */
  public static Class forName(Class clazz, ClassLoader classLoader)
          throws ClassNotFoundException, LinkageError {
    if (clazz == null) { return null; }
    if (classLoader == null) {
      classLoader = getDefaultClassLoader();
    }
    if (clazz.getClassLoader() == null || clazz.getClassLoader().equals(classLoader)) {
      return clazz;
    } else { return forName(clazz.getName(), classLoader); }
  }

  /**
   * Execute the call within the given class loader... handle setting / reverting to
   * previous class loader in a safe manner.
   *
   * @param classLoader the class loader to set for the duration of the call
   * @param callable    the callable to execute within the context of the provided class loader
   * @return the result of the call
   * @throws Exception whatever callable throws
   */
  public static <T> T executeWithClassLoader(ClassLoader classLoader,
                                             Callable<T> callable) throws Exception {
    ClassLoader previousClassLoader = Thread.currentThread().getContextClassLoader();
    try {
      Thread.currentThread().setContextClassLoader(classLoader);
      return callable.call();
    } finally {
      Thread.currentThread().setContextClassLoader(previousClassLoader);
    }
  }

  /**
   * Computes the signature of a method. Note that if 2 methods are declared
   * in different classes or interfaces then I1.m and I2.m are different but
   * they can still have the same signature...
   *
   * @param m method
   * @return the signature (unique per method definition)
   */
  @SuppressWarnings("unchecked")
  public static String computeSignature(Method m) {
    StringBuilder sb = new StringBuilder();
    sb.append(m.getName()).append('(');
    Class[] parameterTypes = m.getParameterTypes();
    for (int i = 0; i < parameterTypes.length; i++) {
      if (i > 0) { sb.append(','); }
      Class parameterType = parameterTypes[i];
      sb.append(parameterType.getName());
    }
    sb.append(')');
    return sb.toString();
  }

  /**
   * Utility which extract the interfaces implemented by o: it goes up the chain of inheritance
   * to find all the interfaces.
   *
   * @param o
   * @return all the interfaces implemented by o
   */
  public static Class[] extractAllInterfaces(Object o) {
    Set<Class> interfaces = new LinkedHashSet<Class>();
    Class c = o.getClass();
    do {
      interfaces.addAll(Arrays.asList(c.getInterfaces()));
      c = c.getSuperclass();
    }
    while (c != null);
    return interfaces.toArray(new Class[interfaces.size()]);
  }

  /**
   * The purpose of this method is somewhat to provide a better naming / documentation than the
   * javadoc
   * of <code>Class.isAssignableFrom</code> method. Every time I need to use this method I read
   * and reread the javadoc 25 times without being clear of what should be the order of each
   * parameter.
   * <p>
   * What you usually want to do is:
   * <pre>
   * if o1 is an object you write something like
   * if(o1 instanceof C)
   * But if you have only the class of o1 (say C1), then you write
   * if(isSubClassOrInterfaceOf(C1, C))
   *
   * in other words:
   * o1 instanceof C == isSubClassOrInterfaceOf(o1.getClass(), C)
   * </pre>
   *
   * @param subclass
   * @param superclass
   * @return <code>true</code> if subclass is a subclass or sub interface of superclass
   */
  public static boolean isSubClassOrInterfaceOf(Class subclass, Class superclass) {
    return superclass.isAssignableFrom(subclass);
  }
}