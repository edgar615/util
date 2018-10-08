package com.github.edgar615.util.interceptor;

import com.github.edgar615.util.db.Jdbc;
import com.github.edgar615.util.db.Persistent;
import com.github.edgar615.util.search.Example;
import java.util.List;
import java.util.Map;

/**
 * @author Edgar
 * @create 2018-09-14 16:32
 **/
public class MockJdbc implements Jdbc {

  @Override
  public <ID> void insert(Persistent<ID> persistent) {

  }

  @Override
  public <ID> void insertAndGeneratedKey(Persistent<ID> persistent) {

  }

  @Override
  public <ID, T extends Persistent<ID>> void batchInsert(List<T> persistentList) {

  }

  @Override
  public <ID, T extends Persistent<ID>> int deleteById(Class<T> elementType, ID id) {
    System.out.println("deleteById");
    return deleteByExample(elementType, Example.create());
  }

  @Override
  public <ID, T extends Persistent<ID>> int deleteByExample(Class<T> elementType, Example example) {
    System.out.println("deleteByExample");
    return 0;
  }

  @Override
  public <ID> int updateById(Persistent<ID> persistent, Map<String, Number> addOrSub,
      List<String> nullFields, ID id) {
    return 0;
  }

  @Override
  public <ID> int updateByExample(Persistent<ID> persistent, Map<String, Number> addOrSub,
      List<String> nullFields, Example example) {
    return 0;
  }

  @Override
  public <ID, T extends Persistent<ID>> T findById(Class<T> elementType, ID id,
      List<String> fields) {
    return null;
  }

  @Override
  public <ID, T extends Persistent<ID>> List<T> findByExample(Class<T> elementType,
      Example example) {
    return null;
  }

  @Override
  public <ID, T extends Persistent<ID>> List<T> findByExample(Class<T> elementType, Example example,
      int start, int limit) {
    return null;
  }

  @Override
  public <ID, T extends Persistent<ID>> int countByExample(Class<T> elementType, Example example) {
    return 0;
  }
}
