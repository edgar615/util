package com.edgar.util.cache;

import org.assertj.core.api.Assertions;

import java.util.concurrent.TimeUnit;

/**
 * Created by edgar on 16-4-1.
 */
public class ExpiringCacheTest {

  public static void main(String[] args) {
    ExpiringCache<String, String> cache = new ExpiringCache<>(LRUCache.<String, String>builder().setMaxSize(2).build());

    cache.put("a", "a", 1000);
    cache.put("b", "b", 2000);
    Assertions.assertThat(cache.get("a")).isEqualTo("a");
    Assertions.assertThat(cache.get("b")).isEqualTo("b");
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Assertions.assertThat(cache.get("a")).isNull();
    Assertions.assertThat(cache.get("b")).isEqualTo("b");
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    Assertions.assertThat(cache.get("b")).isNull();
  }

}
