package com.edgar.util.collection;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by edgar on 16-6-28.
 */
public class PairTest {

    @Test
    public void testEquals() {
        Pair pair1 = Pair.create("A", "B");
        Pair pair2 = Pair.create("A", "B");
        Assertions.assertThat(pair1).isEqualTo(pair2);
        Assertions.assertThat(pair1.hashCode()).isEqualTo(pair2.hashCode());
        Map<Pair, Integer> map = new HashMap<>();
        map.put(pair1, 1);
        map.put(pair2, 1);
        Assertions.assertThat(map.size()).isEqualTo(1);

    }
}
