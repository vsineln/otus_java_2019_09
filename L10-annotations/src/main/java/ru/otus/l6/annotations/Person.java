package ru.otus.l6.annotations;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Exclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;
import lombok.With;
import lombok.experimental.FieldDefaults;

//@Getter
//@Setter
//@ToString
//@EqualsAndHashCode
@Data
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class Person {

  String name;
  @Exclude
  int age;

  @Singular
  List<String> contacts;
}
