package com.github.edgar615.util.reflect;

/**
 * 测试Bean.
 *
 * @author Edgar
 * @create 2018-09-06 12:46
 **/
public class SomeBean extends SomeSuperBean {

  private int id;

  private String name;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
