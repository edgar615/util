package com.github.edgar615.util.serialization;

/**
 * marker interface
 *
 * @param <T>
 */
public interface SerDe<T> extends Serializer<T>, Deserializer<T> {
}