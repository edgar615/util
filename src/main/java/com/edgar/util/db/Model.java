package com.edgar.util.db;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import com.edgar.util.search.Criterion;
import com.edgar.util.search.Example;

import java.util.List;

/**
 * Created by Edgar on 2017/5/22.
 *
 * @author Edgar  Date 2017/5/22
 */
public interface Model {

  /**
   * 新增数据.
   *
   * @param persistent 持久化对象
   * @param <ID>       主键类型
   */
  <ID> void insert(Persistent<ID> persistent);

  /**
   * 根据主键删除.
   *
   * @param elementType 持久化对象
   * @param id         主键
   * @param <ID>       主键类型
   * @param <T>        持久化对象
   * @return
   */
  <ID, T extends Persistent<ID>> int deleteById(Class<T> elementType, ID id);

  /**
   * 根据条件删除.
   *
   * @param elementType 持久化对象
   * @param example   查询条件
   * @param <ID>       主键类型
   * @param <T>        持久化对象
   * @return
   */
  <ID, T extends Persistent<ID>> int deleteByCriteria(Class<T> elementType, Example example);

  /**
   * 根据主键更新.
   *
   * @param persistent 持久化对象
   * @param id         主键
   * @param <ID>       主键类型
   * @param <T>        持久化对象
   * @return
   */
  <ID, T extends Persistent<ID>> int updateById(Persistent<ID> persistent, ID id);

  /**
   * 根据条件更新.
   *
   * @param persistent 持久化对象
   * @param example   查询条件
   * @param <ID>       条件集合
   * @param <T>        持久化对象
   * @return
   */
  <ID, T extends Persistent<ID>> int updateByCriteria(Persistent<ID> persistent, Example example);

  /**
   * 根据主键查找.
   *
   * @param elementType 持久化对象
   * @param id         主键
   * @param fields     返回的属性列表
   * @param <ID>       主键类型
   * @param <T>        持久化对象
   * @return
   */
  <ID, T extends Persistent<ID>> T findById(Class<T> elementType, ID id, List<String> fields);

  /**
   * 根据条件查找.
   *
   * @param elementType 持久化对象,
   * @param example    查询参数的定义，包括查询条件、排序规则等
   * @param <ID>       主键类型
   * @param <T>        持久化对象
   * @return
   */
  <ID, T extends Persistent<ID>> List<T> findByExample(Class<T> elementType, Example example);

  /**
   * 根据条件查找.
   *
   * @param elementType 持久化对象
   * @param example    查询参数的定义，包括查询条件、排序规则等
   * @param start      开始索引
   * @param limit      查询数量
   * @param <ID>       主键类型
   * @param <T>        持久化对象
   * @return
   */
  <ID, T extends Persistent<ID>> List<T> findByExample(Class<T> elementType, Example example, int start,
                                                       int limit);

  /**
   * 根据条件查找.
   *
   * @param elementType 持久化对象
   * @param example   查询条件
   * @param <ID>       主键类型
   * @param <T>        持久化对象
   * @return
   */
  <ID, T extends Persistent<ID>> int countByCriteria(Class<T> elementType, Example example);

  /**
   * 分页查找.
   *
   * @param elementType 持久化对象
   * @param example    查询参数的定义，包括查询条件、排序规则等
   * @param page       页码
   * @param pageSize   每页数量
   * @param <ID>       主键类型
   * @param <T>        持久化对象
   * @return
   */
  default <ID, T extends Persistent<ID>> Pagination<T> pagination(Class<T> elementType, Example example,
                                                                  int page, int pageSize) {
    Preconditions.checkArgument(page > 0, "page must greater than 0");
    Preconditions.checkArgument(pageSize > 0, "pageSize must greater than 0");
    //查询总数
    final int totalRecords = countByCriteria(elementType, example);
    if (totalRecords == 0) {
      return Pagination.newInstance(page, pageSize, totalRecords, Lists.newArrayList());
    }
    int pageCount = totalRecords / pageSize;
    if (totalRecords > pageSize * pageCount) {
      pageCount++;
    }
    if (pageCount < page) {
      page = pageCount;
    }
    int offset = (page - 1) * pageSize;
    List<T> records = findByExample(elementType, example, offset, pageSize);
    return Pagination.newInstance(page, pageSize, totalRecords, records);
  }

  /**
   * 根据条件查找.
   *
   * @param elementType 持久化对象
   * @param example    查询参数的定义，包括查询条件、排序规则等
   * @param <ID>       主键类型
   * @param <T>        持久化对象
   * @return
   */
  default <ID, T extends Persistent<ID>> T findFirstByExample(Class<T> elementType, Example example) {
    List<T> result = findByExample(elementType, example);
    if (result == null || result.isEmpty()) {
      return null;
    }
    return result.get(0);
  }

  /**
   * 根据主键查找.
   *
   * @param elementType 持久化对象
   * @param id         主键
   * @param <ID>       主键类型
   * @param <T>        持久化对象
   * @return
   */
  default <ID, T extends Persistent<ID>> T findById(Class<T> elementType, ID id) {
    return findById(elementType, id, Lists.newArrayList());
  }

}
