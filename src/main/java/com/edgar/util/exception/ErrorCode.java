package com.edgar.util.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * 异常编码.
 *
 * @author Edgar
 * @version 1.0
 */
public interface ErrorCode {

  /**
   * 返回异常的编码.
   *
   * @return 编码值
   */
  int getNumber();

  /**
   * 返回异常的描述.
   *
   * @return 描述
   */
  String getMessage();

  /**
   * HTTP response应该返回的错误码.
   *
   * @return http response code
   */
  int getStatusCode();

  default Map<String, Object> asMap() {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("message", getMessage());
    map.put("code", getNumber());
    return map;
  }
}