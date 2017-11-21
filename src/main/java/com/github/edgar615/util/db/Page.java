package com.github.edgar615.util.db;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * 简单的分页类，该类是一个不可变类.
 * 只会显示总数和列表，没有页码之类的条件
 *
 * @param <T> 实体类
 * @author Edgar Zhang
 * @version 1.0
 */
public final class Page<T> {

  private final List<T> records;

  private final int totalRecords;


  private Page(int totalRecords, List<T> records) {
    this.totalRecords = totalRecords;
    this.records = ImmutableList.copyOf(records);
  }

  /**
   * 创建一个Pagination类
   *
   * @param totalRecords 总记录数
   * @param records      当前页显示的集合
   * @param <T>          实体类
   * @return Page
   */
  public static <T> Page<T> newInstance(int totalRecords,
                                        List<T> records) {
    return new Page<T>(totalRecords, records);
  }

  public List<T> getRecords() {
    return records;
  }

  public int getTotalRecords() {
    return totalRecords;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("Pagination")
            .add("totalRecords", totalRecords)
            .add("records", records)
            .toString();
  }
}