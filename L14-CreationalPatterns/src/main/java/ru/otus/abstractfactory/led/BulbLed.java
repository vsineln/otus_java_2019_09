package ru.otus.abstractfactory.led;

import ru.otus.abstractfactory.Bulb;

/**
 * @author sergey
 * created on 18.09.18.
 */
public class BulbLed implements Bulb {
  @Override
  public void light() {
    System.out.println("Led light");
  }
}
