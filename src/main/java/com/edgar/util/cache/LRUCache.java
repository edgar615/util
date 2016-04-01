package com.edgar.util.cache;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU缓存.
 * 键最近最少使用的数据删除.
 *
 * @author Edgar
 */
public class LRUCache<K, V> implements Cache<K, V> {

  private final String name;

  private final Map<K, V> map;

  public LRUCache(String name, int maxSize, float loadFactor, boolean makeSynchronized) {
    this.name = name;
    //避免扩容
    int capacity = (int) Math.ceil(maxSize / loadFactor) + 1;
    Map<K, V> _map = new LinkedHashMap<K, V>(capacity, loadFactor, true) {
      @Override
      protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > maxSize;
      }
    };
    if (makeSynchronized) {
      map = Collections.synchronizedMap(_map);
    } else {
      map = _map;
    }
  }

  public static <K, V> Builder<K, V> builder() {
    return new Builder<>();
  }

  @Override
  public V get(K key) {
    return map.get(key);
  }

  @Override
  public void put(K key, V value) {
    map.put(key, value);
  }

  @Override
  public void delete(K key) {
    map.remove(key);
  }

  public static class Builder<K, V> {

    private String name = null;

    private int maxSize = 1000;

    private float loadFactor = 0.75f;

    private boolean makeSynchronized = true;

    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    public Builder setMaxSize(int maxSize) {
      this.maxSize = maxSize;
      return this;
    }

    public Builder setLoadFactor(float loadFactor) {
      this.loadFactor = loadFactor;
      return this;
    }

    public Builder setMakeSynchronized(boolean makeSynchronized) {
      this.makeSynchronized = makeSynchronized;
      return this;
    }

    public LRUCache<K, V> build() {
      return new LRUCache<>(name, maxSize, loadFactor, makeSynchronized);
    }
  }
}
