package com.edgar.util.search;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

import com.edgar.util.base.MorePreconditions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询定义，目前仅支持and查询，不支持or
 *
 * @author Edgar
 */
public class Criteria {

  private final List<Criterion> criteria = new ArrayList<>();

  private Criteria() {
    super();
  }

  /**
   * 创建Query
   *
   * @return Criteria
   */
  public static Criteria create() {
    return new Criteria();
  }

  /**
   * 根据传入的string，解析出对应的query.
   * <p>
   * <pre>
   * foo:bar foo=bar的条件
   * foo:"bar" foo="bar"的条件
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
   * @param queryString 查询字符串
   * @return Criteria
   */
  public static Criteria fromStr(String queryString) {
    Criteria criteria = new Criteria();
    SearchHelper.fromStr(queryString)
            .forEach(c -> criteria.addCriteria(c));
    return criteria;
  }

  public List<Criterion> criteria() {
    return ImmutableList.copyOf(criteria);
  }

  /**
   * <> 查询
   *
   * @param field 查询字段
   * @param value 比较值
   * @return Criteria
   */
  public Criteria notEqualsTo(String field, Object value) {
    Preconditions.checkNotNull(value);
    return addCriteria(field, Op.NE, value);
  }

  /**
   * > 查询
   *
   * @param field 查询字段
   * @param value 比较值
   * @return Criteria
   */
  public Criteria greaterThan(String field, Object value) {
    Preconditions.checkNotNull(value);
    return addCriteria(field, Op.GT, value);
  }

  /**
   * >= 查询
   *
   * @param field 查询字段
   * @param value 比较值
   * @return Criteria
   */
  public Criteria greaterThanOrEqualTo(String field, Object value) {
    Preconditions.checkNotNull(value);
    return addCriteria(field, Op.GE, value);
  }

  /**
   * < 查询
   *
   * @param field 查询字段
   * @param value 比较值
   * @return Criteria
   */
  public Criteria lessThan(String field, Object value) {
    Preconditions.checkNotNull(value);
    return addCriteria(field, Op.LT, value);
  }

  /**
   * <= 查询
   *
   * @param field 查询字段
   * @param value 比较值
   * @return Criteria
   */
  public Criteria lessThanOrEqualTo(String field, Object value) {
    Preconditions.checkNotNull(value);
    return addCriteria(field, Op.LE, value);
  }

  /**
   * 包含 like'%...%'查询
   *
   * @param field 查询字段
   * @param value 比较值
   * @return Criteria
   */
  public Criteria contains(String field, Object value) {
    Preconditions.checkNotNull(value);
    return addCriteria(field, Op.CN, value);
  }

  /**
   * 开始于 like'...%'查询
   *
   * @param field 查询字段
   * @param value 比较值
   * @return Criteria
   */
  public Criteria startsWith(String field, Object value) {
    Preconditions.checkNotNull(value);
    return addCriteria(field, Op.SW, value);
  }

  /**
   * 结束于 like '%...'查询.
   * <p>
   * <b>尽量少用</b>
   *
   * @param field 查询字段
   * @param value 比较值
   * @return Criteria
   */
  public Criteria endsWtih(String field, Object value) {
    Preconditions.checkNotNull(value);
    return addCriteria(field, Op.EW, value);
  }

  /**
   * in 查询
   *
   * @param field  查询字段
   * @param values 比较值
   * @return Criteria
   */
  public Criteria in(String field, List<Object> values) {
    Preconditions.checkNotNull(values);
    MorePreconditions.checkNotEmpty(values);
    return addCriteria(field, Op.IN, values);
  }

  /**
   * not in 查询
   *
   * @param field  查询字段
   * @param values 比较值
   * @return Criteria
   */
  public Criteria notIn(String field, List<Object> values) {
    Preconditions.checkNotNull(values);
    MorePreconditions.checkNotEmpty(values);
    return addCriteria(field, Op.NOT_IN, values);
  }

  /**
   * = 查询
   *
   * @param field 查询字段
   * @param value 比较值
   * @return Criteria
   */
  public Criteria equalsTo(String field, Object value) {
    Preconditions.checkNotNull(value);
    return addCriteria(field, Op.EQ, value);
  }

  /**
   * between查询
   *
   * @param field  查询字段
   * @param value1 比较值
   * @param value2 比较值
   * @return Criteria
   */
  public Criteria between(String field, Object value1, Object value2) {
    Preconditions.checkNotNull(value1);
    Preconditions.checkNotNull(value2);
    return addCriteria(field, Op.BETWEEN, value1, value2);
  }

  /**
   * is null查询
   *
   * @param field 查询字段
   * @return Criteria
   */
  public Criteria isNull(String field) {
    Preconditions.checkNotNull(field);
    return addCriteria(field, Op.IS_NULL);
  }

  /**
   * is not null查询
   *
   * @param field 查询字段
   * @return Criteria
   */
  public Criteria isNotNull(String field) {
    Preconditions.checkNotNull(field);
    return addCriteria(field, Op.IS_NOT_NULL);
  }

  /**
   * 增加一个查询条件
   *
   * @param field 查询字段
   * @param op    查询条件
   * @return Criteria
   */
  private Criteria addCriteria(String field, Op op) {
    MorePreconditions.checkNotNullOrEmpty(field, "field cannot be null");
    criteria.add(new Criterion(field, op));
    return this;
  }

  private Object check(Op op, Object val) {
    if (val == null) {
      throw new IllegalStateException("Illegal type in Event Content: " + val.getClass());
    }
    if (op == Op.IN || op == Op.NOT_IN) {
      if (val instanceof List) {
        val = new ArrayList<>((List) val);
      } else {
        throw new IllegalStateException("Illegal type in Event Content: " + val.getClass());
      }
    } else {
      if (val instanceof Number && !(val instanceof BigDecimal)) {
        // OK
      } else if (val instanceof Boolean) {
        // OK
      } else if (val instanceof String) {
        // OK
      } else if (val instanceof Character) {
        // OK
      } else if (val instanceof CharSequence) {
        val = val.toString();
      } else if (val instanceof Map) {
        val = new HashMap<>((Map) val);
      } else if (val instanceof List) {
        val = new ArrayList<>((List) val);
      } else {
        throw new IllegalStateException("Illegal type in Event Content: " + val.getClass());
      }
    }
    return val;
  }

  /**
   * 增加一个查询条件
   *
   * @param field 查询字段
   * @param op    查询条件
   * @param value 比较值
   * @return Criteria
   */
  private Criteria addCriteria(String field, Op op, Object value) {
    MorePreconditions.checkNotNullOrEmpty(field, "field cannot be null");
    Preconditions.checkNotNull(value, "value cannot be null");
    check(op, value);
    addCriteria(new Criterion(field, op, value));
    return this;
  }

  /**
   * 增加一个查询条件
   *
   * @param field  查询字段
   * @param op     查询条件
   * @param value1 比较值
   * @param value2 比较值
   * @return Criteria
   */
  private Criteria addCriteria(String field, Op op, Object value1, Object value2) {
    MorePreconditions.checkNotNullOrEmpty(field, "field cannot be null");
    Preconditions.checkNotNull(value1, "value1 cannot be null");
    Preconditions.checkNotNull(value2, "value2 cannot be null");
    Preconditions.checkArgument(op == Op.BETWEEN, "op should be BETWEEN");
    check(op, value1);
    check(op, value2);
    addCriteria(new Criterion(field, op, value1, value2));
    return this;
  }

  private Criteria addCriteria(Criterion criteria) {
    Preconditions.checkNotNull(criteria, "value1 cannot be null");
    this.criteria.add(criteria);
    return this;
  }
}