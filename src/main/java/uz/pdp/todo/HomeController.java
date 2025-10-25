package uz.pdp.todo;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {
    @GetMapping
    public String home() {
        System.out.println(Utils.sessionUser());
        return "Hello World";
    }

    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    @GetMapping("/test")
    public String test(@AuthenticationPrincipal CustomUserDetails sessionUser) {
        System.out.println(sessionUser);
        return "Hello World";
    }

}
