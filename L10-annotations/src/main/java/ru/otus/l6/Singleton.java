package ru.otus.l6;

public class Singleton {
  private static Singleton INSTANCE;
  int x;
  String y;

  Singleton(int x, String y) {
    this.x = x;
    this.y = y;
  }

  public static Singleton getInstance() {
    if (INSTANCE == null)
      synchronized (Singleton.class) {
        if (INSTANCE == null)
          INSTANCE = new Singleton(1, "dfgfdg");
      }
    return INSTANCE;
  }
}
