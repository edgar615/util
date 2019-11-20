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

import com.github.edgar615.util.reflect.ReflectionException;
import com.github.edgar615.util.search.Example;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * 从数据库中加载数据的工具类,这个工具类会一直执行，直到load方法返回null或者空
 * Builder必须传入下列参数：
 * jdbc
 * elementType 实体的class
 * example 默认的查询条件，不应该带排序功能，因为这个工具类会用主键生序排列，如果增加了排序，会导致加载的数据不一致.
 * limit 每次加载的数量
 * consumer 加载到数据之后的处理类
 *
 * @author Edgar  Date 2018/5/18
 */
public class JdbcLoadAllAction<ID, T extends Persistent<ID>> implements LoadAllAction {
  private final Jdbc jdbc;

  private final Class<T> elementType;

  private final Example example;

  private final int limit;

  private final Consumer<List<T>> consumer;

  private PersistentKit<ID, T> kit;

  private JdbcLoadAllAction(Jdbc jdbc, Class<T> elementType, Example example, int limit,
                            Consumer<List<T>> consumer) {
    Objects.requireNonNull(jdbc);
    Objects.requireNonNull(elementType);
    Objects.requireNonNull(example);
    Objects.requireNonNull(consumer);
    this.jdbc = jdbc;
    this.elementType = elementType;
    try {
      this.kit = (PersistentKit<ID, T>) Class.forName(elementType.getName() + "Kit").newInstance();
    } catch (Exception e) {
      throw new ReflectionException("class not found");
    }
    this.example = example;
    this.limit = limit;
    this.consumer = consumer;
  }

  public static <ID, T extends Persistent<ID>> Builder<ID, T> builder() {
    return new Builder<>();
  }

  @Override
  public void execute() {
    load(null);
  }

  private void load(ID startPk) {
    Example newExample = Example.create();
    newExample.addCriteria(example.criteria());
    newExample.addFields(example.fields());
    Persistent persistent = newDomain(elementType);
    if (startPk != null) {
      newExample.greaterThan(kit.primaryField(), startPk);
    }
    newExample.asc(kit.primaryField());
    List<T> elements = jdbc.findByExample(elementType, newExample, 0, limit);
    if (elements == null || elements.isEmpty()) {
      return;
    }
    consumer.accept(elements);
    ID lastPk = elements.get(elements.size() - 1).id();
    load(lastPk);
  }

  private <ID> Persistent newDomain(Class<? extends Persistent<ID>> clazz) {
    try {
      return clazz.newInstance();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static class Builder<ID, T extends Persistent<ID>> {
    private Jdbc jdbc;

    private Class<T> elementType;

    private Example example;

    private int limit;

    private Consumer<List<T>> consumer;

    public Builder setJdbc(Jdbc jdbc) {
      this.jdbc = jdbc;
      return this;
    }

    public Builder setElementType(Class<T> elementType) {
      this.elementType = elementType;
      return this;
    }

    public Builder setExample(Example example) {
      this.example = example;
      return this;
    }

    public Builder setLimit(int limit) {
      this.limit = limit;
      return this;
    }

    public Builder setConsumer(Consumer<List<T>> consumer) {
      this.consumer = consumer;
      return this;
    }

    public JdbcLoadAllAction build() {
      return new JdbcLoadAllAction(jdbc, elementType, example, limit, consumer);
    }
  }
}
