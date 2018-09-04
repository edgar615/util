package com.github.edgar615.util.cache;

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
  V get(K key);

  /**
   * 将键值对放入缓存.
   *
   * @param key 缓存的键值，不能为 {@code null}.
   * @param value 缓存的键值，不能为 {@code null}.
   */
  void put(K key, V value);

  /**
   * 将键值对放入缓存.
   *
   * @param key 缓存的键值，不能为 {@code null}.
   * @param value 缓存的键值，不能为 {@code null}.
   * @param expires 缓存的失效
   */
  void put(K key, V value, long expires);

  /**
   * 根据键删除缓存值.
   *
   * @param key 缓存的键值，不能为 {@code null}.
   */
  void delete(K key);

  /**
   * 增加监听器，当从缓存中删除值的时候触发.
   *
   * @param listener 监听器
   */
  void addEvictionListener(EvictionListener<K, V> listener);

}