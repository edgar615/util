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

package com.github.edgar615.util.search;

import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 字符串类型的query清洗，避免调用方任意传递query影响性能.
 *
 * @author Edgar
 * @create 2018-09-08 11:57
 **/
public class QueryCleanup {

  private final List<Map.Entry<String, Op>> prohibited = new ArrayList<>();

  private final List<Map.Entry<String, Op>> allowed = new ArrayList<>();

  private QueryCleanup() {
  }

  public static QueryCleanup create() {
    return new QueryCleanup();
  }

  /**
   * 添加一个禁止项.
   *
   * @param field 字段
   * @param op 条件
   */
  public void addProhibited(String field, Op op) {
    prohibited.add(Maps.immutableEntry(field, op));
  }

  /**
   * 添加一个允许.
   *
   * @param field 字段
   * @param op 条件
   */
  public void addAllowed(String field, Op op) {
    prohibited.add(Maps.immutableEntry(field, op));
  }

  /**
   * 删除query中禁止的查询，并转换为查询条件
   *
   * @param query 查询字符串
   * @return Criterion的集合
   */
  public List<Criterion> removeProhibited(String query) {
    List<Criterion> criteria = SearchConvert.fromStr(query);
    criteria.removeIf(c -> prohibited.stream()
        .anyMatch(p -> p.getKey().equals(c.field()) && p.getValue().equals(c.op())));
    return criteria;
  }

  /**
   * 删除query中没有设置为允许的查询，并转换为查询条件
   *
   * @param query 查询字符串
   * @return Criterion的集合
   */
  public List<Criterion> removeUnAllowed(String query) {
    List<Criterion> criteria = SearchConvert.fromStr(query);
    criteria.removeIf(c -> allowed.stream()
        .noneMatch(p -> p.getKey().equals(c.field()) && p.getValue().equals(c.op())));
    return criteria;
  }

}
