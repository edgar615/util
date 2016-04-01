package com.edgar.util.serialization;

/**
 * marker interface
 *
 * @param <T>
 */
public interface Codec<T> extends Serializer<T>, Deserializer<T> {
}