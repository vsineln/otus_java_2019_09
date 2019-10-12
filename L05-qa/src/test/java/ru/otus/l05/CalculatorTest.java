package ru.otus.l05;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;

@DisplayName("DEMO test Class C")
class CalculatorTest {

    private Calculator calculator;

    @BeforeAll
    static void beforeClass() {
        System.out.println("before class");
    }

    @AfterAll
    static void afterClass() {
        System.out.println("after class");
    }

    private static Object[][] dividerDataProvider() {
        return new Object[][]{
                {4, 2, 2.0},
                {100, 10, 10.0},
                {40, 5, 8.0},
                {3, 1, 3.0},
                {50, 5, 10.0}
        };
    }

    @BeforeEach
    void init() {
        calculator = new Calculator();
        System.out.println("before");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("test method add")
    void add() {
        int x = 2;
        int y = 3;
        int result = new Calculator().add(x, y);
        assertEquals(x + y, result, "add result");
        assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException();
        });
    }

    @Disabled("Test is ignored as a demonstration")
    @Test
    @DisplayName("test method void")
    void testVoid() {

    }

    @Test
    @Disabled
    void testLongCalculation() {
        assertTimeout(ofSeconds(5), () -> new Calculator().longCalculation());
    }

    @Disabled
    @ParameterizedTest
    @MethodSource("dividerDataProvider")
    void test(double param1, double param2, double result) {
        assertEquals(result, new Calculator().div(param1, param2), 0.1, "параметрический тест:");
    }

    @Test
    public void testDivSuccess() {
        assertEquals(2, calculator.div(4, 2));
    }

    @Test
    public void testDivThrowIllegalArgumentExceptionWhenDivideByZero() {
        assertThrows(IllegalArgumentException.class, () -> calculator.div(4, 0));
    }

    @Test
    public void testDivOnlyPositiveNumber() {
        assertThrows(IllegalArgumentException.class, () -> calculator.div(4, -2));
    }

    @Test
    public void testDivResultOnlySimpleNumber() {
        assertThrows(IllegalArgumentException.class, () -> calculator.div(4, 3));
    }

}
