package com.edgar.util.search;

/**
 * 查询标准.
 *
 * @author Edgar
 * @version 1.0
 */
public class Criteria {

  /**
   * 查询字段.
   */
  private String field;

  /**
   * 查询运算符
   */
  private Op op;

  /**
   * 查询参数
   */
  private Object value;

  /**
   * 查询参数，用于between的第二个参数
   */
  private Object secondValue;

  public Criteria(String field, Op op, Object value) {
    super();
    this.field = field;
    this.op = op;
    this.value = value;
  }

  public Criteria(String field, Op op) {
    super();
    this.field = field;
    this.op = op;
  }

  public Criteria(String field, Op op, Object value, Object secondValue) {
    this(field, op, value);
    this.secondValue = secondValue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Criteria criteria = (Criteria) o;

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

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public Op getOp() {
    return op;
  }

  public void setOp(Op op) {
    this.op = op;
  }

  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  public Object getSecondValue() {
    return secondValue;
  }

  public void setSecondValue(Object secondValue) {
    this.secondValue = secondValue;
  }
}
