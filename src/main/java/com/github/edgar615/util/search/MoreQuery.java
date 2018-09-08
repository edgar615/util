package com.github.edgar615.util.search;

import com.google.common.base.Joiner;

/**
 * @author Edgar
 * @create 2018-09-08 15:43
 **/
public class MoreQuery implements Expression {

  private Expression expression;

  private final ExpressionOp op;

  private MoreQuery(ExpressionOp op) {
    this.op = op;
  }

  public static MoreQuery and() {
    return new MoreQuery(ExpressionOp.AND);
  }

  public static MoreQuery or() {
    return new MoreQuery(ExpressionOp.OR);
  }


  public MoreQuery eq(String field, Object value) {
    Criterion criterion = new Criterion(field, Op.EQ, value);
    add(criterion);
    return this;
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

  public static void main(String[] args) {
    MoreQuery moreQuery = MoreQuery.and().eq("foo", "bar").eq("foo", "bar")
        .inner(MoreQuery.and().ge("score", 100));
//        .or().eq("foo", "bar");
//        .orNesting(MoreQuery.and().eq("foo", "bar"));
    StringBuilder sql = new StringBuilder();
    sql(moreQuery, sql);
    System.out.println(sql);
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
      Expression left = andExpression.getLeft();
      Expression right = andExpression.getRight();
      StringBuilder leftSql = new StringBuilder();
      StringBuilder rightSql = new StringBuilder();
      sql(left, leftSql);
      sql(right, rightSql);
      sql.append(leftSql.toString() + " and " + rightSql.toString());
      return;
    }
    if (expression instanceof OrExpression) {
      OrExpression orExpression = (OrExpression) expression;
      Expression left = orExpression.getLeft();
      Expression right = orExpression.getRight();
      StringBuilder leftSql = new StringBuilder();
      StringBuilder rightSql = new StringBuilder();
      sql(left, leftSql);
      sql(right, rightSql);
      sql.append(leftSql.toString()).append(" or ").append(rightSql.toString());
      return;
    }
    if (expression instanceof MoreQuery) {
      MoreQuery moreQuery = (MoreQuery) expression;
      StringBuilder innerSql = new StringBuilder();
      sql(moreQuery.getExpression(), innerSql);
      sql.append("(").append(innerSql.toString()).append(")");
      return;
    }
  }

}
