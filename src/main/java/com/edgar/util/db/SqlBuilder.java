package com.edgar.util.db;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import com.edgar.util.base.MorePreconditions;
import com.edgar.util.base.StringUtils;

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
   * @param clazz 领域类
   * @param id    主键
   * @param <ID>  主键类型
   * @return {@link SQLBindings}
   */
  public static <ID> SQLBindings findById(Class<? extends Persistent<ID>> clazz, ID id) {
    return findById(clazz, id, Lists.newArrayList());
  }

  /**
   * 根据主键查询.
   *
   * @param clazz  领域类
   * @param id     主键
   * @param fields 返回的属性列表
   * @param <ID>   主键类型
   * @return {@link SQLBindings}
   */
  public static <ID> SQLBindings findById(Class<? extends Persistent<ID>> clazz,
                                          ID id, List<String> fields) {
    Persistent<ID> domain = newDomain(clazz);
    String selectedField = "*";
    if (!fields.isEmpty()) {
      List<String> domainFields = domain.fields();
      long undefindedCount = fields.stream()
              .filter(f -> !domainFields.contains(f))
              .count();
      Preconditions.checkArgument(undefindedCount == 0,
                                  "fields contains %s undefined field", undefindedCount);
      selectedField = Joiner.on(",")
              .join(fields.stream().map(f -> StringUtils.underscoreName(f))
                            .collect(Collectors.toList()));
    }
    StringBuilder s = new StringBuilder();
    s.append("select ")
            .append(selectedField)
            .append(" from ")
            .append(StringUtils.underscoreName(clazz.getSimpleName()))
            .append(" where ")
            .append(StringUtils.underscoreName(domain.primaryField()))
            .append(" = ?");
    return SQLBindings.create(s.toString(), Arrays.asList(id));
  }

  /**
   * 根据主键删除.
   *
   * @param clazz 领域类
   * @param id    主键
   * @param <ID>  主键类型
   * @return {@link SQLBindings}
   */
  public static <ID> SQLBindings deleteById(Class<? extends Persistent<ID>> clazz, ID id) {
    Persistent<ID> domain = newDomain(clazz);
    StringBuilder s = new StringBuilder();
    s.append("delete from ")
            .append(StringUtils.underscoreName(clazz.getSimpleName()))
            .append(" where ")
            .append(StringUtils.underscoreName(domain.primaryField()))
            .append(" = ?");
    return SQLBindings.create(s.toString(), Arrays.asList(id));
  }

  /**
   * 根据主键更新.
   *
   * @param persistent 领域对象
   * @param id          主键
   * @param <ID>        主键类型
   * @return {@link SQLBindings}
   */
  public static <ID> SQLBindings updateById(Persistent<ID> persistent, ID id) {
    Map<String, Object> map = persistent.toMap();
    List<String> columns = new ArrayList<>();
    List<Object> params = new ArrayList<>();
    map.forEach((k, v) -> {
      if (v != null) {
        columns.add(StringUtils.underscoreName(k) + " = ?");
        params.add(v);
      }
    });
    MorePreconditions.checkNotEmpty(columns, "no update field");

    String tableName = StringUtils.underscoreName(persistent.getClass().getSimpleName());
    StringBuilder s = new StringBuilder();
    s.append("update ")
            .append(tableName)
            .append(" set ")
            .append(Joiner.on(",").join(columns))
            .append(" where ")
            .append(StringUtils.underscoreName(persistent.primaryField()))
            .append(" = ?");
    params.add(id);
    return SQLBindings.create(s.toString(), params);
  }

  /**
   * insert.
   *
   * @param persistent 领域对象
   * @param <ID>        主键类型
   * @return {@link SQLBindings}
   */
  public static <ID> SQLBindings insert(Persistent<ID> persistent) {
    Map<String, Object> map = persistent.toMap();
    List<String> columns = new ArrayList<>();
    List<String> prepare = new ArrayList<>();
    List<Object> params = new ArrayList<>();
    map.forEach((k, v) -> {
      if (v != null) {
        columns.add(StringUtils.underscoreName(k));
        prepare.add("?");
        params.add(v);
      }
    });

    String tableName = StringUtils.underscoreName(persistent.getClass().getSimpleName());
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

}
