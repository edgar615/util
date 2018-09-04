package com.github.edgar615.util.collection;

import java.util.Enumeration;
import java.util.NoSuchElementException;

/**
 * 将Enumerration数组组合在一起.
 * <p>
 * 借鉴自linkedin的代码
 *
 * @author ypujante@linkedin.com, Edgar
 */
public class CompoundEnumeration<E> implements Enumeration<E> {

    private Enumeration<E>[] enums;

    private int index = 0;

    public CompoundEnumeration(Enumeration<E>[] enums) {
        this.enums = enums;
    }

    @Override
    public boolean hasMoreElements() {
        return next();
    }

    @Override
    public E nextElement() {
        if (next()) {
            return enums[index].nextElement();
        }
        throw new NoSuchElementException();
    }

    private boolean next() {
        while (index < enums.length) {
            if (enums[index] != null && enums[index].hasMoreElements()) {
                return true;
            }
            index++;
        }
        return false;
    }
}