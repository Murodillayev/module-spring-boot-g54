package uz.pdp.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.todo.model.dto.LoginRequest;
import uz.pdp.todo.model.dto.LoginResponse;
import uz.pdp.todo.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;


    @GetMapping("/login")
    public ResponseEntity<LoginResponse> login(LoginRequest request) {
        LoginResponse response = service.login(request.getUsername(), request.getPassword());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/refresh-token")
    public ResponseEntity<LoginResponse> refreshToken(@RequestParam String refreshToken) {
        LoginResponse response = service.refreshToken(refreshToken);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
