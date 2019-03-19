package com.github.edgar615.util.search;

import com.github.edgar615.util.base.MorePreconditions;
import com.github.edgar615.util.base.StringUtils;
import com.github.edgar615.util.db.Persistent;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 偏复杂的查询. 一般用的很少，目前还不是很完善.
 *
 * @author Edgar
 * @create 2018-09-08 15:43
 **/
public class MoreExample implements Expression {

  private static final String REVERSE_KEY = "-";
  private final ExpressionOp op;
  private final List<String> fields = new ArrayList<>();
  private final List<String> orderBy = new ArrayList<>();
  private Expression expression;
  private boolean distinct = false;

  private MoreExample(ExpressionOp op) {
    this.op = op;
  }

  public static <ID, T extends Persistent<ID>> MoreExample and() {
    return new MoreExample(ExpressionOp.AND);
  }

  public static <ID, T extends Persistent<ID>> MoreExample or() {
    return new MoreExample(ExpressionOp.OR);
  }

  /**
   * 增加返回的字段
   *
   * @param field 字段
   * @return Example
   */
  public MoreExample addField(String field) {
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
  public MoreExample addFields(List<String> fields) {
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
   * 设置distinct
   *
   * @return MoreExample
   */
  public MoreExample withDistinct() {
    this.distinct = true;
    return this;
  }

  /**
   * = 查询
   *
   * @param field 查询字段
   * @param value 比较值
   * @return MoreExample
   */
  public MoreExample equalsTo(String field, Object value) {
    return addCriterion(new Criterion(field, Op.EQ, value));
  }

  /**
   * <> 查询
   *
   * @param field 查询字段
   * @param value 比较值 ，如果为null，忽略这个查询
   * @return MoreExample
   */
  public MoreExample notEqualsTo(String field, Object value) {
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
   * @return MoreExample
   */
  public MoreExample greaterThan(String field, Object value) {
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
   * @return MoreExample
   */
  public MoreExample greaterThanOrEqualTo(String field, Object value) {
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
   * @return MoreExample
   */
  public MoreExample lessThan(String field, Object value) {
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
   * @return MoreExample
   */
  public MoreExample lessThanOrEqualTo(String field, Object value) {
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
   * @return MoreExample
   */
  public MoreExample contains(String field, Object value) {
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
   * @return MoreExample
   */
  public MoreExample startsWith(String field, Object value) {
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
   * @return MoreExample
   */
  public MoreExample endsWtih(String field, Object value) {
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
   * @param field 查询字段
   * @param values 比较值，如果为null或空，忽略这个查询
   * @return MoreExample
   */
  public MoreExample in(String field, List<Object> values) {
    if (values == null || values.isEmpty()) {
      return this;
    }
    return addCriterion(new Criterion(field, Op.IN, values));
  }

  /**
   * not in 查询
   *
   * @param field 查询字段
   * @param values 比较值，如果为null或空，忽略这个查询
   * @return MoreExample
   */
  public MoreExample notIn(String field, List<Object> values) {
    if (values == null || values.isEmpty()) {
      return this;
    }
    return addCriterion(new Criterion(field, Op.NOT_IN, values));
  }

  /**
   * between查询
   *
   * @param field 查询字段
   * @param value1 比较值，如果为null，忽略这个查询
   * @param value2 比较值，如果为null，忽略这个查询
   * @return MoreExample
   */
  public MoreExample between(String field, Object value1, Object value2) {
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
   * @return MoreExample
   */
  public MoreExample isNull(String field) {
    if (Strings.isNullOrEmpty(field)) {
      return this;
    }
    return addCriterion(new Criterion(field, Op.IS_NULL));
  }

  /**
   * is not null查询
   *
   * @param field 查询字段
   * @return MoreExample
   */
  public MoreExample isNotNull(String field) {
    if (Strings.isNullOrEmpty(field)) {
      return this;
    }
    return addCriterion(new Criterion(field, Op.IS_NOT_NULL));
  }

  /**
   * 增加生序排序
   *
   * @param field 排序字段
   * @return Example
   */
  public MoreExample asc(String field) {
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
  public MoreExample desc(String field) {
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
  public MoreExample orderBy(String field) {
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

  public MoreExample ge(String field, Object value) {
    Criterion criterion = new Criterion(field, Op.GE, value);
    add(criterion);
    return this;
  }

  public MoreExample inner(MoreExample moreExample) {
    add(moreExample);
    return this;
  }

  private MoreExample addCriterion(Criterion criterion) {
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

  public boolean isDistinct() {
    return distinct;
  }

  public Expression getExpression() {
    return expression;
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
}
