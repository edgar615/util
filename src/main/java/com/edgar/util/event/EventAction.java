package com.edgar.util.event;

import java.util.Map;

/**
 * 消息活动，用于区分不同的消息类型.
 *
 * @author Edgar  Date 2017/3/8
 */
public interface EventAction {

  /**
   * Unknown action.
   */
  String UNKNOWN = "unknown";

  /**
   * @return action名称
   */
  String name();

  /**
   * @return 资源标识
   */
  String resource();

  /**
   * @return 请求参数
   */
  Map<String, Object> content();
}
