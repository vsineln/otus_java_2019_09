package ru.otus.proxy;

/**
 * Добавить слой логирования
 */
public class ProxyDemo {
  public static void main(String[] args) {
    SecurityAccess proxy = new SecurityAccessProxy(new SecurityAccessImpl());
    proxy.access();
  }
}
