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
 * 分页类，该类是一个不可变类.
 *
 * @param <T> 实体类
 * @author Edgar Zhang
 * @version 1.0
 */
public final class Pagination<T> {

  private final List<T> records;

  private final int page;

  private final int pageSize;

  private final int totalRecords;

  private final int totalPages;

  private Pagination(int page, int pageSize, int totalRecords, List<T> records) {
    this.page = page;
    this.pageSize = pageSize;
    this.totalRecords = totalRecords;
    int pages = totalRecords / pageSize;
    if (totalRecords > pageSize * pages) {
      pages++;
    }
    this.totalPages = pages;
    this.records = ImmutableList.copyOf(records);
  }

  /**
   * 创建一个Pagination类
   *
   * @param page 页码
   * @param pageSize 每页显示的记录数
   * @param totalRecords 总记录数
   * @param records 当前页显示的集合
   * @param <T> 实体类
   * @return Pagination
   */
  public static <T> Pagination<T> newInstance(int page, int pageSize, int totalRecords,
      List<T> records) {
    return new Pagination<T>(page, pageSize, totalRecords, records);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("Pagination")
        .add("page", page)
        .add("pageSize", pageSize)
        .add("totalRecords", totalRecords)
        .add("totalPages", totalPages)
        .add("records", records)
        .toString();
  }

  public List<T> getRecords() {
    return records;
  }

  /**
   * 下一页页码
   *
   * @return 下一页页码
   */
  public int getNextPage() {
    if (this.page >= this.totalPages) {
      return this.page;
    }
    return this.page + 1;
  }

  /**
   * 上一页页码
   *
   * @return 上一页页码
   */
  public int getPrevPage() {
    if (this.page == 1) {
      return 1;
    }
    return this.page - 1;
  }

  public int getPage() {
    return page;
  }

  public int getPageSize() {
    return pageSize;
  }

  public int getTotalRecords() {
    return totalRecords;
  }

  public int getTotalPages() {
    return totalPages;
  }
}
