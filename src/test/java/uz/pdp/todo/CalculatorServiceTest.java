package uz.pdp.todo;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(value = DisplayNameGenerator.IndicativeSentences.class)
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.DisplayName.class)
class CalculatorServiceTest {

    private CalculatorService calculatorService;


    @BeforeEach
    void setUp() {
        calculatorService = new CalculatorService();
        System.out.println("Calculator Service is started");

    }

    @Disabled
    @Test
    void add() {
        double a = 1.1;
        double b = 2.2;
        double expect = a + b;
        Double result = calculatorService.add(a, b);
        assertEquals(expect, result);
    }

    @RepeatedTest(value = 10)
//    @EnabledOnOs(value = {OS.WINDOWS, OS.MAC})
//    @EnabledOnJre(value = JRE.JAVA_8)
//    @EnabledForJreRange(min = JRE.JAVA_17, max = JRE.JAVA_18)
    void should_happen_error_when_b_zero() {
        double a = 1.1;
        double b = 0;
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> calculatorService.divide(a, b));
        assertEquals("b 0 bo'lmasligi kerak", exception.getMessage());
        System.out.println("should_happen_error_when_b_zero ishladi");
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