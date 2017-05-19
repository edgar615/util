package com.edgar.util.search;

import com.google.common.base.Preconditions;

import com.edgar.util.base.MorePreconditions;
import com.edgar.util.db.Persistent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Edgar on 2017/5/19.
 *
 * @author Edgar  Date 2017/5/19
 */
public class Example {
  private final Class<? extends Persistent> persistentClass;

  private final Criteria criteria = Criteria.create();

  private final List<String> fields = new ArrayList<>();

  private Integer limit;

  private Integer offset;

  private Example(Class<? extends Persistent> persistentClass) {
    this.persistentClass = persistentClass;
  }

  public static Example create(Class<? extends Persistent> persistentClass) {
    return new Example(persistentClass);
  }

  public Class<? extends Persistent> persistentClass() {
    return persistentClass;
  }

  public Criteria criteria() {
    return criteria;
  }

  /**
   * 设置limit
   *
   * @param limit 现在返回的记录数
   * @return Example
   */
  public Example limit(int limit) {
    Preconditions.checkArgument(limit > -1, "limit must > 0");
    this.limit = limit;
    return this;
  }

  /**
   * 设置offset
   *
   * @param offset 偏移量
   * @return Example
   */
  public Example offset(int offset) {
    Preconditions.checkArgument(offset >= 0, "offset must >= 0");
    this.offset = offset;
    return this;
  }

  /**
   * 增加返回的字段
   *
   * @param field 字段
   * @return Example
   */
  public Example addField(String field) {
    MorePreconditions.checkNotNullOrEmpty(field, "field cannot be null");
    this.fields.add(field);
    return this;
  }

  /**
   * 增加返回的字段
   *
   * @param fields 字段的集合
   * @return Example
   */
  public Example addFields(List<String> fields) {
    MorePreconditions.checkNoNullElements(fields, "field cannot be null");
    fields.forEach(f -> addField(f));
    return this;
  }

  public int limit() {
    return limit;
  }

  public int offset() {
    return offset;
  }

  public List<String> fields() {
    return Collections.unmodifiableList(fields);
  }

}
