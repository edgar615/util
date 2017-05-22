package com.edgar.util.search;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;

import com.edgar.util.base.MorePreconditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Edgar on 2017/5/19.
 *
 * @author Edgar  Date 2017/5/19
 */
public class Example {

  private final Criteria criteria = Criteria.create();

  private final List<String> fields = new ArrayList<>();

  private final List<String> orderBy = new ArrayList<>();

  private Example() {
  }

  public static Example create() {
    return new Example();
  }

  public Example addCriteria(List<Criterion> criteria) {
    criteria.addAll(criteria);
    return this;
  }

  public List<Criterion> criteria() {
    return criteria.criteria();
  }

  /**
   * <> 查询
   *
   * @param field 查询字段
   * @param value 比较值
   * @return Example
   */
  public Example notEqualsTo(String field, Object value) {
    criteria.notEqualsTo(field, value);
    return this;
  }

  /**
   * > 查询
   *
   * @param field 查询字段
   * @param value 比较值
   * @return Example
   */
  public Example greaterThan(String field, Object value) {
    criteria.greaterThan(field, value);
    return this;
  }

  /**
   * >= 查询
   *
   * @param field 查询字段
   * @param value 比较值
   * @return Example
   */
  public Example greaterThanOrEqualTo(String field, Object value) {
    criteria.greaterThanOrEqualTo(field, value);
    return this;
  }

  /**
   * < 查询
   *
   * @param field 查询字段
   * @param value 比较值
   * @return Example
   */
  public Example lessThan(String field, Object value) {
    criteria.lessThan(field, value);
    return this;
  }

  /**
   * <= 查询
   *
   * @param field 查询字段
   * @param value 比较值
   * @return Example
   */
  public Example lessThanOrEqualTo(String field, Object value) {
    criteria.lessThanOrEqualTo(field, value);
    return this;
  }

  /**
   * 包含 like'%...%'查询
   *
   * @param field 查询字段
   * @param value 比较值
   * @return Example
   */
  public Example contains(String field, Object value) {
    criteria.contains(field, value);
    return this;
  }

  /**
   * 开始于 like'...%'查询
   *
   * @param field 查询字段
   * @param value 比较值
   * @return Example
   */
  public Example startsWith(String field, Object value) {
    criteria.startsWith(field, value);
    return this;
  }

  /**
   * 结束于 like '%...'查询.
   * <p>
   * <b>尽量少用</b>
   *
   * @param field 查询字段
   * @param value 比较值
   * @return Example
   */
  public Example endsWtih(String field, Object value) {
    criteria.endsWtih(field, value);
    return this;
  }

  /**
   * in 查询
   *
   * @param field  查询字段
   * @param values 比较值
   * @return Example
   */
  public Example in(String field, List<Object> values) {
    criteria.in(field, values);
    return this;
  }

  /**
   * not in 查询
   *
   * @param field  查询字段
   * @param values 比较值
   * @return Example
   */
  public Example notIn(String field, List<Object> values) {
    criteria.notIn(field, values);
    return this;
  }

  /**
   * = 查询
   *
   * @param field 查询字段
   * @param value 比较值
   * @return Example
   */
  public Example equalsTo(String field, Object value) {
    criteria.equalsTo(field, value);
    return this;
  }

  /**
   * between查询
   *
   * @param field  查询字段
   * @param value1 比较值
   * @param value2 比较值
   * @return Example
   */
  public Example between(String field, Object value1, Object value2) {
    criteria.between(field, value1, value2);
    return this;
  }

  /**
   * is null查询
   *
   * @param field 查询字段
   * @return Example
   */
  public Example isNull(String field) {
    criteria.isNull(field);
    return this;
  }

  /**
   * is not null查询
   *
   * @param field 查询字段
   * @return Example
   */
  public Example isNotNull(String field) {
    criteria.isNotNull(field);
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

  public List<String> fields() {
    return Collections.unmodifiableList(fields);
  }

  /**
   * 增加生序排序
   *
   * @param field 排序字段
   * @return Example
   */
  public Example asc(String field) {
    MorePreconditions.checkNoNullElements(fields, "field cannot be null");
    orderBy.add(field);
    return this;
  }

  /**
   * 增加降序排序
   *
   * @param field 排序字段
   * @return Example
   */
  public Example desc(String field) {
    MorePreconditions.checkNoNullElements(fields, "field cannot be null");
    orderBy.add("-" + field);
    return this;
  }

  /**
   * 增加排序
   *
   * @param field 多个排序用逗号,分隔，降序排序需要在字段前面加上-
   * @return Example
   */
  public Example orderBy(String field) {
    MorePreconditions.checkNoNullElements(fields, "field cannot be null");
    orderBy.addAll(Splitter.on(",")
                           .trimResults()
                           .omitEmptyStrings()
                           .splitToList(field));
    return this;
  }

  public List<String> orderBy() {
    return ImmutableList.copyOf(orderBy);
  }
}
