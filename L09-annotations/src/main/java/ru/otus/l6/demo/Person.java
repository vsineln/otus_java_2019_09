package ru.otus.l6.demo;


import lombok.experimental.NonFinal;

public class Person implements IPerson {

  @Length
  @NonFinal
  private String name;

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }
}
