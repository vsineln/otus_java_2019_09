package ru.otus.decorator;

/**
 * Реализовать калькулятор (Сложение, умножение, вычитание) с помощью паттерна декоратор
 * MultiplyArithmeticDecorator multiplyArithmeticDecorator = new MultiplyArithmeticDecorator(
 *  new AddArithmeticDecorator(...)
 * );
 * multiplyArithmeticDecorator.calculate();
 */
public class DecoratorDemo {
  public static void main(String[] args) {
    DataSource ds = new DataSourceImpl();
    printer(ds);

    printer(new DataSourceDecoratorAdder(ds));
    printer(new DataSourceDecoratorMultiplicator(ds));
    printer(new DataSourceDecoratorAdder(new DataSourceDecoratorMultiplicator(ds)));

  }

  private static void printer(DataSource ds) {
    System.out.println(ds.getInteger());
  }
}
