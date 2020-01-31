package ru.otus.singleton;

/**
 * @author sergey
 * created on 19.09.18.
 */
class Singleton {
  private Singleton() {
    System.out.println("lazy creation");
  }

  private static class SingletonHolder {
    static final Singleton instance = new Singleton();
  }

  static Singleton getInstance() {
    return SingletonHolder.instance;
  }
}
