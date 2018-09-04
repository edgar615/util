package com.github.edgar615.util.serialization;

import java.io.InputStream;

/**
 * 将流中的数据反序列化为对象.
 *
 * @param <T>
 * @author Edgar
 */
public interface Deserializer<T> {
    /**
     * 反序列化方法
     *
     * @param in 输入流
     * @return 反序列化的对象
     * @throws SerDeException
     */
    T deserialize(InputStream in) throws SerDeException;
}