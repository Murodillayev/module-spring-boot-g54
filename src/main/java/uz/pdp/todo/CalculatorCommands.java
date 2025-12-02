package uz.pdp.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@RequiredArgsConstructor
public class CalculatorCommands {

    private final CalculatorService service;

    @ShellMethod("Bu qo'shadi")
    public Double add(Double a, Double b) {

        return service.add(a, b);
    }

    @ShellMethod(key = "s", value = "Bu ayradi")
    public Double subtract(
            @ShellOption(value = "one") Double a,
            @ShellOption(defaultValue = "10") Double b
    ) {
        return a - b;
    }

    @ShellMethod("Bu ko'paytiradi")
    public Double multiply(Double a, Double b) {
        return a * b;
    }

    @ShellMethod(value = "Bu bo'ladi")
    public Double divide(Double a, Double b) {
        return a / b;
    }


    @ShellMethodAvailability({"multiply", "divide", "s", "add"})
    Availability availability() {

        if (SecurityContext.getCurrentUser() != null) {
            return Availability.available();
        }
        return Availability.unavailable("Please login!");
    }
}


//