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

import com.github.edgar615.util.base.StringUtils;
import com.google.common.base.MoreObjects;
import java.util.List;

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

  public String condition() {

    if (op == Op.IS_NULL) {
      return " is null";
    }
    if (op == Op.IS_NOT_NULL) {
      return " is not null";
    }
    if (op == Op.EQ) {
      return " = ";
    }
    if (op == Op.NE) {
      return " <> ";
    }
    if (op == Op.GT) {
      return " > ";
    }
    if (op == Op.GE) {
      return " >= ";
    }
    if (op == Op.LT) {
      return " < ";
    }
    if (op == Op.LE) {
      return " <= ";
    }
    if (op == Op.SW) {
      return " like ";
    }
    if (op == Op.EW) {
      return " like ";
    }
    if (op == Op.CN) {
      return " like ";
    }
    if (op == Op.BETWEEN) {
      return " between ";
    }
    if (op == Op.REGEXP) {
      return " regexp ";
    }
    if (op == Op.IN) {
      return " in ";
    }
    if (op == Op.NOT_IN) {
      return " not in ";
    }
    throw new UnsupportedOperationException(op.name());
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

  // 为了兼容mybatis，实现的方法
  public String underscoreFiled() {
    return StringUtils.underscoreName(field);
  }

  public Object getLikeValue() {
    if (op == Op.SW) {
      return value+ "%";
    }
    if (op == Op.EW) {
      return "%" + value;
    }
    if (op == Op.CN) {
      return "%" + value + "%";
    }
    return value;
  }

  public Object getValue() {
    return value;
  }

  public Object getSecondValue() {
    return secondValue;
  }

  public boolean noValue() {
    return value == null;
  }

  public boolean singleValue() {
    return value != null && secondValue == null && !(value instanceof List);
  }

  public boolean betweenValue() {
    return value != null && secondValue != null && op == Op.BETWEEN;
  }

  public boolean listValue() {
    return value != null && value instanceof List;
  }

}
