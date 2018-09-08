package com.github.edgar615.util.search;

import com.github.edgar615.util.base.MorePreconditions;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
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
public class Criteria implements Expression {

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
    return addCriterion(field, Op.NE, value);
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
    return addCriterion(field, Op.GT, value);
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
    return addCriterion(field, Op.GE, value);
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
    return addCriterion(field, Op.LT, value);
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
    return addCriterion(field, Op.LE, value);
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
    return addCriterion(field, Op.CN, value);
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
    return addCriterion(field, Op.SW, value);
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
    return addCriterion(field, Op.EW, value);
  }

  /**
   * in 查询
   *
   * @param field 查询字段
   * @param values 比较值
   * @return Criteria
   */
  public Criteria in(String field, List<Object> values) {
    Preconditions.checkNotNull(values);
    MorePreconditions.checkNotEmpty(values);
    return addCriterion(field, Op.IN, values);
  }

  /**
   * not in 查询
   *
   * @param field 查询字段
   * @param values 比较值
   * @return Criteria
   */
  public Criteria notIn(String field, List<Object> values) {
    Preconditions.checkNotNull(values);
    MorePreconditions.checkNotEmpty(values);
    return addCriterion(field, Op.NOT_IN, values);
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
    return addCriterion(field, Op.EQ, value);
  }

  /**
   * between查询
   *
   * @param field 查询字段
   * @param value1 比较值
   * @param value2 比较值
   * @return Criteria
   */
  public Criteria between(String field, Object value1, Object value2) {
    Preconditions.checkNotNull(value1);
    Preconditions.checkNotNull(value2);
    return addCriterion(field, Op.BETWEEN, value1, value2);
  }

  /**
   * is null查询
   *
   * @param field 查询字段
   * @return Criteria
   */
  public Criteria isNull(String field) {
    Preconditions.checkNotNull(field);
    return addCriterion(field, Op.IS_NULL);
  }

  /**
   * is not null查询
   *
   * @param field 查询字段
   * @return Criteria
   */
  public Criteria isNotNull(String field) {
    Preconditions.checkNotNull(field);
    return addCriterion(field, Op.IS_NOT_NULL);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("Criteria")
        .add("criteria", criteria)
        .toString();
  }

  Criteria addCriteria(List<Criterion> criteria) {
    this.criteria.addAll(criteria);
    return this;
  }

  /**
   * 增加一个查询条件
   *
   * @param field 查询字段
   * @param op 查询条件
   * @return Criteria
   */
  Criteria addCriterion(String field, Op op) {
    MorePreconditions.checkNotNullOrEmpty(field, "field cannot be null");
    criteria.add(new Criterion(field, op));
    return this;
  }

  /**
   * 增加一个查询条件
   *
   * @param field 查询字段
   * @param op 查询条件
   * @param value 比较值
   * @return Criteria
   */
  Criteria addCriterion(String field, Op op, Object value) {
    MorePreconditions.checkNotNullOrEmpty(field, "field cannot be null");
    Preconditions.checkNotNull(value, "value cannot be null");
    check(op, value);
    addCriterion(new Criterion(field, op, value));
    return this;
  }

  /**
   * 增加一个查询条件
   *
   * @param field 查询字段
   * @param op 查询条件
   * @param value1 比较值
   * @param value2 比较值
   * @return Criteria
   */
  Criteria addCriterion(String field, Op op, Object value1, Object value2) {
    MorePreconditions.checkNotNullOrEmpty(field, "field cannot be null");
    Preconditions.checkNotNull(value1, "value1 cannot be null");
    Preconditions.checkNotNull(value2, "value2 cannot be null");
    Preconditions.checkArgument(op == Op.BETWEEN, "op should be BETWEEN");
    check(op, value1);
    check(op, value2);
    addCriterion(new Criterion(field, op, value1, value2));
    return this;
  }

  Criteria addCriterion(Criterion criterion) {
    Preconditions.checkNotNull(criterion, "value1 cannot be null");
    this.criteria.add(criterion);
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
}