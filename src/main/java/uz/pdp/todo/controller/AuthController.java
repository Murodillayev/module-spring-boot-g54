package uz.pdp.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.todo.model.LoginResponse;
import uz.pdp.todo.service.AuthService;
import uz.pdp.todo.model.AuthUserDto;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @GetMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestParam String username, @RequestParam String password) {
        LoginResponse response = service.login(username, password);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/refresh-token")
    public ResponseEntity<LoginResponse> refreshToken(@RequestParam String refreshToken) {
        LoginResponse response = service.refreshToken(refreshToken);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AuthUserDto>> getAll() {
        List<AuthUserDto> users = service.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
