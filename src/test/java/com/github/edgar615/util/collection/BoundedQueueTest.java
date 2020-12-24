/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
