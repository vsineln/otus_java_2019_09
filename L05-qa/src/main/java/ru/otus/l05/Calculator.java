package ru.otus.l05;

import java.util.concurrent.TimeUnit;

public class Calculator {

  public int add(int x, int y) {
    return x + y;
  }

  /*
   *
   * 1) Деление выполняется корректно
   * 2) При делении на ноль генерируется исключение IllegalArgumentException
   * 3) Делить можно только положительные числа, иначе генерируется исключение IllegalArgumentException
   * 4) Результат деления всегда целое число, иначе генерируется исключение IllegalArgumentException
   * ( 4 % 2 == 0)
   *
   * */

  public double div(double x, double y) {
    return 0;
  }

  public void longCalculation() throws InterruptedException {
    Thread.sleep(TimeUnit.SECONDS.toMillis(10));
  }

  /*
   * Задание для TDD:
   * Реализовать функцию сложения двух положительных чисел.
   * Функция должна:
   * 1. Принимать в качестве аргументов только положительные числа.
   * 2. Возвращать сумму переданных чисел.
   */
  public int addPositive(int x, int y) {
    return 0;
  }
}
