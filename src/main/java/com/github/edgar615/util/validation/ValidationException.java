package com.github.edgar615.util.validation;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * Created by Edgar on 2016/4/13.
 *
 * @author Edgar  Date 2016/4/13
 */
public class ValidationException extends RuntimeException {

  private final Multimap<String, String> errorDetail = ArrayListMultimap.create();

  public ValidationException() {
  }

  public ValidationException(String message) {
    super(message);
  }

  public ValidationException(Multimap<String, String> errorDetail) {
    super();
    this.errorDetail.putAll(errorDetail);
  }

  public ValidationException(String message, Multimap<String, String> errorDetail) {
    super(message);
    this.errorDetail.putAll(errorDetail);
  }

  /**
   * 增加校验异常值.
   *
   * @param name 属性名，不允许为null
   * @param value 属性值，不允许为null
   * @return 返回对象本身，这样可以使用链式操作<code>putError("foo", "bar").putError("name", "Edgar)</code>
   */
  public ValidationException putError(String name, String value) {
    errorDetail.put(name, value);
    return this;
  }

  public ValidationException putErrors(Multimap<String, String> errors) {
    errorDetail.putAll(errors);
    return this;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder("ValidationException");
    stringBuilder.append(getMessage());
    return stringBuilder.toString();

  }

  @Override
  public String getMessage() {
    StringBuilder stringBuilder = new StringBuilder();
    if (super.getMessage() != null) {
      stringBuilder.append(": " + super.getMessage());
    }
    if (!errorDetail.isEmpty()) {
      stringBuilder.append("\nDetails: ");
      stringBuilder.append("\n--------------------------------------------------------------");
      errorDetail.asMap().forEach((key, value) -> {
        stringBuilder
            .append("\n")
            .append(key)
            .append(":")
            .append(value);

      });
      stringBuilder.append("\n--------------------------------------------------------------");
    }
    return stringBuilder.toString();
  }

  public Multimap<String, String> getErrorDetail() {
    return errorDetail;
  }

}
