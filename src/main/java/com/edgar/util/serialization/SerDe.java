package com.edgar.util.serialization;

/**
 * marker interface
 *
 * @param <T>
 */
public interface SerDe<T> extends Serializer<T>, Deserializer<T> {
}