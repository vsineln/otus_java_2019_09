package ru.otus.l6.reflection;

import lombok.experimental.NonFinal;

public class ReflectionExample {

  @NonFinal
  int x;

  public ReflectionExample(int x) {
    this.x = x;
  }

  private String m() {
    return "123";
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }
}
