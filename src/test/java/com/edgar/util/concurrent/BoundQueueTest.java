package com.edgar.util.concurrent;

import com.edgar.util.collection.BoundedQueue;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by edgar on 16-3-31.
 */
public class BoundQueueTest {

  @Test
  public void testBoundQueue() {
    BoundedQueue<Integer> queue = BoundedQueue.create(3);
    for (int i = 0; i < 10; i ++) {
      queue.add(i);
    }
    Assertions.assertThat(queue.size()).isEqualTo(3);
  }
}
