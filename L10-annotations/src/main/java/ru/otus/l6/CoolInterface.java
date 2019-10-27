package ru.otus.l6;

public interface CoolInterface {
  T coolMethod(boolean param);

  int getX();
  //...
}

interface T {
  int getInt();
  /*...*/
}

interface U {/*...*/
}

class MyBusinessClass {
//  CoolInterface ci;
  int x;

  public MyBusinessClass(int x) {
    this.x = x;
  }

//  public CoolInterface getCi() {
//    return ci;
//  }

//  int myBusinessMethod(int money) {
//    return ci.coolMethod(money >= 100)
//               .getInt();
//  }
}

class Cool2BusinessAdapter {
  MyBusinessClass fromBusiness(CoolInterface ci) {
    return new MyBusinessClass(ci.getX());
  }
}
