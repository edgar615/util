package com.edgar.util.event;

import java.util.Map;

/**
 * 请求回应为请求消息的回应.
 *
 * @author Edgar  Date 2017/3/8
 */
public interface Response extends EventAction {
  String TYPE = "response";

  /**
   *
   * @return 0 成功非0失败
   */
  int result();

  static Response create(String resource, int result, Map<String, Object> content) {
    return new ResponseImpl(resource, result, content);
  }
}
