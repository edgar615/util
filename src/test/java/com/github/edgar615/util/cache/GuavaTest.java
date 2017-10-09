package com.github.edgar615.util.cache;

import com.google.common.base.Ticker;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Created by Edgar on 2016/5/12.
 *
 * @author Edgar  Date 2016/5/12
 */
public class GuavaTest {
  public static void main(String[] args) {

    com.google.common.cache.Cache<String, String> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(5L, TimeUnit.MINUTES)
            .maximumSize(5000L)
            .ticker(Ticker.systemTicker())
            .build();

    System.out.println(cache.getIfPresent("foo"));
    cache.put("foo", "bar");
    System.out.println(cache.getIfPresent("foo"));
    try {
      TimeUnit.SECONDS.sleep(6);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(cache.getIfPresent("foo"));
  }
}
