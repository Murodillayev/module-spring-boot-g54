package uz.pdp.todo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TestController {

    private final AuthService authService;

    public TestController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public void test() {
        for (int i = 0; i < 1000; i++) {
            authService.register("User data");
        }
    }
}
