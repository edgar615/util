package com.github.edgar615.util.exception;

public class CustomErrorCode implements ErrorCode {

  private final int number;
  private final String message;
  private final int statusCode;

  public static CustomErrorCode create(int number, String message, int statusCode) {
    return new CustomErrorCode(number, message, statusCode);
  }

  CustomErrorCode(int number, String message, int statusCode) {
    this.number = number;
    this.message = message;
    this.statusCode = statusCode;
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
    return "CustomErrorCode{" +
           "number=" + number +
           ", message='" + message + '\'' +
           ", statusCode=" + statusCode +
           '}';
  }
}