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
  UNKOWN(999, "未知错误", 400),

  /**
   * 空指针异常.
   */
  NULL(1000, "空指针异常", 400),

  /**
   * 未知用户.
   */
  UNKOWN_ACCOUNT(1001, "未知用户", 403),

  /**
   * 未登录用户.
   */
  UNKOWN_LOGIN(1002, "未登录用户", 403),

  /**
   * 用户名或密码错误.
   */
  NAME_PWD_INCORRECT(1003, "用户名或密码错误", 400),

  /**
   * 权限不足.
   */
  NO_AUTHORITY(1004, "权限不足", 403),

  /**
   * Token过期.
   */
  EXPIRE_TOKEN(1005, "Token过期", 403),

  /**
   * 资源不存在，GET请求是数据不存在.
   */
  RESOURCE_NOT_FOUND(1006, "资源不存在", 404),

  /**
   * 相关对象不存在，请求依赖的某个对象已经被删除.
   */
  TARGET_NOT_FOUND(1007, "对象不存在", 400),

  /**
   * 参数不全.
   */
  MISSING_ARGS(1008, "参数不全", 400),

  /**
   * 参数非法.
   */
  INVALID_ARGS(1009, "参数非法", 400),

  /**
   * 输入为空或字数不够.
   */
  INPUT_TOO_SHORT(1010, "输入为空或字数不够", 400),

  /**
   * 输入字数太多.
   */
  INPUT_TOO_LONG(1011, "输入的字数过多", 400),

  /**
   * 文件为空.
   */
  UPLOAD_FILE_EMPTY(1012, "文件为空", 400),

  /**
   * 上传文件失败.
   */
  UPLOAD_FILE_FAILED(1013, "上传文件失败", 400),

  /**
   * 用户名重复.
   */
  USERNAME_DUPLICATE(1014, "用户名重复", 400),

  /**
   * 超时.
   */
  TIME_OUT(1015, "请求超时", 400),

  /**
   * 未找到远程服务器.
   */
  UNKOWN_REMOTE(1016, "未找到远程服务器", 400),

  /**
   * 参数类型错误.
   */
  INVALID_TYPE(1017, "参数类型错误", 400),

  /**
   * SQL语句错误.
   */
  INVALID_SQL(1018, "SQL语句错误", 400),

  /**
   * 类型转换错误.
   */
  CAST_ERROR(1019, "类型转换错误", 400),

  /**
   * 服务调用异常.
   */
  REMOTE_EX(1020, "服务调用异常", 400),

  /**
   * TOKEN失效.
   */
  INVALID_TOKEN(1021, "Token无效", 403),

  /**
   * 非法请求.
   */
  INVALID_REQ(1022, "非法请求", 400),

  /**
   * 请求过期.
   */
  EXPIRE(1023, "过期的请求", 400),

  /**
   * 非法的JSON.
   */
  INVALID_JSON(1024, "错误的JSON格式", 400),
  /**
   * 事件超时.
   */
  EVENTBUS_TIMOUT(1025, "事件超时", 400),

  /**
   * 事件被拒绝.
   */
  EVENTBUS_REJECTED(1026, "事件被拒绝", 400),

  /**
   * 未定义事件.
   */
  EVENTBUS_NO_HANDLERS(1027, "未定义事件", 400);

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