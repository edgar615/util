package com.edgar.util.base;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by Edgar on 2016/3/31.
 *
 * @author Edgar  Date 2016/3/31
 */
public class RandomsTest {

  @Test
  public void generateRandomString() {
    String s1 = Randoms.randomString(10, "abc");
    Assertions.assertThat(s1).hasSize(10).doesNotContain("d").doesNotContain("e");

  }
}
