package com.github.edgar615.util.exception;

public class CustomErrorCode implements ErrorCode {

  private final int number;
  private final String message;

  public static CustomErrorCode create(int number, String message) {
    return new CustomErrorCode(number, message);
  }

  CustomErrorCode(int number, String message) {
    this.number = number;
    this.message = message;
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
  public String toString() {
    return "CustomErrorCode{" +
           "number=" + number +
           ", message='" + message + '\'' +
           '}';
  }
}