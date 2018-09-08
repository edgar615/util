package com.github.edgar615.util.search;

/**
 * @author Edgar
 * @create 2018-09-08 16:35
 **/
public class AndExpression implements Expression {

  private final Expression left;

  private final Expression right;

  private final ExpressionOp op = ExpressionOp.AND;

  public AndExpression(Expression left, Expression right) {
    this.left = left;
    this.right = right;
  }

  public Expression getLeft() {
    return left;
  }

  public Expression getRight() {
    return right;
  }

  public ExpressionOp getOp() {
    return op;
  }
}
