package com.github.edgar615.util.reflect;

public interface Person {

  String getName();

  void setName(String name);

  int getAge();

  void setAge(int age);

  default String display() {
    return String.format("%s (%s)", getName(), getAge());
  }
}