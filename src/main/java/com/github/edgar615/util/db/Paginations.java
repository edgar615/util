package com.github.edgar615.util.db;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
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


    public static <T extends Persistent, V> Pagination<V> transform(
            Pagination<T> pagination, Function<T, V> function) {
        List<V> mapRecords =
                pagination.getRecords().stream()
                        .map(r -> function.apply(r))
                        .collect(Collectors.toList());
        return Pagination.newInstance(pagination.getPage(), pagination.getPageSize(),
                pagination.getTotalRecords(), mapRecords);
    }

    public static <T extends Persistent> Pagination<Map> transformToMap(
            Pagination<T> pagination) {
        return transform(pagination, p -> p.toMap());
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
