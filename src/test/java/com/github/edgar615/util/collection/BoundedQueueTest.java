package com.github.edgar615.util.collection;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Edgar on 2016/6/27.
 *
 * @author Edgar  Date 2016/6/27
 */
public class BoundedQueueTest {
    @Test
    public void testQueue() {
        BoundedQueue<Integer> queue = BoundedQueue.create(3);
        Assert.assertNull(queue.add(1));
        Assert.assertNull(queue.add(2));
        Assert.assertNull(queue.add(3));
        Assert.assertEquals(1, queue.add(4), 0);
        Assert.assertEquals(2, queue.add(5), 0);

        Assertions.assertThat(queue.size()).isEqualTo(3);
    }
}
