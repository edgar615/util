package com.github.edgar615.util.base;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.UUID;

/**
 * Created by Edgar on 2016/3/31.
 *
 * @author Edgar  Date 2016/3/31
 */
public class RandomsTest {

    @Test
    public void generateRandomString() {
        System.out.println(UUID.randomUUID().toString().replace("-", ""));
        System.out.println(UUID.randomUUID().toString().replace("-", ""));
        String s1 = Randoms.randomString(10, "abc");
        Assertions.assertThat(s1).hasSize(10).doesNotContain("d").doesNotContain("e");

    }
}
