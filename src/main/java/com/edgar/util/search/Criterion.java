package com.edgar.util.search;

/**
 * 查询标准.
 *
 * @author Edgar
 * @version 1.0
 */
public class Criterion {

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

  public Criterion(String field, Op op, Object value) {
    this(field, op, value, null);
  }

  public Criterion(String field, Op op) {
    this(field, op, null, null);
  }

  public Criterion(String field, Op op, Object value, Object secondValue) {
    this.field = field;
    this.op = op;
    this.value = value;
    this.secondValue = secondValue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Criterion criteria = (Criterion) o;

    if (!field.equals(criteria.field)) return false;
    if (op != criteria.op) return false;
    if (secondValue != null ? !secondValue.equals(criteria.secondValue) :
            criteria.secondValue != null) { return false; }
    return !(value != null ? !value.equals(criteria.value) : criteria.value != null);

  }

  @Override
  public int hashCode() {
    int result = field.hashCode();
    result = 31 * result + op.hashCode();
    result = 31 * result + (value != null ? value.hashCode() : 0);
    result = 31 * result + (secondValue != null ? secondValue.hashCode() : 0);
    return result;
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

}
