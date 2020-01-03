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

  private class Node<K, V> {

    private K key;
    private V value;
    //指向下一个节点，并维持多个层级数据
    private Node next[];

    //跨越几层
    private int level;

    public Node(K key, V value, int level) {
      this.key = key;
      this.value = value;
      this.level = level;
      this.next = new Node[level];
    }
  }

  @Override
  public V add(K key, V value) {
    return null;
  }

  @Override
  public V get(K key) {
    return null;
  }

  @Override
  public V remove(K key) {
    return null;
  }

  @Override
  public long size() {
    return size;
  }

  @Override
  public List<V> findRange(K start, K end) {
    return null;
  }
}
