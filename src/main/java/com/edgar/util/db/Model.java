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
   * @param persistent 领域对象
   * @param <ID>       主键类型
   */
  <ID> void insert(Persistent<ID> persistent);

  /**
   * 根据主键删除.
   *
   * @param persistent 领域对象, <b>这个对象不需要设置任何属性，实现类需要通过这个对象获得对应的数据库属性</b>
   * @param id         主键
   * @param <ID>       主键类型
   * @param <T>        领域对象
   * @return
   */
  <ID, T extends Persistent<ID>> int deleteById(T persistent, ID id);

  /**
   * 根据条件删除.
   *
   * @param persistent 领域对象, <b>这个对象不需要设置任何属性，实现类需要通过这个对象获得对应的数据库属性</b>
   * @param criteria   条件集合
   * @param <ID>       主键类型
   * @param <T>        领域对象
   * @return
   */
  <ID, T extends Persistent<ID>> int deleteByCriteria(T persistent, List<Criterion> criteria);

  /**
   * 根据主键更新.
   *
   * @param persistent 领域对象
   * @param id         主键
   * @param <ID>       主键类型
   * @param <T>        领域对象
   * @return
   */
  <ID, T extends Persistent<ID>> int updateById(T persistent, ID id);

  /**
   * 根据条件更新.
   *
   * @param persistent 领域对象
   * @param criteria   条件集合
   * @param <ID>       条件集合
   * @param <T>        领域对象
   * @return
   */
  <ID, T extends Persistent<ID>> int updateByCriteria(T persistent, List<Criterion> criteria);

  /**
   * 根据主键查找.
   *
   * @param persistent 领域对象, <b>这个对象不需要设置任何属性，实现类需要通过这个对象获得对应的数据库属性</b>
   * @param id         主键
   * @param fields     返回的属性列表
   * @param <ID>       主键类型
   * @param <T>        领域对象
   * @return
   */
  <ID, T extends Persistent<ID>> T findById(T persistent, ID id, List<String> fields);

  /**
   * 根据条件查找.
   *
   * @param persistent 领域对象, <b>这个对象不需要设置任何属性，实现类需要通过这个对象获得对应的数据库属性</b>
   * @param example    查询参数的定义，包括查询条件、排序规则等
   * @param <ID>       主键类型
   * @param <T>        领域对象
   * @return
   */
  <ID, T extends Persistent<ID>> List<T> findByExample(T persistent, Example example);

  /**
   * 根据条件查找.
   *
   * @param persistent 领域对象, <b>这个对象不需要设置任何属性，实现类需要通过这个对象获得对应的数据库属性</b>
   * @param example    查询参数的定义，包括查询条件、排序规则等
   * @param start      开始索引
   * @param limit      查询数量
   * @param <ID>       主键类型
   * @param <T>        领域对象
   * @return
   */
  <ID, T extends Persistent<ID>> List<T> findByExample(T persistent, Example example, int start,
                                                       int limit);

  /**
   * 根据条件查找.
   *
   * @param persistent 领域对象, <b>这个对象不需要设置任何属性，实现类需要通过这个对象获得对应的数据库属性</b>
   * @param criteria   条件集合
   * @param <ID>       主键类型
   * @param <T>        领域对象
   * @return
   */
  <ID, T extends Persistent<ID>> int countByCriteria(T persistent, List<Criterion> criteria);

  /**
   * 分页查找.
   *
   * @param persistent 领域对象, <b>这个对象不需要设置任何属性，实现类需要通过这个对象获得对应的数据库属性</b>
   * @param example    查询参数的定义，包括查询条件、排序规则等
   * @param page       页码
   * @param pageSize   每页数量
   * @param <ID>       主键类型
   * @param <T>        领域对象
   * @return
   */
  default <ID, T extends Persistent<ID>> Pagination<T> pagination(T persistent, Example example,
                                                                  int page, int pageSize) {
    Preconditions.checkArgument(page > 0, "page must greater than 0");
    Preconditions.checkArgument(pageSize > 0, "pageSize must greater than 0");
    //查询总数
    final int totalRecords = countByCriteria(persistent, example.criteria());
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
    List<T> records = findByExample(persistent, example, offset, pageSize);
    return Pagination.newInstance(page, pageSize, totalRecords, records);
  }

  /**
   * 根据条件查找.
   *
   * @param persistent 领域对象, <b>这个对象不需要设置任何属性，实现类需要通过这个对象获得对应的数据库属性</b>
   * @param example    查询参数的定义，包括查询条件、排序规则等
   * @param <ID>       主键类型
   * @param <T>        领域对象
   * @return
   */
  default <ID, T extends Persistent<ID>> T findFirstByExample(T persistent, Example example) {
    List<T> result = findByExample(persistent, example);
    if (result == null || result.isEmpty()) {
      return null;
    }
    return result.get(0);
  }

  /**
   * 根据主键查找.
   *
   * @param persistent 领域对象, <b>这个对象不需要设置任何属性，实现类需要通过这个对象获得对应的数据库属性</b>
   * @param id         主键
   * @param <ID>       主键类型
   * @param <T>        领域对象
   * @return
   */
  default <ID, T extends Persistent<ID>> T findById(T persistent, ID id) {
    return findById(persistent, id, Lists.newArrayList());
  }

}
