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
 * 跳表的数组实现
 */
public class ArraySkipList<K extends Comparable<K>, V> implements SkipList<K, V> {

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

  public ArraySkipList() {
    this(MAX_LEVEL);
  }

  public ArraySkipList(int maxLevel) {
    this.maxLevel = maxLevel;
    this.head = new Node(null, null, maxLevel);
  }

  private class Node<K extends Comparable<K>, V> {

    private K key;
    private V value;
    // 每个节点都包含了一个节点数组用来表示当前节点的下一个节点，同时这个数组还维持了多个层级的数据，而完成数据的查找，比较难理解，debug了好几遍才弄明白
    // next[1]表示第一层,next[2]表示第二层
    private Node<K, V> next[];

    //最高的层
    private int level;

    public Node(K key, V value, int level) {
      this.key = key;
      this.value = value;
      this.level = level;
      this.next = new Node[level];
    }
  }

//  @Override
//  public V add(K key, V value) {
//    int level = randomLevel();
//    // 每次只增加一层，如果条件满足
//    if (level > levelCount) {
//      level = ++levelCount;
//    }
//    Node<K, V> newNode = new Node(key, value, level);
//    // 辅助数组，存放所有要调整的节点
//    Node<K, V> update[] = new Node[level];
//    for (int i = 0; i < level; ++i) {
//      update[i] = head;
//    }
//
//    Node<K, V> p = head;
//    // 在每层里找到最接近的节点
//    for (int i = level - 1; i >= 0; --i) {
//      while (p.next[i] != null && p.next[i].key.compareTo(key) < 0) {
//        p = p.next[i];
//      }
//      // 将要调整的节点存入辅助数组
//      update[i] = p;
//    }
//
//    boolean existed = false;
//    V oldValue = null;
//    if (update[0].next[0] != null && update[0].next[0].key.equals(key)) {
//      existed = true;
//      oldValue = update[0].next[0].value;
//    }
//    // 调整
//    for (int i = 0; i < level; ++i) {
//      newNode.next[i] = update[i].next[i];
//      update[i].next[i] = newNode;
//    }
//    return oldValue;
//  }

  @Override
  public V add(K key, V value) {
    int level = head.next[0] == null ? 1 : randomLevel();
    // 每次只增加一层，如果条件满足
    if (level > levelCount) {
      level = ++levelCount;
    }
    Node<K, V> newNode = new Node(key, value, level);
    // 辅助数组，存放所有要调整的节点
    Node<K, V> update[] = new Node[level];
    for (int i = 0; i < level; ++i) {
      update[i] = head;
    }

    Node<K, V> p = head;
    // 在每层里找到最接近的节点，放入辅助数组
    for (int i = level - 1; i >= 0; --i) {
      while (p.next[i] != null && p.next[i].key.compareTo(key) < 0) {
        p = p.next[i];
      }
      update[i] = p;
    }

    boolean existed = false;
    V oldValue = null;
    // 调整
    for (int i = 0; i < level; ++i) {
      newNode.next[i] = update[i].next[i];
      if (update[i].next[i] != null && update[i].next[i].key.equals(key)) {
        oldValue = update[i].next[i].value;
        existed = true;
        update[i].next[i].value = value;
      } else {
        update[i].next[i] = newNode;
      }
    }
    if (!existed) {
      size ++;
    }
    return oldValue;
  }

  public void printAll() {
    Node p = head;
    Node[] c = p.next;
    Node[] d = c;
    int maxLevel = c.length;
    for (int i = maxLevel - 1; i >= 0; i--) {
      System.out.print(i + ":");
      do {
        System.out.print((d[i] != null ? d[i].key : null) + "---");
      } while (d[i] != null && (d = d[i].next)[i] != null);
      System.out.println();
      d = c;
    }
  }

  @Override
  public V get(K key) {

    Node<K, V> p = head;
    for (int i = levelCount - 1; i >= 0; --i) {
      while (p.next[i] != null && p.next[i].key.compareTo(key) < 0) {
        p = p.next[i];
      }
    }

    if (p.next[0] != null && p.next[0].key.compareTo(key) == 0) {
      return p.next[0].value;
    } else {
      return null;
    }
  }

  @Override
  public V remove(K key) {
    Node<K, V>[] update = new Node[levelCount];
    Node<K, V> p = head;
    for (int i = levelCount - 1; i >= 0; --i) {
      while (p.next[i] != null && p.next[i].key.compareTo(key) < 0) {
        p = p.next[i];
      }
      update[i] = p;
    }

    V oldValue = null;
    if (p.next[0] != null && p.next[0].key.equals(key)) {
      for (int i = levelCount - 1; i >= 0; --i) {
        if (update[i].next[i] != null && update[i].next[i].key.equals(key)) {
          oldValue = update[i].next[i].value;
          update[i].next[i] = update[i].next[i].next[i];
        }
      }
    }

    while (levelCount>1&&head.next[levelCount]==null){
      levelCount--;
    }
    if (oldValue != null) {
      size --;
    }
    return oldValue;
  }

  @Override
  public long size() {
    return size;
  }

  @Override
  public List<V> findRange(K start, K end) {
    Node<K, V> p = head;
    for (int i = levelCount - 1; i >= 0; --i) {
      while (p.next[i] != null && p.next[i].key.compareTo(start) < 0) {
        p = p.next[i];
      }
    }
    List<V> list = new ArrayList<>();
    while (p.next[0] != null && p.next[0].key.compareTo(end) <= 0) {
      list.add(p.next[0].value);
      p = p.next[0];
    }

    return list;
  }

  private int randomLevel() {
    int level = 1;
    for (int i = 1; i < maxLevel; ++i) {
      if (random.nextInt() % 2 == 1) {
        level++;
      }
    }
    return level;
  }
}
