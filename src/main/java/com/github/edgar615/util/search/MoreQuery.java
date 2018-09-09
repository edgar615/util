package com.github.edgar615.util.search;

import com.github.edgar615.util.db.Persistent;
import com.google.common.base.Strings;

import java.util.List;

/**
 * @author Edgar
 * @create 2018-09-08 15:43
 **/
public class MoreQuery<ID, T extends Persistent<ID>> implements Expression {

  private final ExpressionOp op;
  private Persistent persistent;
  private Expression expression;

  private MoreQuery(Class<T> beanClass, ExpressionOp op) {
    this.persistent = Persistent.create(beanClass);
    this.op = op;
  }

  public static <ID, T extends Persistent<ID>> MoreQuery and(Class<T> beanClass) {
    return new MoreQuery(beanClass, ExpressionOp.AND);
  }

  public static <ID, T extends Persistent<ID>> MoreQuery or(Class<T> beanClass) {
    return new MoreQuery(beanClass, ExpressionOp.OR);
  }

  public static void main(String[] args) {
//    MoreQuery moreQuery = MoreQuery.or().eq("foo", "bar").eq("foo", "bar")
//            .inner(MoreQuery.and().ge("score", 100).eq("foo", "bar"));
////        .or().eq("foo", "bar");
////        .orNesting(MoreQuery.and().eq("foo", "bar"));
//    StringBuilder sql = new StringBuilder();
//    sql(moreQuery, sql);
//    System.out.println(sql);
  }

  private static String criterion(Criterion criterion) {
    return new StringBuilder().append(criterion.field())
            .append(" ")
            .append(criterion.op())
            .append(" ")
            .append(criterion.value())
            .toString();
  }

  public static void sql(Expression expression, StringBuilder sql) {
    if (expression == null) {
      return;
    }
    if (expression instanceof Criterion) {
      Criterion criterion = (Criterion) expression;
      sql.append(criterion(criterion));
      return;
    }
    if (expression instanceof AndExpression) {
      AndExpression andExpression = (AndExpression) expression;
      sql.append(appendAndOr(andExpression.getLeft(), andExpression.getRight(), "and"));
      return;
    }
    if (expression instanceof OrExpression) {
      OrExpression orExpression = (OrExpression) expression;
      sql.append(appendAndOr(orExpression.getLeft(), orExpression.getRight(), "or"));
      return;
    }
    if (expression instanceof MoreQuery) {
      MoreQuery moreQuery = (MoreQuery) expression;
      StringBuilder innerSql = new StringBuilder();
      sql(moreQuery.getExpression(), innerSql);
      sql.append(innerSql);
      return;
    }
  }

  private static String appendAndOr(Expression left, Expression right, String op) {
    StringBuilder sql = new StringBuilder();
    StringBuilder leftSql = new StringBuilder();
    StringBuilder rightSql = new StringBuilder();
    sql(left, leftSql);
    sql(right, rightSql);
    if (left instanceof MoreQuery) {
      sql.append("(").append(leftSql).append(")");
    } else {
      sql.append(leftSql);
    }
    sql.append(" ").append(op).append(" ");
    if (right instanceof MoreQuery) {
      sql.append("(").append(rightSql).append(")");
    } else {
      sql.append(rightSql);
    }
    return sql.toString();
  }

  private MoreQuery addCriterion(Criterion criterion) {
    if (persistent.fields().contains(criterion.field())) {
      add(criterion);
    }
    return this;
  }

  /**
   * = 查询
   *
   * @param field 查询字段
   * @param value 比较值
   * @return Criteria
   */
  public MoreQuery equalsTo(String field, Object value) {
    return addCriterion(new Criterion(field, Op.EQ, value));
  }

  /**
   * <> 查询
   *
   * @param field 查询字段
   * @param value 比较值 ，如果为null，忽略这个查询
   * @return MoreQuery
   */
  public MoreQuery notEqualsTo(String field, Object value) {
    if (value == null) {
      return this;
    }
    if (Strings.isNullOrEmpty(value.toString())) {
      return this;
    }
    return addCriterion(new Criterion(field, Op.NE, value));
  }

  /**
   * > 查询
   *
   * @param field 查询字段
   * @param value 比较值，如果为null，忽略这个查询
   * @return MoreQuery
   */
  public MoreQuery greaterThan(String field, Object value) {
    if (value == null) {
      return this;
    }
    if (Strings.isNullOrEmpty(value.toString())) {
      return this;
    }
    return addCriterion(new Criterion(field, Op.GT, value));
  }

  /**
   * >= 查询
   *
   * @param field 查询字段
   * @param value 比较值，如果为null，忽略这个查询
   * @return MoreQuery
   */
  public MoreQuery greaterThanOrEqualTo(String field, Object value) {
    if (value == null) {
      return this;
    }
    if (Strings.isNullOrEmpty(value.toString())) {
      return this;
    }
    return addCriterion(new Criterion(field, Op.GE, value));
  }

  /**
   * < 查询
   *
   * @param field 查询字段
   * @param value 比较值，如果为null，忽略这个查询
   * @return MoreQuery
   */
  public MoreQuery lessThan(String field, Object value) {
    if (value == null) {
      return this;
    }
    if (Strings.isNullOrEmpty(value.toString())) {
      return this;
    }
    return addCriterion(new Criterion(field, Op.LT, value));
  }

  /**
   * <= 查询
   *
   * @param field 查询字段
   * @param value 比较值，如果为null，忽略这个查询
   * @return MoreQuery
   */
  public MoreQuery lessThanOrEqualTo(String field, Object value) {
    if (value == null) {
      return this;
    }
    if (Strings.isNullOrEmpty(value.toString())) {
      return this;
    }
    return addCriterion(new Criterion(field, Op.LE, value));
  }

  /**
   * 包含 like'%...%'查询.
   *
   * @param field 查询字段
   * @param value 比较值，如果为null，忽略这个查询
   * @return MoreQuery
   */
  public MoreQuery contains(String field, Object value) {
    if (value == null) {
      return this;
    }
    if (Strings.isNullOrEmpty(value.toString())) {
      return this;
    }
    return addCriterion(new Criterion(field, Op.CN, value));
  }

  /**
   * 开始于 like'...%'查询
   *
   * @param field 查询字段
   * @param value 比较值，如果为null，忽略这个查询
   * @return MoreQuery
   */
  public MoreQuery startsWith(String field, Object value) {
    if (value == null) {
      return this;
    }
    if (Strings.isNullOrEmpty(value.toString())) {
      return this;
    }
    return addCriterion(new Criterion(field, Op.SW, value));
  }

  /**
   * 结束于 like '%...'查询.
   * <p>
   * <b>尽量少用</b>
   *
   * @param field 查询字段
   * @param value 比较值，如果为null，忽略这个查询
   * @return MoreQuery
   */
  public MoreQuery endsWtih(String field, Object value) {
    if (value == null) {
      return this;
    }
    if (Strings.isNullOrEmpty(value.toString())) {
      return this;
    }
    return addCriterion(new Criterion(field, Op.EW, value));
  }

  /**
   * in 查询
   *
   * @param field  查询字段
   * @param values 比较值，如果为null或空，忽略这个查询
   * @return MoreQuery
   */
  public MoreQuery in(String field, List<Object> values) {
    if (values == null || values.isEmpty()) {
      return this;
    }
    return addCriterion(new Criterion(field, Op.IN, values));
  }

  /**
   * not in 查询
   *
   * @param field  查询字段
   * @param values 比较值，如果为null或空，忽略这个查询
   * @return MoreQuery
   */
  public MoreQuery notIn(String field, List<Object> values) {
    if (values == null || values.isEmpty()) {
      return this;
    }
    return addCriterion(new Criterion(field, Op.NOT_IN, values));
  }

  /**
   * between查询
   *
   * @param field  查询字段
   * @param value1 比较值，如果为null，忽略这个查询
   * @param value2 比较值，如果为null，忽略这个查询
   * @return MoreQuery
   */
  public MoreQuery between(String field, Object value1, Object value2) {
    if (value1 == null || value2 == null) {
      return this;
    }
    if (Strings.isNullOrEmpty(value1.toString())
            && Strings.isNullOrEmpty(value2.toString())) {
      return this;
    }
    if (Strings.isNullOrEmpty(value1.toString())
            && !Strings.isNullOrEmpty(value2.toString())) {
      return lessThanOrEqualTo(field, value2);
    }
    if (!Strings.isNullOrEmpty(value1.toString())
            && Strings.isNullOrEmpty(value2.toString())) {
      return greaterThanOrEqualTo(field, value1);
    }
    return addCriterion(new Criterion(field, Op.BETWEEN, value1, value2));
  }

  /**
   * is null查询
   *
   * @param field 查询字段
   * @return MoreQuery
   */
  public MoreQuery isNull(String field) {
    if (Strings.isNullOrEmpty(field)) {
      return this;
    }
    return addCriterion(new Criterion(field, Op.IS_NULL));
  }

  /**
   * is not null查询
   *
   * @param field 查询字段
   * @return MoreQuery
   */
  public MoreQuery isNotNull(String field) {
    if (Strings.isNullOrEmpty(field)) {
      return this;
    }
    return addCriterion(new Criterion(field, Op.IS_NOT_NULL));
  }

  private void add(Expression right) {
    if (expression == null) {
      expression = right;
    } else if (op == ExpressionOp.AND) {
      expression = new AndExpression(expression, right);
    } else if (op == ExpressionOp.OR) {
      expression = new OrExpression(expression, right);
    }
  }

  public MoreQuery ge(String field, Object value) {
    Criterion criterion = new Criterion(field, Op.GE, value);
    add(criterion);
    return this;
  }

  public MoreQuery inner(MoreQuery moreQuery) {
    add(moreQuery);
    return this;
  }

  public Expression getExpression() {
    return expression;
  }

}
