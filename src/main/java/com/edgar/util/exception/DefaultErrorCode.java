package com.edgar.util.exception;

/**
 * 异常码，实现了{@link ErrorCode}接口.
 *
 * @author Edgar
 * @version 1.0
 */
public enum DefaultErrorCode implements ErrorCode {

  /**
   * 空指针异常.
   */
  UNKOWN(999, "Unknown Error", 500),

  /**
   * 空指针异常.
   */
  NULL(1000, "Nullpointer Abnormal", 400),

  /**
   * 未知用户.
   */
  UNKOWN_ACCOUNT(1001, "Unkonwn User", 401),

  /**
   * 未登录用户.
   */
  UNKOWN_LOGIN(1002, "User Not Log In", 401),

  /**
   * 用户名或密码错误.
   */
  NAME_PWD_INCORRECT(1003, "Incorrect Username or Password", 400),

  /**
   * 权限不足.
   */
  PERMISSION_DENIED(1004, "Permission Denied", 403),

  /**
   * Token过期.
   */
  EXPIRE_TOKEN(1005, "Token Expired", 401),

  /**
   * 资源不存在，GET请求是数据不存在.
   */
  RESOURCE_NOT_FOUND(1006, "Resources Not Found", 404),

  /**
   * 相关对象不存在，请求依赖的某个对象已经被删除.
   */
  TARGET_NOT_FOUND(1007, "No Object Found", 400),

  /**
   * 参数不全.
   */
  MISSING_ARGS(1008, "Incomplete Parameter", 400),

  /**
   * 参数非法.
   */
  INVALID_ARGS(1009, "Invalid Parameter", 400),

  /**
   * 输入为空或字数不够.
   */
  INPUT_TOO_SHORT(1010, "Empty or Less Input", 400),

  /**
   * 输入字数太多.
   */
  INPUT_TOO_LONG(1011, "Over Input", 400),

  /**
   * 文件为空.
   */
  UPLOAD_FILE_EMPTY(1012, "Empty File", 400),

  /**
   * 上传文件失败.
   */
  UPLOAD_FILE_FAILED(1013, "Upload Failed", 400),

  /**
   * 资源、数据已存在.
   */
  ALREADY_EXISTS(1014, "Already exists", 400),

  /**
   * 超时.
   */
  TIME_OUT(1015, "Request Overtime", 400),

  /**
   * 未找到远程服务器.
   */
  SERVICE_UNAVAILABLE(1016, "Service Unavailable", 503),

  /**
   * 参数类型错误.
   */
  INVALID_TYPE(1017, "Wrong Parameter Format", 400),

  /**
   * SQL语句错误.
   */
  INVALID_SQL(1018, "SQL Error", 400),

  /**
   * 类型转换错误.
   */
  CAST_ERROR(1019, "Type Conversion Error", 400),

  /**
   * 服务调用异常.
   */
  SERVICE_EX(1020, "Service Exception", 400),

  /**
   * TOKEN失效.
   */
  INVALID_TOKEN(1021, "Token Invalid", 401),

  /**
   * 非法请求.
   */
  INVALID_REQ(1022, "Illegal Request", 400),

  /**
   * 请求过期.
   */
  EXPIRE(1023, "Request Expired", 400),

  /**
   * 非法的JSON.
   */
  INVALID_JSON(1024, "Problems Parsing JSON", 400),

  /**
   * 并发错误.
   */
  CONCURRENT_CONFLICT(1025, "Concurrent Conflict", 409),

  /**
   * 服务到期.
   */
  SERVICE_EXPIRED(1026, "Service Expired", 403),

  /**
   * 账户被锁定.
   */
  ACCOUNT_BLOCKED(1027, "Account Blocked", 403),

  /**
   * 缺少必要的头信息.
   */
  MISSING_HEADER(1028, "Incomplete Header", 428),

  /**
   * 过多的请求.
   */
  TOO_MANY_REQ(1029, "Too Many Requests", 429),

  /**
   * 断路器打开.
   */
  BREAKER_TRIPPED(1030, "Breaker Tripped", 429),

  /**
   * 事件超时.
   */
  EVENTBUS_TIMOUT(1101, "Eventbus: Timeout", 400),

  /**
   * 事件被拒绝.
   */
  EVENTBUS_REJECTED(1102, "Eventbus: Rejected", 400),

  /**
   * 未定义事件.
   */
  EVENTBUS_NO_HANDLERS(1103, "Eventbus: Undefined Event", 400);

  /**
   * 异常编码.
   */
  private final int number;

  /**
   * 异常描述.
   */
  private final String message;

  /**
   * HTTP response code.
   */
  private final int statusCode;

  DefaultErrorCode(int number, String message, int statusCode) {
    this.number = number;
    this.message = message;
    this.statusCode = statusCode;
  }

  /**
   * 根据code返回对应的枚举，如果未找到对应的枚举，返回null
   * @param code 异常编码
   * @return 异常码
   */
  public static DefaultErrorCode getCode(int code) {
    for (DefaultErrorCode errorCode : DefaultErrorCode.values()) {
      if (errorCode.getNumber() == code) {
        return errorCode;
      }
    }
    return null;
  }

  @Override
  public int getNumber() {
    return number;
  }

  @Override
  public String getMessage() {
    return message;
  }

  @Override
  public int getStatusCode() {
    return statusCode;
  }

  @Override
  public String toString() {
    return "DefaultErrorCode{"
           + "number=" + number
           + ", message='" + message + '\''
           + ", statusCode='" + statusCode + '\''
           + '}';
  }
}