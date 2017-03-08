package com.edgar.util.event;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;

import java.time.Instant;
import java.util.UUID;

/**
 * 消息头.
 *
 * @author Edgar  Date 2016/4/18
 */
class EventHeadImpl implements EventHead {
  /**
   * 消息接收者信道
   */
  private final String to;

  /**
   * 消息活动
   */
  private final String action;

  /**
   * 消息id
   */
  private final String id;

  /**
   * 消息产生时间戳
   */
  private final long timestamp;

  /**
   * 消息发送者私有信道
   */
  private String from;

  /**
   * 消息发送者组信道
   */
  private String group;

  /**
   * 分片消息序列号, 分片中第一条序列号为0，最后一条为-1
   */
  private Integer sequence;

  EventHeadImpl(String from, String to, String group, String action) {
    Preconditions.checkNotNull(from, "from can not be null");
    Preconditions.checkNotNull(to, "to can not be null");
    Preconditions.checkNotNull(action, "action cannot be null");
    this.from = from;
    this.to = to;
    this.group = group;
    this.action = action;
    this.sequence = 0;
    this.id = UUID.randomUUID().toString();
    this.timestamp = Instant.now().getEpochSecond();
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("header")
            .add("from", from)
            .add("to", to)
            .add("group", group)
            .add("action", action)
            .add("id", id)
            .add("timestamp", timestamp)
            .add("sequence", sequence)
            .toString();
  }

  @Override
  public String id() {
    return id;
  }

  @Override
  public String from() {
    return from;
  }

  @Override
  public String to() {
    return to;
  }

  @Override
  public String group() {
    return group;
  }

  @Override
  public long timestamp() {
    return timestamp;
  }

  @Override
  public int sequence() {
    return sequence;
  }

  @Override
  public String action() {
    return action;
  }
}
