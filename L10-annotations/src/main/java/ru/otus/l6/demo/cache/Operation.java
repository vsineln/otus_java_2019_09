package ru.otus.l6.demo.cache;

import lombok.Builder;
import lombok.Data;
import lombok.With;
import lombok.experimental.Wither;

@Data
@Builder
@With
public class Operation {

  private final int a;
  private final int b;

  public int getA() {
    System.out.println("Getting a");
    return a;
  }
}
