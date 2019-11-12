package ru.otus.l6.lombok;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.With;
import lombok.experimental.FieldDefaults;
import lombok.val;

@Data
@With
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(force = true)
@FieldDefaults(level = PRIVATE)
public class Person {

  @Default
  final int age = 100;
  String firstName;
  String lastName;

  @Singular
  List<String> contacts;

  public static void main(String... __) {
    System.out.println(new Person(18, "jhgsdfg", ",kjsef", List.of("222-33-22", "kjhdfg@kjhdfg.ru"))
                           .getFirstName());//"drf"
    Person person = new Person();
    System.out.println(person);
    System.out.println(new Person().firstName);

    int x;
    int y;
    int z;

    //    final var a = 55;
    val a = 55;

    //....

    x = 67;

    Person vasyaPupkin = Person.builder()
                             .age(17)
                             .firstName("Вася")
                             .lastName("Пупкин")
                             //.contacts(List.of("222-33-22", "kjhdfg@kjhdfg.ru"))
                             .contact("222-33-22")
                             .contact("kjhdfg@kjhdfg.ru")
                             .build();

    Person petyaPupkin = vasyaPupkin
                             .toBuilder()
                             .firstName("Петя")
                             .build();

    System.out.println("petyaPupkin.getFirstName() = " + petyaPupkin.getFirstName());//Петя
    System.out.println("petyaPupkin.getLastName() = " + petyaPupkin.getLastName());
    System.out.println("petyaPupkin.getAge() = " + petyaPupkin.getAge()); //100

    Person fedorPupkin = petyaPupkin.withFirstName("Фёдор");
  }

  public void m1(Person this) {
    System.out.println("kjhgsdfg");
  }

}
