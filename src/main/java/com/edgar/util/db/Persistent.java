package com.edgar.util.db;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Simple interface for entities.
 *
 * @param <ID> the type of the identifier
 */
public interface Persistent<ID> extends Serializable {

  /**
   * Returns the id of the entity.
   *
   * @return the id
   */
  ID id();

  void setId(ID id);

  /**
   * @return 属性集合
   */
  List<String> fields();

  /**
   *
   * @return 主键属性
   */
  String primaryField();

  Map<String, Object> toMap();

  /**
   * 设置自增主键
   * @param key
   */
  void setGeneratedKey(Number key);
}