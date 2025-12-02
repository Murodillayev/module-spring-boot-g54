package uz.pdp.todo;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
class CalculatorServiceTest {

    private CalculatorService calculatorService;

    @BeforeEach
    void setUp() {
        calculatorService = new CalculatorService();
        System.out.println("Calculator Service is started");

    }

    @Test
    void add() {
        double a = 1.1;
        double b = 2.2;
        double expect = a + b;
        Double result = calculatorService.add(a, b);
        assertEquals(expect, result);
    }

    @Test
    void should_happen_error_when_b_zero() {
        double a = 1.1;
        double b = 0;
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> calculatorService.divide(a, b));
        assertEquals("b 0 bo'lmasligi kerak", exception.getMessage());
    }

    @Test
    @DisplayName(value = "maksimum 1 sekundda javob qaytarishi kere")
    public void should_done_between_0_and_1_second_divide() {
        assertTimeout(Duration.ofMillis(100), () -> {
            calculatorService.divide(0.0, 1.0);
        });
    }

    @AfterEach
    void afterAll() {
        System.out.println("Calculator Service is finished");
    }
}