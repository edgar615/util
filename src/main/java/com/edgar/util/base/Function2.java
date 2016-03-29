package com.edgar.util.base;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface Function2<T1, T2, R> {

    R apply(T1 t1, T2 t2);

    default <V> Function2<T1, T2, V> andThen(Function<? super R, ? extends V> f) {
        Objects.requireNonNull(f);
        return (T1 t1, T2 t2) ->
          f.apply(apply(t1, t2));
    }

}