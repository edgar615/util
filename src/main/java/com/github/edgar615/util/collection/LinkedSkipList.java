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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 跳表的链表实现
 */
public class LinkedSkipList<K extends Comparable<K>, V> implements SkipList<K, V> {

  /**
   * 默认层级
   */
  private static final int MAX_LEVEL = 16;

  /**
   * 跳表的最大层级
   */
  private final int maxLevel;

  /**
   * head节点不存储实际数据，表示层级
   */
  private Node head;

  /**
   * 节点数量
   */
  private long size;

  /**
   * 记录层数
   */
  private int levelCount;

  /**
   * 随机算法
   */
  private Random random = new Random();

  public LinkedSkipList() {
    this(MAX_LEVEL);
  }

  public LinkedSkipList(int maxLevel) {
    this.maxLevel = maxLevel;
    this.head = new Node(null, null, 0, null, null, null, null);
  }

  @Override
  public V add(K key, V value) {
    // 计算层级，最多值加一层，不会出现跳跃的情况
    long level = randomLevel();
    if (level > levelCount) {
      level = ++levelCount;
    }
    // 如果新构建的level大于原head的层级，需要使用新的层级作为head，原head作为新head的down节点
    if (level > head.level) {
      head = new Node(null, null, level, null, null, null, head);
    }

    // 从第一个节点开始
    Node current = head;
    // 记录上一层找到的最后一个节点
    Node last = null;
    while (current != null) {
      // 判断下一个节点的key是否大于当前要插入的key
      if (current.next == null || current.next.key.compareTo(key) > 0) {
        // 如果新层级是否大于等于当前层级，在这个层级插入该节点（跳表的特性）
        if (level >= current.level) {
          // 为节省空间，只在最后一个层级的节点插入value
          // 好像想多了，这样应该并没有节省多少空间，暂时没有验证，等后面验证了再调整
          V _value = null;
          if (0 == current.level) {
            _value = value;
          }
          Node newNode = new Node(key, _value, current.level, current, current.next, last, null);
          // 追加当前节点
          Node next = current.next;
          current.next = newNode;
          if (last != null) {
            last.down = newNode;
          }
          if (next != null) {
            next.prev = newNode;
          }
          // 记录新插入的节点，它的down应该指向下层新插入的节点
          last = newNode;
        }
        // 下降一级更新
        current = current.down;
        continue;
        // 进入了同一层级，如果找到相同的key，直接更新
      } else if (current.next.key.equals(key) && current.level > 0) {
        // 记录层级关系，这句写漏了，调了两天才找到，泪奔
        if (last != null) {
          last.down = current.next;
          current.next.up = last;
        }
        current = current.down;
        continue;
      } else if (current.next.key.equals(key) && current.level == 0) {
        V oldValue =  current.next.value;
        current.next.value = value;
        return oldValue;
      }
      // 同一层级，到这一步，说明新插入的值大于当前节点，那就继续向后遍历查询
      current = current.next;
    }
    size++;
    return value;
  }

  @Override
  public V get(K key) {
    Node node = search(key);
    return node == null ? null : node.value;
  }

  @Override
  public V remove(K key) {
    Node node = search(key);
    if (node == null) {
      return null;
    }
    // 找到的node永远是最后一级
    Node next = node.next;
    if (next != null) {
      next.prev = node.prev;
    }
    node.prev.next = next;

    Node current = node;
    // 依次删除层级
    while (current.up != null) {
      current = current.up;
      next = current.next;
      current.prev.next = next;
      if (next != null) {
        next.prev = current.prev;
      }
      current.down = null;
    }

    size--;
    return node.value;
  }


  @Override
  public long size() {
    return size;
  }

  @Override
  public List<V> findRange(K start, K end) {
    List<V> list = new ArrayList<>();
    Node floor = searchFloor(start);
    Node current;
    if (floor != null && floor.key != null && floor.key.compareTo(start) >= 0) {
      current = floor;
    } else {
      current = floor.next;
    }
    list.add(current.value);
    while (current != null) {
      if (current.next != null && current.next.key.compareTo(end) <= 0) {
        list.add(current.next.value);
        current = current.next;
      } else {
        break;
      }
    }
    return list;
  }

  private Node searchFloor(K key) {
    Node current = head;
    while (current != null) {
      // 数据永远存在最后一级
      if (current.level == 0 && key.equals(current.key)) {
        return current;
        // 如果最后一级的key已经大于当前要查询的值，说明没有找到数据，停止查找
      } else if (current.level == 0 && (current.key != null && current.key.compareTo(key) > 0)) {
        return current.prev;
        // 如果next的值大于当前要查询的值，说明当前的值在左边，然后就下降，继续查找
      } else if ((current.next == null || current.next.key.compareTo(key) > 0) && current.level > 0) {
        current = current.down;
        continue;
        // 如果找到相同的key，但是不是最后一级，继续下降直接返回（数据永远存在最后一级）
      } else if (current.next.key.equals(key) && current.level > 0) {
        current = current.next.down;
        continue;
      }
      // 继续向后查找
      current = current.next;
    }
    // 到这一步说明没找到
    return current;
  }

  private Node search(K key) {
    Node current = searchFloor(key);
    if (current != null && current.key != null && current.key.equals(key)) {
      return current;
    }
    // 到这一步说明没找到
    return null;
  }

  /**
   * 通过随机函数，决定新节点的层级，随机 level 次，如果是奇数层数 +1，防止伪随机
   */
  private int randomLevel() {
    int level = 1;
    for (int i = 1; i < maxLevel; ++i) {
      if (random.nextInt() % 2 == 1) {
        level++;
      }
    }
    return level;
  }

  /**
   * 为了使删除更方便，我们使用双向列表
   */
  private class Node {

    /**
     * 数据的key，通常情况下需要可比较排序
     */
    public K key;

    /**
     * 数据的value
     */
    public V value;

    /**
     * 记录当前节点的层级
     */
    public long level;

    /**
     * 水平方向上的上一个节点的指针
     */
    public Node prev;

    /**
     * 水平方向上的下一个节点的指针
     */
    public Node next;

    /**
     * 垂直方向上的下一个节点的指针
     */
    public Node up;

    /**
     * 垂直方向上的下一个节点的指针
     */
    public Node down;

    public Node(K key, V value, long level, Node prev, Node next, Node up, Node down) {
      this.key = key;
      this.value = value;
      this.level = level;
      this.prev = prev;
      this.next = next;
      this.up = up;
      this.down = down;
    }
  }

}
