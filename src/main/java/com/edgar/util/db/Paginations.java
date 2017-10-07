package com.edgar.util.db;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Created by Edgar on 2017/8/15.
 *
 * @author Edgar  Date 2017/8/15
 */
public class Paginations {
  private Paginations() {
    throw new AssertionError("Not instantiable: " + Paginations.class);
  }

  public static <T extends Persistent> Pagination<Map> transformToMap(
          Pagination<T> pagination) {
    List<Map> mapRecords =
            pagination.getRecords().stream()
                    .map(r -> r.toMap())
                    .collect(Collectors.toList());
    return Pagination.newInstance(pagination.getPage(), pagination.getPageSize(),
            pagination.getTotalRecords(), mapRecords);
  }

  public static <T extends Persistent> Pagination<Map> transformToMap(
          Pagination<T> pagination, Consumer<Map> consumer) {
    List<Map> mapRecords =
            pagination.getRecords().stream()
                    .map(r -> r.toMap())
                    .collect(Collectors.toList());
    mapRecords.forEach(m -> consumer.accept(m));

    return Pagination.newInstance(pagination.getPage(), pagination.getPageSize(),
            pagination.getTotalRecords(), mapRecords);
  }

}
