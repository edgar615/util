package com.github.edgar615.util.log;

/**
 * Created by Edgar on 2017/7/7.
 *
 * @author Edgar  Date 2017/7/7
 */
@Deprecated
public class LogType {

  /**
   * Server Received 服务端收到请求.
   */
  public static final String SR = "SR";

  /**
   * Server Send，服务端处理完请求，向客户端返回.
   */
  public static final String SS = "SS";

  /**
   * Client Send，客户端向服务端（包括下游服务，DB等等）发起请求.
   */
  public static final String CS = "CS";

  /**
   * Client Received 客户端收到服务端的响应.
   */
  public static final String CR = "CR";

  /**
   * 本地产生的一些业务日志，比如：请求成功，请求失败.
   */
  public static final String LOG = "LOG";

  /**
   * Server Event send，向Eventbus发送事件，主要是对send请求的回应.
   */
  public static final String SES = "SES";

  /**
   * Server Event Received，从eventbus收到事件.
   */
  public static final String SER = "SER";
  /**
   * Client Event send，向Eventbus发送事件.
   */
  public static final String CES = "CES";

  /**
   * Client Event Received，从eventbus中接受事件，主要是对send请求的回应
   */
  public static final String CER = "CER";

  /**
   * Message send，向消息服务器发送事件.
   */
  public static final String MS = "MS";

  /**
   * Message Received，从消息服务器读取事件
   */
  public static final String MR = "MR";

  /**
   * 程序的起点，一般用在方法的开始位置
   */
  public static final String SP = "SP";

  /**
   * 程序的终点，一般用在方法的结束位置
   */
  public static final String EP = "EP";
}
