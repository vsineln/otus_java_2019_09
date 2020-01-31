package ru.otus.l6.annotations;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.experimental.NonFinal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PersonTest {

  @NonFinal
  Person person;

  @BeforeEach
  void setUp() {
    if (person == null)
      //    person = new Person("Вася", 18, List.of("222-33-22", "kjhgsdfgf@jklnsdg.ru"));
      person = Person.builder()
                   .name("Вася")
                   .age(18)
                   //.contacts(List.of("222-33-22", "kjhgsdfgf@jklnsdg.ru"))
                   .contact("222-33-22")
                   .contact("kjhgsdfgf@jklnsdg.ru")
                   .build();
  }

  @Test
  @DisplayName("SetName method works correctly")
  void setNameTest() {
    assertThat(person
                   .setName("Федя")
                   .getName())
        .isEqualTo("Федя");
  }

  @Test
  @DisplayName("GetAge method works correctly")
  void getAgeTest() {
    assertThat(person.getAge())
        .isEqualTo(18);
  }

}
