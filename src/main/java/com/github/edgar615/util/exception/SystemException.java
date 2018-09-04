package com.github.edgar615.util.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 开发过程中需要的异常都使用此异常。 该异常使用错误码ErrorCode来区分不同类型的异常
 * <p>创建对象：
 * </p>
 * <pre>
 *   SystemException.create(AppErrorCode.RESOURCE_NOT_FOUND);
 * </pre>
 * <p>将一个异常对象包装成SystemException：
 * </p>
 * <pre>
 *   SystemException.wrap(AppErrorCode.INVALID_ARGS, ex);
 * </pre>
 * <p>对SystemException设置以下额外属性：
 * </p>
 * <pre>
 *   SystemException.wrap(AppErrorCode.INVALID_ARGS, ex).set("foo", "bar");
 * </pre>
 */
public class SystemException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private final Map<String, Object> properties = new TreeMap<String, Object>();

  private final ErrorCode errorCode;

  private SystemException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }

  private SystemException(ErrorCode errorCode, Throwable cause) {
    super(cause);
    this.errorCode = errorCode;
  }

  /**
   * 根据错误码，创建一个SystemException.
   *
   * @param errorCode 错误码
   * @return SystemException
   */
  public static SystemException create(ErrorCode errorCode) {
    return new SystemException(errorCode);
  }

  /**
   * 将一个异常对象包装成SystemException.
   *
   * @param errorCode 错误码
   * @param throwable 异常对象
   * @return SystemException
   */
  public static SystemException wrap(ErrorCode errorCode, Throwable throwable) {
    if (throwable instanceof SystemException) {
      SystemException se = (SystemException) throwable;
      if (errorCode != null && errorCode != se.getErrorCode()) {
        return new SystemException(errorCode, throwable);
      }
      return se;
    } else {
      return new SystemException(errorCode, throwable);
    }
  }

  public Map<String, Object> asMap() {
    Map<String, Object> map = new HashMap<>();
    map.putAll(properties);
    map.putAll(this.errorCode.asMap());
    return map;
  }

  /**
   * 增加额外属性.
   *
   * @param name 属性名，不允许为null
   * @param value 属性值，不允许为null
   * @return 返回对象本身，这样可以使用链式操作<code>set("foo", "bar").set("name", "Edgar)</code>
   */
  public SystemException set(String name, Object value) {
    properties.put(name, value);
    return this;
  }

  /**
   * 根据属性名获取额外属性.
   *
   * @param name 属性名，不允许为null
   * @param <T> 泛型，方法内部直接返回需要的类型，避免在调用代码中再次使用<code>(String) get("foo")</code>来做类型转换
   * @return 属性值
   */
  @SuppressWarnings("unchecked")
  public <T> T get(String name) {
    return (T) properties.get(name);
  }

  @Override
  public String getMessage() {
    StringBuilder builder = new StringBuilder(super.getMessage());
    builder.append("\n\t--------------------------------------------------------------");
    if (errorCode != null) {
      builder.append("\n\t| " + errorCode);
    }
    for (String key : properties.keySet()) {
      builder.append("\n\t| " + key + "=[" + properties.get(key) + "]");
    }
    builder.append("\n\t--------------------------------------------------------------");
    return builder.toString();
  }

  /**
   * 返回错误码.
   *
   * @return 错误码
   */
  public ErrorCode getErrorCode() {
    return errorCode;
  }

  /**
   * 返回保存额外属性的Map对象，<b>该方法返回的是一个不可变对象.</b> 如果map为空，直接返回nul，该方法会返回null，这样是避免jaskson序列化该对象的时候忽略值为null的属性
   *
   * @return 返回一个不可变Map对象
   */
  public Map<String, Object> getProperties() {
    return properties;
  }

  /**
   * 增加details的属性.
   *
   * @param details 细节描述
   * @return 返回对象本身，这样可以使用链式操作<code>set("foo", "bar").set("name", "Edgar)</code>
   */
  public SystemException setDetails(String details) {
    properties.put("details", details);
    return this;
  }

  /**
   * 增加额外属性
   *
   * @param properties 属性的map
   */
  public SystemException setAll(Map<String, Object> properties) {
    if (properties != null) {
      properties.putAll(properties);
    }
    return this;
  }

}