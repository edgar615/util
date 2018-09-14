package com.github.edgar615.util.db;

import com.github.edgar615.util.base.StringUtils;
import com.github.edgar615.util.search.AndExpression;
import com.github.edgar615.util.search.Criterion;
import com.github.edgar615.util.search.Example;
import com.github.edgar615.util.search.Expression;
import com.github.edgar615.util.search.Op;
import com.github.edgar615.util.search.OrExpression;
import com.github.edgar615.util.search.Select;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Edgar on 2017/5/18.
 *
 * @author Edgar  Date 2017/5/18
 */
public class SqlBuilder {

  private static final String REVERSE_KEY = "-";

  /**
   * 根据主键查询.
   *
   * @param elementType 持久化对象
   * @param id 主键
   * @param <ID> 主键类型
   * @return {@link SQLBindings}
   */
  public static <ID> SQLBindings findById(Class<? extends Persistent<ID>> elementType, ID id) {
    return findById(elementType, id, Lists.newArrayList());
  }

  /**
   * 根据主键查询.
   *
   * @param elementType 持久化对象
   * @param id 主键
   * @param fields 返回的属性列表
   * @param <ID> 主键类型
   * @return {@link SQLBindings}
   */
  public static <ID> SQLBindings findById(Class<? extends Persistent<ID>> elementType,
      ID id, List<String> fields) {
    Example example = primaryKeyExample(elementType, id);
    if (!fields.isEmpty()) {
      example.addFields(fields);
    }
    return findByExample(elementType, example);
  }

  /**
   * 根据条件查询.
   *
   * @param elementType 持久化对象 * @param example 返回的属性列表
   * @param <ID> 主键类型
   * @return {@link SQLBindings}
   */
  public static <ID> SQLBindings findByExample(Class<? extends Persistent<ID>> elementType,
      Example example) {
    example = removeUndefinedField(elementType, example);
    String selectedField;
    Persistent<ID> domain = Persistent.create(elementType);
    List<String> domainFields = domain.fields();
    if (example.fields().isEmpty()) {
      selectedField = Joiner.on(", ")
          .join(domainFields.stream()
              .map(f -> underscoreName(f))
              .collect(Collectors.toList()));
    } else {
      selectedField = Joiner.on(", ")
          .join(example.fields().stream()
              .filter(f -> domainFields.contains(f))
              .map(f -> underscoreName(f))
              .collect(Collectors.toList()));
    }
    StringBuilder sql = new StringBuilder();
    sql.append("select ")
        .append(selectedField)
        .append(" from ")
        .append(underscoreName(elementType.getSimpleName()));
    SQLBindings sqlBindings = SqlBuilder.whereSql(example.criteria());
    if (!Strings.isNullOrEmpty(sqlBindings.sql())) {
      sql.append(" where ").append(sqlBindings.sql());
    }
    if (!example.orderBy().isEmpty()) {
      sql.append(SqlBuilder.orderSql(example.orderBy()));
    }
    return SQLBindings.create(sql.toString(), sqlBindings.bindings());
  }

  /**
   * 根据条件查询.
   *
   * @param elementType 持久化对象 * @param example 返回的属性列表
   * @param offset offset
   * @param limit limit
   * @param <ID> 主键类型
   * @return {@link SQLBindings}
   */
  public static <ID> SQLBindings findByExample(Class<? extends Persistent<ID>> elementType,
      Example example, long offset, long limit) {
    SQLBindings sqlBindings = findByExample(elementType, example);
    StringBuilder sql = new StringBuilder(sqlBindings.sql());
    sql.append(" limit ?, ?");
    List<Object> args = new ArrayList<>(sqlBindings.bindings());
    args.add(offset);
    args.add(limit);
    return SQLBindings.create(sql.toString(), args);
  }

  /**
   * 根据条件查询总数.
   *
   * @param elementType 持久化对象 * @param example 返回的属性列表
   * @param <ID> 主键类型
   * @return {@link SQLBindings}
   */
  public static <ID> SQLBindings countByExample(Class<? extends Persistent<ID>> elementType,
      Example example) {
    example = removeUndefinedField(elementType, example);
    StringBuilder sql = new StringBuilder();
    sql.append("select count(*) from ")
        .append(underscoreName(elementType.getSimpleName()));
    SQLBindings sqlBindings = SqlBuilder.whereSql(example.criteria());
    if (!Strings.isNullOrEmpty(sqlBindings.sql())) {
      sql.append(" where ").append(sqlBindings.sql());
    }
    return SQLBindings.create(sql.toString(), sqlBindings.bindings());
  }

  /**
   * 根据主键删除.
   *
   * @param elementType 持久化对象
   * @param id 主键
   * @param <ID> 主键类型
   * @return {@link SQLBindings}
   */
  public static <ID> SQLBindings deleteById(Class<? extends Persistent<ID>> elementType, ID id) {
    Example example = primaryKeyExample(elementType, id);
    return deleteByExample(elementType, example);
  }

  /**
   * 根据条件删除.
   *
   * @param elementType 持久化对象
   * @param example 条件
   * @param <ID> 主键类型
   * @return {@link SQLBindings}
   */
  public static <ID> SQLBindings deleteByExample(Class<? extends Persistent<ID>> elementType,
      Example example) {
    example = removeUndefinedField(elementType, example);
    SQLBindings sqlBindings = SqlBuilder.whereSql(example.criteria());
    String tableName = StringUtils.underscoreName(elementType.getSimpleName());
    StringBuilder sql = new StringBuilder("delete from ")
        .append(tableName);
    if (!example.criteria().isEmpty()) {
      sql.append(" where ")
          .append(sqlBindings.sql());
    }

    return SQLBindings.create(sql.toString(), sqlBindings.bindings());
  }

  /**
   * 根据主键更新,忽略实体中的null.
   *
   * @param persistent 持久化对象
   * @param id 主键
   * @param <ID> 主键类型
   * @return {@link SQLBindings}
   */
  public static <ID> SQLBindings updateById(Persistent<ID> persistent,
      Map<String, Integer> addOrSub,
      List<String> nullFields, ID id) {
    Example example = Example.create().equalsTo(persistent.primaryField(), id);
    return updateByExample(persistent, addOrSub, nullFields, example);
  }

  /**
   * 根据条件更新,忽略实体中的null.
   *
   * @param persistent 持久化对象
   * @param example 条件
   * @param <ID> 主键类型
   * @return {@link SQLBindings}
   */
  public static <ID> SQLBindings updateByExample(Persistent<ID> persistent,
      Map<String, Integer> addOrSub,
      List<String> nullFields, Example example) {
    boolean noUpdated = persistent.toMap().values().stream()
        .allMatch(v -> v == null);
    boolean noAddOrSub = addOrSub == null
        || addOrSub.keySet().stream().allMatch(v -> !persistent.fields().contains(v));
    boolean noNull = nullFields == null
        || nullFields.stream().allMatch(v -> !persistent.fields().contains(v));
    if (noUpdated && noAddOrSub && noNull) {
      return null;
    }

    //对example做一次清洗，将表中不存在的条件删除，避免频繁出现500错误
    example = example.removeUndefinedField(persistent.fields());
    Map<String, Object> map = persistent.toMap();
    List<String> columns = new ArrayList<>();
    List<Object> params = new ArrayList<>();
    //忽略虚拟列
    List<String> virtualFields = persistent.virtualFields();
    map.forEach((k, v) -> {
      if (v != null && !virtualFields.contains(k)) {
        columns.add(StringUtils.underscoreName(k) + " = ?");
        params.add(v);
      }
    });
    if (addOrSub != null) {
      for (Map.Entry<String, Integer> entry : addOrSub.entrySet()) {
        String key = entry.getKey();
        if (persistent.fields().contains(key)) {
          String underscoreKey = StringUtils.underscoreName(key);
          if (entry.getValue() > 0) {
            columns.add(
                underscoreKey + " = " + underscoreKey + " + " + entry.getValue());
          } else {
            columns.add(
                underscoreKey + " = " + underscoreKey + " - " + ~(entry.getValue() - 1));
          }
        }
      }
    }
    if (nullFields != null) {
      List<String> nullColumns = nullFields.stream()
          .filter(f -> persistent.fields().contains(f))
          .map(f -> StringUtils.underscoreName(f))
          .map(f -> f + " = null")
          .collect(Collectors.toList());
      columns.addAll(nullColumns);
    }

    if (columns.isEmpty()) {
      return null;
    }

    String tableName = StringUtils.underscoreName(persistent.getClass().getSimpleName());
    StringBuilder sql = new StringBuilder();
    sql.append("update ")
        .append(tableName)
        .append(" set ")
        .append(Joiner.on(",").join(columns));
    List<Object> args = new ArrayList<>(params);
    if (!example.criteria().isEmpty()) {
      SQLBindings sqlBindings = SqlBuilder.whereSql(example.criteria());
      sql.append(" where ")
          .append(sqlBindings.sql());
      args.addAll(sqlBindings.bindings());
    }
    return SQLBindings.create(sql.toString(), args);
  }

  /**
   * insert.
   *
   * @param persistent 持久化对象
   * @param <ID> 主键类型
   * @return {@link SQLBindings}
   */

  public static <ID> SQLBindings insert(Persistent<ID> persistent) {
    Map<String, Object> map = persistent.toMap();
    List<String> columns = new ArrayList<>();
    List<String> prepare = new ArrayList<>();
    List<Object> params = new ArrayList<>();
    List<String> virtualFields = persistent.virtualFields();
    map.forEach((k, v) -> {
      if (v != null && !virtualFields.contains(k)) {
        columns.add(underscoreName(k));
        prepare.add("?");
        params.add(v);
      }
    });

    String tableName = underscoreName(persistent.getClass().getSimpleName());
    StringBuilder s = new StringBuilder();
    s.append("insert into ")
        .append(tableName)
        .append("(")
        .append(Joiner.on(",").join(columns))
        .append(") values(")
        .append(Joiner.on(",").join(prepare))
        .append(")");
    return SQLBindings.create(s.toString(), params);
  }

  /**
   * 构建更复杂的SQL.一般用的很少，目前还不是很完善.
   *
   * @param select 查询条件
   * @param <ID> 主键类型
   * @return {@link SQLBindings}
   */
  public static <ID> SQLBindings select(Select<ID, ? extends Persistent<ID>> select) {
    String selectedField;
    Persistent<ID> domain = Persistent.create(select.getElementType());
    List<String> domainFields = domain.fields();
    if (select.fields().isEmpty()) {
      selectedField = Joiner.on(", ")
          .join(domainFields.stream()
              .map(f -> underscoreName(f))
              .collect(Collectors.toList()));
    } else {
      selectedField = Joiner.on(", ")
          .join(select.fields().stream()
              .filter(f -> domainFields.contains(f))
              .map(f -> underscoreName(f))
              .collect(Collectors.toList()));
    }
    StringBuilder sql = new StringBuilder();
    sql.append("select ")
        .append(selectedField)
        .append(" from ")
        .append(underscoreName(select.getElementType().getSimpleName()));
    StringBuilder where = new StringBuilder();
    List<Object> bindings = new ArrayList<>();
    sql(select.getExpression(), where, bindings);
    if (!Strings.isNullOrEmpty(where.toString())) {
      sql.append(" where ").append(where.toString());
    }
    if (!select.orderBy().isEmpty()) {
      sql.append(SqlBuilder.orderSql(select.orderBy()));
    }
    return SQLBindings.create(sql.toString(), bindings);
  }

  /**
   * 构建更复杂的SQL.一般用的很少，目前还不是很完善.
   *
   * @param select 查询条件
   * @param offset offset
   * @param limit limit
   * @param <ID> 主键类型
   * @return {@link SQLBindings}
   */
  public static <ID> SQLBindings select(Select<ID, ? extends Persistent<ID>> select, long offset,
      long limit) {
    SQLBindings sqlBindings = select(select);
    StringBuilder sql = new StringBuilder(sqlBindings.sql());
    sql.append(" limit ?, ?");
    List<Object> args = new ArrayList<>(sqlBindings.bindings());
    args.add(offset);
    args.add(limit);
    return SQLBindings.create(sql.toString(), args);
  }

  /**
   * 构建更复杂的SQL，查询总数.一般用的很少，目前还不是很完善.
   *
   * @param select 查询条件
   * @param <ID> 主键类型
   * @return {@link SQLBindings}
   */
  public static <ID> SQLBindings countBySelect(Select<ID, ? extends Persistent<ID>> select) {
    StringBuilder sql = new StringBuilder();
    sql.append("select count(*) from ")
        .append(underscoreName(select.getElementType().getSimpleName()));
    StringBuilder where = new StringBuilder();
    List<Object> bindings = new ArrayList<>();
    sql(select.getExpression(), where, bindings);
    if (!Strings.isNullOrEmpty(where.toString())) {
      sql.append(" where ").append(where.toString());
    }
    return SQLBindings.create(sql.toString(), bindings);
  }

  private static <ID> Example primaryKeyExample(Class<? extends Persistent<ID>> elementType,
      ID id) {
    Persistent<ID> domain = Persistent.create(elementType);
    return Example.create().equalsTo(domain.primaryField(), id);
  }

  private static String orderSql(List<String> orderBy) {
    if (orderBy.isEmpty()) {
      return "";
    }
    List<String> sql = orderBy.stream()
        .distinct()
        .map(o -> {
          if (o.startsWith(REVERSE_KEY)) {
            return underscoreName(o.substring(1)) + " desc";
          }
          return underscoreName(o);
        }).collect(Collectors.toList());
    return " order by " + Joiner.on(",").join(sql);
  }

  private static SQLBindings whereSql(List<Criterion> criteria) {
    if (criteria.isEmpty()) {
      return SQLBindings.create("", new ArrayList<>());
    }
    List<String> sql = new ArrayList<>();
    List<Object> bindings = new ArrayList<>();
    criteria
        .forEach(c -> addSqlFromCriterion(sql, bindings, c));
    return SQLBindings.create(Joiner.on(" and ").join(sql), bindings);
  }

  private static <ID, T extends Persistent<ID>> List<String> removeUndefinedColumn(
      Class<T> elementType,
      List<String> fields) {
    Persistent<ID> persistent = Persistent.create(elementType);
    List<String> domainColumns = persistent.fields();
    return fields.stream()
        .filter(f -> domainColumns.contains(f))
        .collect(Collectors.toList());
  }

  private static <ID, T extends Persistent<ID>> Example removeUndefinedField(Class<T> elementType,
      Example example) {
    //对example做一次清洗，将表中不存在的条件删除，避免频繁出现500错误
    Persistent<ID> persistent = Persistent.create(elementType);
    example = example.removeUndefinedField(persistent.fields());
    return example;
  }

  private static void addSqlFromCriterion(List<String> sql, List<Object> bindings,
      Criterion criterion) {
    SQLBindings sqlBindings = criterion(criterion);
    sql.add(sqlBindings.sql());
    bindings.addAll(sqlBindings.bindings());
  }

  private static SQLBindings criterion(Criterion criterion) {
    StringBuilder sql = new StringBuilder();
    List<Object> bindings = new ArrayList<>();

    if (criterion.op() == Op.IS_NULL) {
      sql.append(underscoreName(criterion.field())).append(" is null");
    }
    if (criterion.op() == Op.IS_NOT_NULL) {
      sql.append(underscoreName(criterion.field())).append(" is not null");
    }
    if (criterion.op() == Op.EQ) {
      sql.append(underscoreName(criterion.field())).append(" = ?");
      bindings.add(criterion.value());
    }
    if (criterion.op() == Op.NE) {
      sql.append(underscoreName(criterion.field())).append(" <> ?");
      bindings.add(criterion.value());
    }
    if (criterion.op() == Op.GT) {
      sql.append(underscoreName(criterion.field())).append(" > ?");
      bindings.add(criterion.value());
    }
    if (criterion.op() == Op.GE) {
      sql.append(underscoreName(criterion.field())).append(" >= ?");
      bindings.add(criterion.value());
    }
    if (criterion.op() == Op.LT) {
      sql.append(underscoreName(criterion.field())).append(" < ?");
      bindings.add(criterion.value());
    }
    if (criterion.op() == Op.LE) {
      sql.append(underscoreName(criterion.field())).append(" <= ?");
      bindings.add(criterion.value());
    }
    if (criterion.op() == Op.SW) {
      sql.append(underscoreName(criterion.field())).append(" like ?");
      bindings.add(criterion.value() + "%");
    }
    if (criterion.op() == Op.EW) {
      sql.append(underscoreName(criterion.field())).append(" like ?");
      bindings.add("%" + criterion.value());
    }
    if (criterion.op() == Op.CN) {
      sql.append(underscoreName(criterion.field())).append(" like ?");
      bindings.add("%" + criterion.value() + "%");
    }
    if (criterion.op() == Op.BETWEEN) {
      sql.append(underscoreName(criterion.field())).append(" between ? and ?");
      bindings.add(criterion.value());
      bindings.add(criterion.secondValue());
    }
    if (criterion.op() == Op.IN) {
      List<Object> values = (List<Object>) criterion.value();
      List<String> strings = values.stream()
          .map(v -> "?")
          .collect(Collectors.toList());
      sql.append(underscoreName(criterion.field()) + " in ("
          + Joiner.on(",").join(strings)
          + ")");
      bindings.addAll(values);
    }
    if (criterion.op() == Op.NOT_IN) {
      List<Object> values = (List<Object>) criterion.value();
      List<String> strings = values.stream()
          .map(v -> "?")
          .collect(Collectors.toList());
      sql.append(underscoreName(criterion.field())).append(" not in (")
          .append(Joiner.on(",").join(strings)).append(")");
      bindings.addAll(values);
    }
    return SQLBindings.create(sql.toString(), bindings);
  }

  private static void sql(Expression expression, StringBuilder sql, List<Object> bindings) {
    if (expression == null) {
      return;
    }
    if (expression instanceof Criterion) {
      SQLBindings sqlBindings = criterion((Criterion) expression);
      sql.append(sqlBindings.sql());
      bindings.addAll(sqlBindings.bindings());
      return;
    }
    if (expression instanceof AndExpression) {
      AndExpression andExpression = (AndExpression) expression;
      SQLBindings sqlBindings = appendAndOr(andExpression.getLeft(), andExpression.getRight(),
          "and");
      sql.append(sqlBindings.sql());
      bindings.addAll(sqlBindings.bindings());
      return;
    }
    if (expression instanceof OrExpression) {
      OrExpression orExpression = (OrExpression) expression;
      SQLBindings sqlBindings = appendAndOr(orExpression.getLeft(), orExpression.getRight(), "or");
      sql.append(sqlBindings.sql());
      bindings.addAll(sqlBindings.bindings());
      return;
    }
    if (expression instanceof Select) {
      Select select = (Select) expression;
      StringBuilder innerSql = new StringBuilder();
      List<Object> innerBindings = new ArrayList<>();
      sql(select.getExpression(), innerSql, innerBindings);
      sql.append(innerSql);
      bindings.addAll(innerBindings);
      return;
    }
  }

  private static SQLBindings appendAndOr(Expression left, Expression right, String op) {
    StringBuilder sql = new StringBuilder();
    StringBuilder leftSql = new StringBuilder();
    StringBuilder rightSql = new StringBuilder();
    List<Object> leftBindings = new ArrayList<>();
    List<Object> rightBindings = new ArrayList<>();
    sql(left, leftSql, leftBindings);
    sql(right, rightSql, rightBindings);
    if (left instanceof Select) {
      sql.append("(").append(leftSql).append(")");
    } else {
      sql.append(leftSql);
    }
    sql.append(" ").append(op).append(" ");
    if (right instanceof Select) {
      sql.append("(").append(rightSql).append(")");
    } else {
      sql.append(rightSql);
    }
    List<Object> bindings = new ArrayList<>();
    bindings.addAll(leftBindings);
    bindings.addAll(rightBindings);
    return SQLBindings.create(sql.toString(), bindings);
  }

  private static String underscoreName(String name) {
    return StringUtils.underscoreName(name);
  }

}
