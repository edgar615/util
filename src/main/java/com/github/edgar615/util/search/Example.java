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

package com.github.edgar615.util.search;

import com.github.edgar615.util.base.MorePreconditions;
import com.github.edgar615.util.base.StringUtils;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Edgar on 2017/5/19.
 *
 * @author Edgar  Date 2017/5/19
 */
public class Example {

  public static final String QUERY_SPLIT = ":";
  private static final Logger LOGGER = LoggerFactory.getLogger(Example.class);
  private static final String REVERSE_KEY = "-";
  private final Criteria criteria = Criteria.create();

  private final List<String> fields = new ArrayList<>();

  private final List<String> orderBy = new ArrayList<>();

  private boolean distinct = false;

  private Example() {
  }

  public static Example create() {
    return new Example();
  }

  /**
   * 根据传入的string，解析出对应的query.
   * <p>
   * <pre>
   * foo:bar foo=bar的条件
   * foo:"bar" foo="bar"的条件
   * type:1,2,3 type in (1,2,3)的条件
   * stars:>10 stars > 10的条件
   * created:>=2012-04-30
   * created:>2012-04-29
   * stars:10..* stars >= 10的条件
   * created:2012-04-30..*
   * stars:10..50
   * created:2012-04-30..2012-07-04
   * stars:<10
   * stars:<=9
   * created:<2012-07-05
   * created:<=2012-07-04
   * stars:* ..10
   * created:*..2012-04-30
   * stars:1..10
   * created:2012-04-30..2012-07-04
   * foo:*bar 以bar开头的条件
   * foo:bar* 以bar结尾的条件
   * foo:*bar* 包含bar的条件
   * -language:javascript 取反language !=javascript的条件 -created:<=2012-07-04表示created>2012-07-04
   * </pre>
   * <p>
   * 多个条件用空格组合，<b>空格会被URL编码为+</b>
   *
   * @param query 查询字符串
   * @return Example
   */
  public Example addQuery(String query) {
    if (Strings.isNullOrEmpty(query)) {
      return this;
    }
    if (CharMatcher.whitespace().matchesAllOf(query)) {
      return this;
    }
    query = query.trim();
    if (query.indexOf(QUERY_SPLIT) < 1) {
      return this;
    }
    addCriteria(SearchConvert.fromStr(query));
    return this;
  }

  public List<Criterion> criteria() {
    return criteria.criteria();
  }

  /**
   * 设置distinct
   *
   * @return Example
   */
  public Example withDistinct() {
    this.distinct = true;
    return this;
  }

  /**
   * <> 查询
   *
   * @param field 查询字段
   * @param value 比较值 ，如果为null，忽略这个查询
   * @return Example
   */
  public Example notEqualsTo(String field, Object value) {
    if (value == null) {
      return this;
    }
    if (Strings.isNullOrEmpty(value.toString())) {
      return this;
    }
    criteria.notEqualsTo(field, value);
    return this;
  }

  /**
   * > 查询
   *
   * @param field 查询字段
   * @param value 比较值，如果为null，忽略这个查询
   * @return Example
   */
  public Example greaterThan(String field, Object value) {
    if (value == null) {
      return this;
    }
    if (Strings.isNullOrEmpty(value.toString())) {
      return this;
    }
    criteria.greaterThan(field, value);
    return this;
  }

  /**
   * >= 查询
   *
   * @param field 查询字段
   * @param value 比较值，如果为null，忽略这个查询
   * @return Example
   */
  public Example greaterThanOrEqualTo(String field, Object value) {
    if (value == null) {
      return this;
    }
    if (Strings.isNullOrEmpty(value.toString())) {
      return this;
    }
    criteria.greaterThanOrEqualTo(field, value);
    return this;
  }

  /**
   * < 查询
   *
   * @param field 查询字段
   * @param value 比较值，如果为null，忽略这个查询
   * @return Example
   */
  public Example lessThan(String field, Object value) {
    if (value == null) {
      return this;
    }
    if (Strings.isNullOrEmpty(value.toString())) {
      return this;
    }
    criteria.lessThan(field, value);
    return this;
  }

  /**
   * <= 查询
   *
   * @param field 查询字段
   * @param value 比较值，如果为null，忽略这个查询
   * @return Example
   */
  public Example lessThanOrEqualTo(String field, Object value) {
    if (value == null) {
      return this;
    }
    if (Strings.isNullOrEmpty(value.toString())) {
      return this;
    }
    criteria.lessThanOrEqualTo(field, value);
    return this;
  }

  /**
   * 包含 like'%...%'查询
   *
   * <b>尽量少用</b>
   *
   * @param field 查询字段
   * @param value 比较值，如果为null，忽略这个查询
   * @return Example
   */
  public Example contains(String field, Object value) {
    if (value == null) {
      return this;
    }
    if (Strings.isNullOrEmpty(value.toString())) {
      return this;
    }
    criteria.contains(field, value);
    return this;
  }

  /**
   * 开始于 like'...%'查询
   *
   * @param field 查询字段
   * @param value 比较值，如果为null，忽略这个查询
   * @return Example
   */
  public Example startsWith(String field, Object value) {
    if (value == null) {
      return this;
    }
    if (Strings.isNullOrEmpty(value.toString())) {
      return this;
    }
    criteria.startsWith(field, value);
    return this;
  }

  /**
   * 结束于 like '%...'查询.
   * <p>
   * <b>尽量少用</b>
   *
   * @param field 查询字段
   * @param value 比较值，如果为null，忽略这个查询
   * @return Example
   */
  public Example endsWtih(String field, Object value) {
    if (value == null) {
      return this;
    }
    if (Strings.isNullOrEmpty(value.toString())) {
      return this;
    }
    criteria.endsWtih(field, value);
    return this;
  }

  /**
   * in 查询
   *
   * @param field 查询字段
   * @param values 比较值，如果为null或空，忽略这个查询
   * @return Example
   */
  public Example in(String field, List<Object> values) {
    if (values == null || values.isEmpty()) {
      return this;
    }
    criteria.in(field, values);
    return this;
  }

  /**
   * not in 查询
   *
   * @param field 查询字段
   * @param values 比较值，如果为null或空，忽略这个查询
   * @return Example
   */
  public Example notIn(String field, List<Object> values) {
    if (values == null || values.isEmpty()) {
      return this;
    }
    criteria.notIn(field, values);
    return this;
  }

  /**
   * = 查询
   *
   * @param field 查询字段
   * @param value 比较值，如果为null，忽略这个查询
   * @return Example
   */
  public Example equalsTo(String field, Object value) {
    if (value == null) {
      return this;
    }
    if (Strings.isNullOrEmpty(value.toString())) {
      return this;
    }
    criteria.equalsTo(field, value);
    return this;
  }

  /**
   * between查询
   *
   * @param field 查询字段
   * @param value1 比较值，如果为null，忽略这个查询
   * @param value2 比较值，如果为null，忽略这个查询
   * @return Example
   */
  public Example between(String field, Object value1, Object value2) {
    if (value1 == null || value2 == null) {
      return this;
    }
    if (Strings.isNullOrEmpty(value1.toString())
        && Strings.isNullOrEmpty(value2.toString())) {
      return this;
    }
    if (Strings.isNullOrEmpty(value1.toString())
        && !Strings.isNullOrEmpty(value2.toString())) {
      criteria.lessThanOrEqualTo(field, value2);
      return this;
    }
    if (!Strings.isNullOrEmpty(value1.toString())
        && Strings.isNullOrEmpty(value2.toString())) {
      criteria.greaterThanOrEqualTo(field, value1);
      return this;
    }
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
    if (Strings.isNullOrEmpty(field)) {
      return this;
    }
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
    if (Strings.isNullOrEmpty(field)) {
      return this;
    }
    criteria.isNotNull(field);
    return this;
  }

  /**
   * REGEXP 查询
   *
   * @param field 查询字段
   * @param value 比较值，如果为null，忽略这个查询
   * @return Example
   */
  public Example regexp(String field, Object value) {
    if (value == null) {
      return this;
    }
    if (Strings.isNullOrEmpty(value.toString())) {
      return this;
    }
    criteria.regexp(field, value);
    return this;
  }

  /**
   * 增加返回的字段
   *
   * @param field 字段
   * @return Example
   */
  public Example addField(String field) {
    if (Strings.isNullOrEmpty(field)) {
      return this;
    }
    if (this.fields.contains(field)) {
      return this;
    }
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
    if (fields == null || fields.isEmpty()) {
      return this;
    }
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
    if (Strings.isNullOrEmpty(field)) {
      return this;
    }
    if (orderBy.contains(field)) {
      return this;
    }
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
    if (Strings.isNullOrEmpty(field)) {
      return this;
    }
    MorePreconditions.checkNoNullElements(fields, "field cannot be null");
    String newField = REVERSE_KEY + field;
    if (orderBy.contains(newField)) {
      return this;
    }
    orderBy.add(newField);
    return this;
  }

  /**
   * 增加排序
   *
   * @param field 多个排序用逗号,分隔，降序排序需要在字段前面加上-
   * @return Example
   */
  public Example orderBy(String field) {
    if (Strings.isNullOrEmpty(field)) {
      return this;
    }
    List<String> fields = Splitter.on(",").trimResults()
        .omitEmptyStrings().splitToList(field);
    for (String order : fields) {
      if (order.startsWith(REVERSE_KEY)) {
        desc(order.substring(REVERSE_KEY.length()));
      } else {
        asc(order);
      }
    }
    return this;
  }

  public List<String> orderBy() {
    return ImmutableList.copyOf(orderBy);
  }

  public String orderSql() {
    if (orderBy.isEmpty()) {
      return "";
    }
    List<String> sql = orderBy.stream()
        .distinct()
        .map(o -> {
          if (o.startsWith(REVERSE_KEY)) {
            return StringUtils.underscoreName(o.substring(1)) + " desc";
          }
          return StringUtils.underscoreName(o);
        }).collect(Collectors.toList());
    return Joiner.on(",").join(sql);
  }

  public Example removeUndefinedField(List<String> definedFields) {
    Example copyExample = Example.create();
    if (isDistinct()) {
      copyExample.withDistinct();
    }
    this.criteria().stream()
        .filter(c -> definedFields.contains(c.field()))
        .forEach(c -> copyExample.addCriterion(c));
    //日志
    List<Criterion> criterias = this.criteria().stream()
        .filter(c -> !definedFields.contains(c.field()))
        .collect(Collectors.toList());
    if (!criterias.isEmpty()) {
      LOGGER.warn("remove undefined criterion:{}", criterias);
    }
    this.fields().stream()
        .filter(f -> definedFields.contains(f))
        .forEach(f -> copyExample.addField(f));
    //日志
    List<String> fields = this.fields().stream()
        .filter(f -> !definedFields.contains(f))
        .collect(Collectors.toList());
    if (!fields.isEmpty()) {
      LOGGER.warn("remove undefined field:{}", fields);
    }
    this.orderBy().stream()
        .filter(o -> {
          if (o.startsWith(REVERSE_KEY)) {
            return definedFields.contains(o.substring(1));
          }
          return definedFields.contains(o);
        })
        .forEach(o -> copyExample.orderBy(o));
    List<String> orderBy = this.fields().stream()
        .filter(o -> {
          if (o.startsWith(REVERSE_KEY)) {
            return !definedFields.contains(o.substring(1));
          }
          return !definedFields.contains(o);
        })
        .collect(Collectors.toList());
    if (!orderBy.isEmpty()) {
      LOGGER.warn("remove undefined sort:{}", orderBy);
    }
    return copyExample;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("Example")
        .add("criteria", criteria)
        .add("fields", fields)
        .add("orderBy", orderBy)
        .toString();
  }

  public Example addCriteria(List<Criterion> criteria) {
    this.criteria.addCriteria(criteria);
    return this;
  }

  public Example addCriterion(Criterion criterion) {
    this.criteria.addCriterion(criterion);
    return this;
  }

  public boolean isDistinct() {
    return distinct;
  }

}
