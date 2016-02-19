package com.edgar.util.collection;

import java.util.Enumeration;
import java.util.NoSuchElementException;

/**
 * @author ypujante@linkedin.com
 */ /*
* A useful utility class that will enumerate over an array of
* enumerations.
*/
public class CompoundEnumeration<E> implements Enumeration<E> {
  private final Enumeration<E>[] _enums;

  private int index = 0;

  public CompoundEnumeration(Enumeration<E>[] enums) {
    _enums = enums;
  }

  @Override
  public boolean hasMoreElements() {
    return next();
  }

  @Override
  public E nextElement() {
    if (!next()) {
      throw new NoSuchElementException();
    }
    return _enums[index].nextElement();
  }

  private boolean next() {
    while (index < _enums.length) {
      if (_enums[index] != null && _enums[index].hasMoreElements()) {
        return true;
      }
      index++;
    }
    return false;
  }
}