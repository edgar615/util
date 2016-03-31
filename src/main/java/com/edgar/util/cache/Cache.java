package com.edgar.util.cache;

/**
 * 缓存的接口.
 *
 * @author Edgar
 */
public interface Cache<K, V> {

  /**
   * 从缓存中获取值.
   *
   * @param key 缓存的键值，不能为 {@code null}.
   * @return 缓存值，如果缓存不存在，返回{@code null}.
   */
  public V get(K key);

  /**
   * 将键值对放入缓存.
   *
   * @param key 缓存的键值，不能为 {@code null}.
   * @param value 缓存值，如果缓存不存在，返回{@code null}.
   */
  public void put(K key, V value);

  /**
   * 根据键删除缓存值.
   *
   * @param key 缓存的键值，不能为 {@code null}.
   */
  public void delete(K key);
}