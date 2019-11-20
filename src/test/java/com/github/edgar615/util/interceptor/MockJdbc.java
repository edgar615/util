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

package com.github.edgar615.util.interceptor;

import com.github.edgar615.util.db.Jdbc;
import com.github.edgar615.util.db.Persistent;
import com.github.edgar615.util.search.Example;
import com.github.edgar615.util.search.MoreExample;
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
  public <ID> ID insertAndGeneratedKey(Persistent<ID> persistent) {
    return null;
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

  @Override
  public <ID, T extends Persistent<ID>> int deleteByMoreExample(Class<T> elementType,
      MoreExample example) {
    return 0;
  }

  @Override
  public <ID> int updateByMoreExample(Persistent<ID> persistent, Map<String, Number> addOrSub,
      List<String> nullFields, MoreExample example) {
    return 0;
  }

  @Override
  public <ID, T extends Persistent<ID>> List<T> findByMoreExample(Class<T> elementType,
      MoreExample example) {
    return null;
  }

  @Override
  public <ID, T extends Persistent<ID>> List<T> findByMoreExample(Class<T> elementType,
      MoreExample example, int start, int limit) {
    return null;
  }

  @Override
  public <ID, T extends Persistent<ID>> int countByMoreExample(Class<T> elementType,
      MoreExample example) {
    return 0;
  }
}
