package ru.otus.l6.lombok;

import java.io.FileInputStream;
import java.io.InputStream;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Utils {

  //  private Utils() {
  //    throw new UnsupportedOperationException();
  //  }

  int f = 59;

  void m() {
    //...
  }

  @SneakyThrows
  public void main(String... __) {
    m();
    System.out.println(f);

    @Cleanup InputStream inputStream = new FileInputStream("dfg");
    //    inputStream.read();
  }
}
