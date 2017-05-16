package com.edgar.util.search;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Edgar on 2017/5/16.
 *
 * @author Edgar  Date 2017/5/16
 */
public class SqlHelper {
  public static SQLBindings getSQL(Query query) {
    List<String> sql = new ArrayList<>();
    List<Object> bindings = new ArrayList<>();
    query.criterias()
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
              if (c.op() == Op.GOE) {
                sql.add(underscoreName(c.field()) + " >= ?");
                bindings.add(c.value());
              }
              if (c.op() == Op.LT) {
                sql.add(underscoreName(c.field()) + " < ?");
                bindings.add(c.value());
              }
              if (c.op() == Op.LOE) {
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
                        .map(v -> ",")
                        .collect(Collectors.toList());
                sql.add(underscoreName(c.field()) + " in ("
                        + Joiner.on(",").join(strings)
                        + ")");
                bindings.addAll(values);
              }
              if (c.op() == Op.NOT_IN) {
                List<Object> values = (List<Object>) c.value();
                List<String> strings = values.stream()
                        .map(v -> ",")
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
    StringBuilder result = new StringBuilder();
    result.append(name.substring(0, 1).toLowerCase());
    for (int i = 1; i < name.length(); i++) {
      String s = name.substring(i, i + 1);
      String slc = s.toLowerCase();
      if (!s.equals(slc)) {
        result.append("_").append(slc);
      } else {
        result.append(s);
      }
    }
    return result.toString();
  }
}
