package com.edgar.util.event;

/**
 * 消息头字段定义了消息的ID，发送信道，接收信道，组信道，消息活动等各种信息。消息头字段是消息的关键内容.
 *
 * @author Edgar  Date 2017/3/8
 */
public interface EventHead {

  /**
   * @return 消息ID
   */
  String id();

  /**
   * @return 消息发送者私有信道
   */
  String from();

  /**
   * @return 消息接收者信道
   */
  String to();

  /**
   * @return 消息发送者组信道
   */
  String group();

  /**
   * @return 消息生成时间
   */
  long timestamp();

  /**
   * @return 分片消息序列号（可选）分片中第一条序列号为0，最后一条为-1
   */
  int sequence();

  /**
   * @return 消息活动，用于区分不同的消息类型
   */
  String action();

  /**
   * 创建EventHead对象
   * @param from 消息发送者私有信道
   * @param to 消息接收者信道
   * @param group 消息发送者组信道
   * @param action 消息活动
   * @return
   */
  static  EventHead create(String from, String to, String group, String action) {
    return new EventHeadImpl(from, to, group, action);
  }
}
