package ru.otus.l6.annotations;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.experimental.NonFinal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PersonTest {

  @NonFinal
  Person person;

  @NonFinal
  Person person2;

  @BeforeEach
  void setUp(PersonTest jgsdf) {
    //    person = new Person("Вася", 18, List.of("222-33-22", "kjhgsdfgf@jklnsdg.ru"));
    this.person = Person.builder()
                 .name("Вася")
                 .age(18)
                 //.contacts(List.of("222-33-22", "kjhgsdfgf@jklnsdg.ru"))
                 .contact("222-33-22")
                 .contact("kjhgsdfgf@jklnsdg.ru")
                 .build();

    person2 = person.withName("Петя");
  }

  @Test
  @DisplayName("GetName method works correctly")
  void GetName() {
  }

  @Test
  @DisplayName("SetName method works correctly")
  void SetName() {
    assertThat(person
                   .setName("Федя")
                   .setAge(19)
                   .getName())
        .isEqualTo("Федя");
    //    person.name
  }

  @Test
  @DisplayName("GetAge method works correctly")
  void GetAge() {
    assertThat(person.getAge())
        .isEqualTo(18);
  }

  @Test
  @DisplayName("SetAge method works correctly")
  void SetAge() {
  }

  @Test
  @DisplayName("GetContacts method works correctly")
  void GetContacts() {
  }

  @Test
  @DisplayName("SetContacts method works correctly")
  void SetContacts() {
  }

  @Test
  @DisplayName("TestEquals method works correctly")
  void TestEquals() {
  }

  @Test
  @DisplayName("TestHashCode method works correctly")
  void TestHashCode() {
  }

  @Test
  @DisplayName("TestToString method works correctly")
  void TestToString() {
  }
}
