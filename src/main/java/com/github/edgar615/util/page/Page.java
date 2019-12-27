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

package com.github.edgar615.util.page;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import java.util.List;

/**
 * 简单的分页类，该类是一个不可变类. 只会显示总数和列表，没有页码之类的条件
 *
 * @param <T> 实体类
 * @author Edgar Zhang
 * @version 1.0
 */
public final class Page<T> {

  private final List<T> records;

  private final int totalRecords;


  private Page(int totalRecords, List<T> records) {
    this.totalRecords = totalRecords;
    this.records = ImmutableList.copyOf(records);
  }

  /**
   * 创建一个Pagination类
   *
   * @param totalRecords 总记录数
   * @param records 当前页显示的集合
   * @param <T> 实体类
   * @return Page
   */
  public static <T> Page<T> newInstance(int totalRecords,
      List<T> records) {
    return new Page<T>(totalRecords, records);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("Pagination")
        .add("totalRecords", totalRecords)
        .add("records", records)
        .toString();
  }

  public List<T> getRecords() {
    return records;
  }

  public int getTotalRecords() {
    return totalRecords;
  }
}
