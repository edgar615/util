package com.github.edgar615.util.db;

import com.github.edgar615.util.search.Example;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基础DAO
 *
 * @author Edgar
 */
public interface BaseDao<ID, T extends Persistent<ID>> {

  /**
   * 新增数据.
   *
   * @param persistent 持久化对象
   */
  void insert(T persistent);

  /**
   * insert一条数据，并返回自增主键
   *
   * @param persistent 持久化对象
   */
  void insertAndGeneratedKey(T persistent);

  /**
   * 批量插入
   * @param persistentList 持久化对象的集合
   */
  void batchInsert(List<T> persistentList);

  /**
   * 根据主键删除.
   *
   * @param id 主键
   * @return 删除记录数
   */
  int deleteById(ID id);

  /**
   * 根据条件删除.
   *
   * @param example 查询条件
   * @return 删除记录数
   */
  int deleteByExample(Example example);

  /**
   * 根据主键更新，忽略实体中的null
   *
   * @param persistent 持久化对象
   * @param addOrSub 需要做增加或者减去的字段，value为正数表示增加，负数表示减少
   * @param nullFields 需要设为null的字段
   * @param id 主键ID
   * @return 修改记录数
   */
  int updateById(T persistent, Map<String, Number> addOrSub,
      List<String> nullFields,
      ID id);

  /**
   * 根据条件更新，忽略实体中的null
   *
   * @param persistent 持久化对象
   * @param addOrSub 需要做增加或者减去的字段，value为正数表示增加，负数表示减少
   * @param nullFields 需要设为null的字段
   * @param example 查询条件
   * @return 修改记录数
   */
  int updateByExample(T persistent, Map<String, Number> addOrSub,
      List<String> nullFields, Example example);

  /**
   * 根据主键查找.
   *
   * @param id 主键
   * @param fields 返回的属性列表
   * @return 持久化对象
   */
  T findById(ID id, List<String> fields);

  /**
   * 根据条件查找.
   *
   * @param example 查询参数的定义，包括查询条件、排序规则等
   * @return 持久化对象列表
   */
  List<T> findByExample(Example example);

  /**
   * 根据条件查找.
   *
   * @param example 查询参数的定义，包括查询条件、排序规则等
   * @param start 开始索引
   * @param limit 查询数量
   * @return 持久化对象列表
   */
  List<T> findByExample(Example example, int start, int limit);

  /**
   * 根据条件查找.
   *
   * @param example 查询条件
   * @return 记录数
   */
  int countByExample(Example example);

  /**
   * 根据主键更新，忽略实体中的null.
   *
   * @param persistent 持久化对象
   * @param id 主键
   * @return 修改记录数
   */
  default int updateById(T persistent, ID id) {
    return updateById(persistent, new HashMap<>(), new ArrayList<>(), id);
  }

  /**
   * 根据条件更新，忽略实体中的null.
   *
   * @param persistent 持久化对象
   * @param example 查询条件
   * @return 修改记录数
   */
  default int updateByExample(T persistent, Example example) {
    return updateByExample(persistent, new HashMap<>(), new ArrayList<>(), example);
  }

  /**
   * 分页查找.
   *
   * @param example 查询参数的定义，包括查询条件、排序规则等
   * @param page 页码
   * @param pageSize 每页数量
   * @return 分页对象
   */
  default Pagination<T> pagination(Example example,
      int page, int pageSize) {
    Preconditions.checkArgument(page > 0, "page must greater than 0");
    Preconditions.checkArgument(pageSize > 0, "pageSize must greater than 0");
    //查询总数
    final int totalRecords = countByExample(example);
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
    List<T> records = findByExample(example, offset, pageSize);
    return Pagination.newInstance(page, pageSize, totalRecords, records);
  }

  /**
   * 根据条件查找.
   *
   * @param example 查询参数的定义，包括查询条件、排序规则等
   * @return 持久化对象，如果未找到任何数据，返回null
   */
  default T findFirstByExample(Example example) {
    List<T> result = findByExample(example, 0, 1);
    if (result == null || result.isEmpty()) {
      return null;
    }
    return result.get(0);
  }

  /**
   * 根据主键查找.
   *
   * @param id 主键
   * @return 持久化对象，如果未找到任何数据，返回null
   */
  default T findById(ID id) {
    return findById(id, Lists.newArrayList());
  }

  /**
   * 根据条件查找，并返回总数.
   *
   * @param example 查询参数的定义，包括查询条件、排序规则等
   * @param start 开始索引
   * @param limit 查询数量
   * @return 分页对象
   */
  default Page<T> page(Example example,      int start,      int limit) {
    List<T> records = findByExample(example, start, limit);
    //如果records的数量小于limit，说明已经没有记录，直接计算总数
    if (records.size() > 0 && records.size() < limit) {
      int total = start + records.size();
      return Page.newInstance(total, records);
    }
    //通过数据库查询总数
    final int totalRecords = countByExample(example);
    return Page.newInstance(totalRecords, records);
  }
}