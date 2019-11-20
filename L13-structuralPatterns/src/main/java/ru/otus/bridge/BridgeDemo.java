package ru.otus.bridge;

/**
 * 1) Добавить платежную систему MIR
 * 2) Добавить бонусные карты
 */
public class BridgeDemo {
  public static void main(String[] args) {
    Card card1 = new CreditCard(new VisaPS());
    card1.info();

    Card card2 = new DebitCard(new MastercardPS());
    card2.info();
  }
}
