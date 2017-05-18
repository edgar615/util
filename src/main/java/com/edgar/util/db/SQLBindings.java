package com.edgar.util.db;

import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * SQL.
 *
 * @author Edgar
 */
public class SQLBindings {

  private final String sql;

  private final List<Object> bindings;

  private SQLBindings(String sql, List<Object> bindings) {
    this.sql = sql;
    this.bindings = ImmutableList.copyOf(bindings);
  }

  public static SQLBindings create(String sql, List<Object> bindings) {
    return new SQLBindings(sql, bindings);
  }

  public String sql() {
    return sql;
  }

  public List<Object> bindings() {
    return bindings;
  }

}