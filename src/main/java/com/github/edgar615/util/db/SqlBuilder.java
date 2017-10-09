package com.github.edgar615.util.db;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import com.github.edgar615.util.base.MorePreconditions;
import com.github.edgar615.util.base.StringUtils;
import com.github.edgar615.util.search.Criterion;
import com.github.edgar615.util.search.Example;
import com.github.edgar615.util.search.Op;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Edgar on 2017/5/18.
 *
 * @author Edgar  Date 2017/5/18
 */
public class SqlBuilder {

  /**
   * 根据主键查询.
   *
   * @param elementType 持久化对象
   * @param id          主键
   * @param <ID>        主键类型
   * @return {@link SQLBindings}
   */
  public static <ID> SQLBindings findById(Class<? extends Persistent<ID>> elementType, ID id) {
    return findById(elementType, id, Lists.newArrayList());
  }

  /**
   * 根据主键查询.
   *
   * @param elementType 持久化对象
   * @param id          主键
   * @param fields      返回的属性列表
   * @param <ID>        主键类型
   * @return {@link SQLBindings}
   */
  public static <ID> SQLBindings findById(Class<? extends Persistent<ID>> elementType,
                                          ID id, List<String> fields) {
    return findById(newDomain(elementType), id, fields);
  }

  /**
   * 根据主键查询.
   *
   * @param domain 持久化对象
   * @param id     主键
   * @param fields 返回的属性列表
   * @param <ID>   主键类型
   * @return {@link SQLBindings}
   */
  public static <ID> SQLBindings findById(Persistent<ID> domain, ID id, List<String> fields) {
    String selectedField = "*";
    if (!fields.isEmpty()) {
      List<String> domainFields = domain.fields();
      long undefindedCount = fields.stream()
              .filter(f -> !domainFields.contains(f))
              .count();
      Preconditions.checkArgument(undefindedCount == 0,
                                  "fields contains %s undefined field", undefindedCount);
      selectedField = Joiner.on(",")
              .join(fields.stream().map(f -> underscoreName(f))
                            .collect(Collectors.toList()));
    }
    StringBuilder s = new StringBuilder();
    s.append("select ")
            .append(selectedField)
            .append(" from ")
            .append(underscoreName(domain.getClass().getSimpleName()))
            .append(" where ")
            .append(underscoreName(domain.primaryField()))
            .append(" = ?");
    return SQLBindings.create(s.toString(), Arrays.asList(id));
  }

  /**
   * 根据主键删除.
   *
   * @param clazz 持久化对象
   * @param id    主键
   * @param <ID>  主键类型
   * @return {@link SQLBindings}
   */
  public static <ID> SQLBindings deleteById(Class<? extends Persistent<ID>> clazz, ID id) {
    Persistent<ID> domain = newDomain(clazz);
    return deleteById(domain, id);
  }

  /**
   * 根据主键删除.
   *
   * @param domain 持久化对象
   * @param id     主键
   * @param <ID>   主键类型
   * @return {@link SQLBindings}
   */
  public static <ID> SQLBindings deleteById(Persistent<ID> domain, ID id) {
    StringBuilder s = new StringBuilder();
    s.append("delete from ")
            .append(underscoreName(domain.getClass().getSimpleName()))
            .append(" where ")
            .append(underscoreName(domain.primaryField()))
            .append(" = ?");
    return SQLBindings.create(s.toString(), Arrays.asList(id));
  }

  /**
   * 根据主键更新.
   *
   * @param persistent 持久化对象
   * @param id         主键
   * @param <ID>       主键类型
   * @return {@link SQLBindings}
   */
  public static <ID> SQLBindings updateById(Persistent<ID> persistent, ID id) {
    Map<String, Object> map = persistent.toMap();
    List<String> columns = new ArrayList<>();
    List<Object> params = new ArrayList<>();
    map.forEach((k, v) -> {
      if (v != null) {
        columns.add(underscoreName(k) + " = ?");
        params.add(v);
      }
    });
    MorePreconditions.checkNotEmpty(columns, "no update field");

    String tableName = underscoreName(persistent.getClass().getSimpleName());
    StringBuilder s = new StringBuilder();
    s.append("update ")
            .append(tableName)
            .append(" set ")
            .append(Joiner.on(",").join(columns))
            .append(" where ")
            .append(underscoreName(persistent.primaryField()))
            .append(" = ?");
    params.add(id);
    return SQLBindings.create(s.toString(), params);
  }

  /**
   * insert.
   *
   * @param persistent 持久化对象
   * @param <ID>       主键类型
   * @return {@link SQLBindings}
   */
  public static <ID> SQLBindings insert(Persistent<ID> persistent) {
    Map<String, Object> map = persistent.toMap();
    List<String> columns = new ArrayList<>();
    List<String> prepare = new ArrayList<>();
    List<Object> params = new ArrayList<>();
    map.forEach((k, v) -> {
      if (v != null) {
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

  private static <ID> Persistent newDomain(Class<? extends Persistent<ID>> clazz) {
    try {
      return clazz.newInstance();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static SQLBindings getSQL(Example example) {
    SQLBindings whereSql = whereSql(example.criteria());
    String orderBy = orderSql(example.orderBy());
    return SQLBindings.create(whereSql.sql() + " " + orderBy, whereSql.bindings());
  }

  public static String orderSql(List<String> orderBy) {
    if (orderBy.isEmpty()) {
      return "";
    }
    List<String> sql = orderBy.stream()
            .distinct()
            .map(o -> {
              if (o.startsWith("-")) {
                return underscoreName(o.substring(1)) + " desc";
              }
              return underscoreName(o);
            }).collect(Collectors.toList());
    return " order by " + Joiner.on(",").join(sql);
  }

  public static SQLBindings whereSql(List<Criterion> criteria) {
    if (criteria.isEmpty()) {
      return SQLBindings.create("", new ArrayList<>());
    }
    List<String> sql = new ArrayList<>();
    List<Object> bindings = new ArrayList<>();
    criteria
            .forEach(c -> {
              if (c.op() == Op.IS_NULL) {
                sql.add(underscoreName(c.field()) + " is null");
              }
              if (c.op() == Op.IS_NOT_NULL) {
                sql.add(underscoreName(c.field()) + " is not null");
              }
              if (c.op() == Op.EQ) {
                sql.add(underscoreName(c.field()) + " = ?");
                bindings.add(c.value());
              }
              if (c.op() == Op.NE) {
                sql.add(underscoreName(c.field()) + " <> ?");
                bindings.add(c.value());
              }
              if (c.op() == Op.GT) {
                sql.add(underscoreName(c.field()) + " > ?");
                bindings.add(c.value());
              }
              if (c.op() == Op.GE) {
                sql.add(underscoreName(c.field()) + " >= ?");
                bindings.add(c.value());
              }
              if (c.op() == Op.LT) {
                sql.add(underscoreName(c.field()) + " < ?");
                bindings.add(c.value());
              }
              if (c.op() == Op.LE) {
                sql.add(underscoreName(c.field()) + " <= ?");
                bindings.add(c.value());
              }
              if (c.op() == Op.SW) {
                sql.add(underscoreName(c.field()) + " like ?");
                bindings.add(c.value() + "%");
              }
              if (c.op() == Op.EW) {
                sql.add(underscoreName(c.field()) + " like ?");
                bindings.add("%" + c.value());
              }
              if (c.op() == Op.CN) {
                sql.add(underscoreName(c.field()) + " like ?");
                bindings.add("%" + c.value() + "%");
              }
              if (c.op() == Op.BETWEEN) {
                sql.add(underscoreName(c.field()) + " between ? and ?");
                bindings.add(c.value());
                bindings.add(c.secondValue());
              }
              if (c.op() == Op.IN) {
                List<Object> values = (List<Object>) c.value();
                List<String> strings = values.stream()
                        .map(v -> "?")
                        .collect(Collectors.toList());
                sql.add(underscoreName(c.field()) + " in ("
                        + Joiner.on(",").join(strings)
                        + ")");
                bindings.addAll(values);
              }
              if (c.op() == Op.NOT_IN) {
                List<Object> values = (List<Object>) c.value();
                List<String> strings = values.stream()
                        .map(v -> "?")
                        .collect(Collectors.toList());
                sql.add(underscoreName(c.field()) + " not in ("
                        + Joiner.on(",").join(strings)
                        + ")");
                bindings.addAll(values);
              }
            });
    return SQLBindings.create(Joiner.on(" and ").join(sql), bindings);
  }

  private static String underscoreName(String name) {
    return StringUtils.underscoreName(name);
  }

}