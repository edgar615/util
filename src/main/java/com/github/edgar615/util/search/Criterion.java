package com.github.edgar615.util.search;

import com.google.common.base.MoreObjects;

/**
 * 查询标准.
 *
 * @author Edgar
 * @version 1.0
 */
public class Criterion implements Expression {

  /**
   * 查询字段.
   */
  private final String field;

  /**
   * 查询运算符
   */
  private final Op op;

  /**
   * 查询参数
   */
  private final Object value;

  /**
   * 查询参数，用于between的第二个参数
   */
  private final Object secondValue;

  Criterion(String field, Op op, Object value) {
    this(field, op, value, null);
  }

  Criterion(String field, Op op) {
    this(field, op, null, null);
  }

  Criterion(String field, Op op, Object value, Object secondValue) {
    this.field = field;
    this.op = op;
    this.value = value;
    this.secondValue = secondValue;
  }

  public String field() {
    return field;
  }

  public Op op() {
    return op;
  }

  public Object value() {
    return value;
  }

  public Object secondValue() {
    return secondValue;
  }

  @Override
  public int hashCode() {
    int result = field.hashCode();
    result = 31 * result + op.hashCode();
    result = 31 * result + (value != null ? value.hashCode() : 0);
    result = 31 * result + (secondValue != null ? secondValue.hashCode() : 0);
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Criterion criteria = (Criterion) o;

    if (!field.equals(criteria.field)) {
      return false;
    }
    if (op != criteria.op) {
      return false;
    }
    if (secondValue != null ? !secondValue.equals(criteria.secondValue) :
        criteria.secondValue != null) {
      return false;
    }
    return !(value != null ? !value.equals(criteria.value) : criteria.value != null);

  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("Criterion")
        .add("field", field)
        .add("op", op)
        .add("value", value)
        .add("secondValue", secondValue)
        .toString();
  }
}
