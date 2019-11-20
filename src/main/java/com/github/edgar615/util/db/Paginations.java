/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

//  public static <T extends Persistent> Pagination<Map> transformToMap(
//      Pagination<T> pagination) {
//    return transform(pagination, p -> p.kit().toMap());
//  }
//
//  public static <T extends Persistent> Pagination<Map> transformToMap(
//      Pagination<T> pagination, Consumer<Map> consumer) {
//    List<Map> mapRecords =
//        pagination.getRecords().stream()
//            .map(r -> r.kit().toMap())
//            .collect(Collectors.toList());
//    mapRecords.forEach(m -> consumer.accept(m));
//    return Pagination.newInstance(pagination.getPage(), pagination.getPageSize(),
//        pagination.getTotalRecords(), mapRecords);
//  }

}
