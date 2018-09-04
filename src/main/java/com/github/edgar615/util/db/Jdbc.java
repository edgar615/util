package com.github.edgar615.util.db;

import com.github.edgar615.util.search.Example;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据访问层的接口.
 *
 * @author Edgar  Date 2017/5/22
 */
public interface Jdbc {

  /**
   * 新增数据.
   *
   * @param persistent 持久化对象
   * @param <ID> 主键类型
   */
  <ID> void insert(Persistent<ID> persistent);

  /**
   * insert一条数据，并返回自增主键
   */
  <ID> void insertAndGeneratedKey(Persistent<ID> persistent);

  /**
   * 根据主键删除.
   *
   * @param elementType 持久化对象
   * @param id 主键
   * @param <ID> 主键类型
   * @param <T> 持久化对象
   */
  <ID, T extends Persistent<ID>> int deleteById(Class<T> elementType, ID id);

  /**
   * 根据条件删除.
   *
   * @param elementType 持久化对象
   * @param example 查询条件
   * @param <ID> 主键类型
   * @param <T> 持久化对象
   */
  <ID, T extends Persistent<ID>> int deleteByExample(Class<T> elementType, Example example);

  /**
   * 根据主键更新，忽略实体中的null
   *
   * @param persistent 持久化对象
   * @param addOrSub 需要做增加或者减去的字段，value为正数表示增加，负数表示减少
   * @param nullFields 需要设为null的字段
   * @param id 主键ID
   * @param <ID> 主键类型
   */
  <ID> int updateById(Persistent<ID> persistent,
      Map<String, Integer> addOrSub,
      List<String> nullFields,
      ID id);

  /**
   * 根据条件更新，忽略实体中的null
   *
   * @param persistent 持久化对象
   * @param addOrSub 需要做增加或者减去的字段，value为正数表示增加，负数表示减少
   * @param nullFields 需要设为null的字段
   * @param example 查询条件
   * @param <ID> 主键类型
   */
  <ID> int updateByExample(Persistent<ID> persistent,
      Map<String, Integer> addOrSub,
      List<String> nullFields, Example example);

  /**
   * 根据主键查找.
   *
   * @param elementType 持久化对象
   * @param id 主键
   * @param fields 返回的属性列表
   * @param <ID> 主键类型
   * @param <T> 持久化对象
   */
  <ID, T extends Persistent<ID>> T findById(Class<T> elementType, ID id, List<String> fields);

  /**
   * 根据条件查找.
   *
   * @param elementType 持久化对象,
   * @param example 查询参数的定义，包括查询条件、排序规则等
   * @param <ID> 主键类型
   * @param <T> 持久化对象
   */
  <ID, T extends Persistent<ID>> List<T> findByExample(Class<T> elementType, Example example);

  /**
   * 根据条件查找.
   *
   * @param elementType 持久化对象
   * @param example 查询参数的定义，包括查询条件、排序规则等
   * @param start 开始索引
   * @param limit 查询数量
   * @param <ID> 主键类型
   * @param <T> 持久化对象
   */
  <ID, T extends Persistent<ID>> List<T> findByExample(Class<T> elementType, Example example,
      int start,
      int limit);

  /**
   * 根据条件查找.
   *
   * @param elementType 持久化对象
   * @param example 查询条件
   * @param <ID> 主键类型
   * @param <T> 持久化对象
   */
  <ID, T extends Persistent<ID>> int countByExample(Class<T> elementType, Example example);

  /**
   * 根据主键更新，忽略实体中的null.
   *
   * @param persistent 持久化对象
   * @param id 主键
   * @param <ID> 主键类型
   */
  default <ID> int updateById(Persistent<ID> persistent, ID id) {
    return updateById(persistent, new HashMap<>(), new ArrayList<>(), id);
  }

  /**
   * 根据条件更新，忽略实体中的null.
   *
   * @param persistent 持久化对象
   * @param example 查询条件
   * @param <ID> 条件集合
   */
  default <ID> int updateByExample(Persistent<ID> persistent, Example example) {
    return updateByExample(persistent, new HashMap<>(), new ArrayList<>(), example);
  }

  /**
   * 分页查找.
   *
   * @param elementType 持久化对象
   * @param example 查询参数的定义，包括查询条件、排序规则等
   * @param page 页码
   * @param pageSize 每页数量
   * @param <ID> 主键类型
   * @param <T> 持久化对象
   */
  default <ID, T extends Persistent<ID>> Pagination<T> pagination(Class<T> elementType,
      Example example,
      int page, int pageSize) {
    Preconditions.checkArgument(page > 0, "page must greater than 0");
    Preconditions.checkArgument(pageSize > 0, "pageSize must greater than 0");
    //查询总数
    final int totalRecords = countByExample(elementType, example);
    if (totalRecords == 0) {
      return Pagination.newInstance(1, pageSize, totalRecords, Lists.newArrayList());
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
   * @param example 查询参数的定义，包括查询条件、排序规则等
   * @param <ID> 主键类型
   * @param <T> 持久化对象
   */
  default <ID, T extends Persistent<ID>> T findFirstByExample(Class<T> elementType,
      Example example) {
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
   * @param id 主键
   * @param <ID> 主键类型
   * @param <T> 持久化对象
   */
  default <ID, T extends Persistent<ID>> T findById(Class<T> elementType, ID id) {
    return findById(elementType, id, Lists.newArrayList());
  }

  /**
   * 根据条件查找，并返回总数.
   *
   * @param elementType 持久化对象
   * @param example 查询参数的定义，包括查询条件、排序规则等
   * @param start 开始索引
   * @param limit 查询数量
   * @param <ID> 主键类型
   * @param <T> 持久化对象
   */
  default <ID, T extends Persistent<ID>> Page<T> page(Class<T> elementType, Example example,
      int start,
      int limit) {
    List<T> records = findByExample(elementType, example, start, limit);
    //如果records的数量小于limit，说明已经没有记录，直接计算总数
    if (records.size() > 0 && records.size() < limit) {
      int total = start + records.size();
      return Page.newInstance(total, records);
    }
    //通过数据库查询总数
    final int totalRecords = countByExample(elementType, example);
    return Page.newInstance(totalRecords, records);
  }

}
