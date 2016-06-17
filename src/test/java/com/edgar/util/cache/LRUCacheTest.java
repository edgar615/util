package com.edgar.util.cache;

import org.assertj.core.api.Assertions;
import org.junit.Assert;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by edgar on 16-4-1.
 */
public class LRUCacheTest {

  public static void main(String[] args) {
    LRUCache<String, String> cache = LRUCache.<String, String>builder().setMaxSize(2).build();
    cache.put("a", "a");
    cache.put("b", "b");
    Assertions.assertThat(cache.get("a")).isEqualTo("a");
    Assertions.assertThat(cache.get("b")).isEqualTo("b");
    cache.get("a");
    cache.get("a");
    cache.get("a");
    cache.get("a");
    cache.put("c", "c");
    Assertions.assertThat(cache.get("a")).isEqualTo("a");
    Assertions.assertThat(cache.get("b")).isNull();
    Assertions.assertThat(cache.get("c")).isEqualTo("c");

    System.out.println(cache);

    System.out.println(cache.getHitRate());

    cache.delete("c");
    Assertions.assertThat(cache.get("c")).isNull();

    System.out.println(cache);
    System.out.println(cache.getHitRate());

    cache.addEvictionListener(new EvictionListener<String, String>() {
      @Override
      public void evicted(String key, String value) {
        System.out.println(key + ":" + value);
      }
    });
    cache.delete("c");
    cache.put("c", "c");
    cache.delete("c");
    Assertions.assertThat(cache.get("c")).isNull();
    System.out.println(cache);
    System.out.println(cache.getHitRate());
  }

}
