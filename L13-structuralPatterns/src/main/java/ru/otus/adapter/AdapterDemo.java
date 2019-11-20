package ru.otus.adapter;

public class AdapterDemo {
  public static void main(String[] args) {
    new AdapterDemo().usePattern();
    new AdapterDemo().alternative();
  }

  public void usePattern() {
    RotaryHammer rotaryHammer = new RotaryHammer();
    Drill drill = new Drill();
    rotaryHammer.drill(new SDSadapter(drill));
  }

  public void alternative() {
    RotaryHammer rotaryHammer = new RotaryHammer();
    Drill drill = new Drill();
    rotaryHammer.drill(() -> System.out.println(drill));
  }
}
