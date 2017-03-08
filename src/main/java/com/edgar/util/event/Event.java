package com.edgar.util.event;

import com.google.common.base.Preconditions;

/**
 * Created by Edgar on 2017/3/8.
 *
 * @author Edgar  Date 2017/3/8
 */
public interface Event {

  /**
   * @return 消息头
   */
  EventHead head();

  /**
   * @return 消息活动
   */
  EventAction action();

  /**
   * 创建一个Event对象
   *
   * @param head   消息头
   * @param action 消息活动
   * @return Event对象
   */
  static Event create(EventHead head, EventAction action) {
    return new EventImpl(head, action);
  }

  /**
   * 创建一个Event对象
   *
   * @param from   消息发送者私有信道
   * @param to     消息接收者信道
   * @param group  消息发送者组信道
   * @param action 消息活动
   * @return Event对象
   */
  static Event create(String from, String to, String group, EventAction action) {
    Preconditions.checkNotNull(action, "action cannot be null");
    EventHead head = EventHead.create(from, to, group, action.name());
    return create(head, action);
  }
}
