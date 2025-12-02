package uz.pdp.todo;


import org.springframework.stereotype.Service;

@Service
public class CalculatorService {


    public Double add(Double a, Double b) {
        return a + b;
    }

    public Double subtract(Double a, Double b) {
        return a - b;
    }

    public Double multiply(Double a, Double b) {
        return a * b;
    }


    public Double divide(Double a, Double b) {
        if (b == 0) {
            throw new ArithmeticException("b 0 bo'lmasligi kerak");
        }
        try {
            Thread.sleep(88);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return a / b;
    }

}
