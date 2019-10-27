package ru.otus.l6.demo;


public class Person implements IPerson {

  @Length
  private String name;

  //    @Override
  public String getNameс() {
    return name;
  }

  @Override
  public String getNamec() {
    return null;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }
}
