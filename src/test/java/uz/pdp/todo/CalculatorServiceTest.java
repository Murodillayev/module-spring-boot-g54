package uz.pdp.todo;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.util.stream.Stream;

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

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void add(double a) {
        double b = 12;
        double expect = a + b;
        Double result = calculatorService.add(a, b);
        assertEquals(expect, result);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "a ,   b,   ex",
            "1,    2,   3",
            "5,    5,   10",
            "-1,   1,   0",
            "0,    0,   0",
            "3.5,  1.5, 5.0"
    }, useHeadersInDisplayName = true)
    void addCsv(double a, double b, double ex) {
        Double result = calculatorService.add(a, b);
        assertEquals(ex, result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/nums.csv", useHeadersInDisplayName = true)
    void addCsvFile(double a, double b, double ex) {
        Double result = calculatorService.add(a, b);
        assertEquals(ex, result);
    }


    @ParameterizedTest(name = "{0} + {1} => {2} ")
    @MethodSource(value = "values")
    void addMethodSource(double a, double b, double ex) {
        Double result = calculatorService.add(a, b);
        assertEquals(ex, result);
    }

    @ParameterizedTest
    @ArgumentsSource(value = TestAddCasesProvider.class)
    void addArgumentsSource(double a, double b, double ex) {
        Double result = calculatorService.add(a, b);
        assertEquals(ex, result);
    }

    @Test
    void should_happen_error_when_b_zero() {
        double a = 1.1;
        double b = 0;

        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> calculatorService.divide(a, b));
        assertEquals("b 0 bo'lmasligi kerak", exception.getMessage());
        System.out.println("should_happen_error_when_b_zero ishladi");
    }

    @Test
    public void should_done_between_0_and_1_second_divide() {
        assertTimeout(Duration.ofMillis(100), () -> {
            calculatorService.divide(0.0, 1.0);
        });
    }


    static Stream<Arguments> values() {

        return Stream.of(
                Arguments.of(1.0, 2.0, 3.0),
                Arguments.of(5.5, 4.5, 10.0),
                Arguments.of(-1.0, 1.0, 0.0),
                Arguments.of(0.0, 0.0, 0.0),
                Arguments.of(3.14, 0.86, 4.0),
                Arguments.of(-2.5, -3.5, -6.0)
        );
    }

}