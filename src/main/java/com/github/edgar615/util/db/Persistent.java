package com.github.edgar615.util.db;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.HashMap;
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

    /**
     * @return 属性集合
     */
    List<String> fields();

    /**
     * @return 主键属性
     */
    String primaryField();

    /**
     * 转换为map对象
     *
     * @return
     */
    Map<String, Object> toMap();

    /**
     * 将map对象填充到实体中
     *
     * @param map
     */
    void fromMap(Map<String, Object> map);

    /**
     * 虚拟列，5.7新增，新增修改是要忽略这个属性
     *
     * @return
     */
    default List<String> virtualFields() {
        return Lists.newArrayList();
    }

    default void fillDefaultValue(Map<String, Object> defaultMap) {
        Map<String, Object> map = toMap();
        Map<String, Object> newMap = new HashMap<>();
        defaultMap.forEach((k, v) -> {
            if (map.get(k) == null) {
                newMap.put(k, v);
            }
        });
        fromMap(newMap);
    }

    void setId(ID id);

    /**
     * 设置自增主键
     *
     * @param key
     */
    void setGeneratedKey(Number key);
}