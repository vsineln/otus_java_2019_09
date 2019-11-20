package ru.otus.proxy;

public class SecurityAccessProxy implements SecurityAccess {

  private final SecurityAccess securityAccess;

  public SecurityAccessProxy(SecurityAccess securityAccess) {
    this.securityAccess = securityAccess;
  }

  @Override
  public void access() {
    System.out.println("before");
    securityAccess.access();
    System.out.println("after");
  }
}
