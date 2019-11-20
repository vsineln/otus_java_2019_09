package ru.otus.proxy;

/**
 * Добавить слой логирования (не изменяя текущие реализации интерфейса SecurityAccess)
 */
public class ProxyDemo {
  public static void main(String[] args) {
    SecurityAccess proxy = new SecurityAccessProxy(new SecurityAccessImpl());
    proxy.access();
  }
}
