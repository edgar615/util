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

import java.util.Iterator;
import java.util.Spliterator;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Consumer;

/**
 * 弹性队列. 队列的内部使用BlockingDeque实现，当队列的容量满了之后，再往队列中加入元素会将队列头部的元素删除。
 * <p>
 * 借鉴twitter的实现.
 *
 * @author Edgar
 */
public class BoundedQueue<T> implements Iterable<T> {

  /**
   * 委托的队列.
   */
  private BlockingDeque<T> queue;

  /**
   * 构造方法.
   *
   * @param limit 队列的容量.
   */
  private BoundedQueue(int limit) {
    this.queue = new LinkedBlockingDeque<>(limit);
  }

  /**
   * 向队列中添加一个元素. 如果队列已经满了，会将第一个元素删除之后再添加。
   *
   * @param item 待添加的元素
   */
  public synchronized T add(T item) {
    T eldItem = null;
    if (queue.remainingCapacity() == 0) {
      eldItem = queue.removeFirst();
    }
    queue.add(item);
    return eldItem;
  }


  /**
   * 清空队列.
   */
  public synchronized void clear() {
    queue.clear();
  }

  /**
   * 队列中元素的数量.
   *
   * @return 元素的数量.
   */
  public synchronized int size() {
    return queue.size();
  }

  @Override
  public synchronized Iterator<T> iterator() {
    return queue.iterator();
  }

  @Override
  public synchronized void forEach(Consumer<? super T> action) {
    queue.forEach(action);
  }

  @Override
  public synchronized Spliterator<T> spliterator() {
    return queue.spliterator();
  }

  /**
   * 创建一个弹性队列
   *
   * @param limit 队列大小
   * @param <T> 队列类型
   * @return 队列
   */
  public static <T> BoundedQueue<T> create(int limit) {
    return new BoundedQueue<>(limit);
  }
}
