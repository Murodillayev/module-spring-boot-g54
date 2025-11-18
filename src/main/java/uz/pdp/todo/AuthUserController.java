package uz.pdp.todo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.todo.service.AuthUserService;

@RestController
@RequestMapping("/user")
public class AuthUserController {


    private final AuthUserService authUserService;

    public AuthUserController(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @PostMapping
    public ResponseEntity<AuthUser> findByUsername(@RequestBody AuthUserCreateDto dto) {
        return ResponseEntity.ok(authUserService.create(dto));
    }
}
