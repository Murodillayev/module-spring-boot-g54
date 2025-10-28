package uz.pdp.todo.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.todo.config.jwt.JwtUtils;
import uz.pdp.todo.model.AuthUser;
import uz.pdp.todo.model.AuthUserDto;
import uz.pdp.todo.repository.AuthUserRepository;

import java.util.List;
import java.util.Map;

@Service
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final AuthUserRepository repository;
    private final JwtUtils jwtUtils;

    private final AuthenticationManager authenticationManager;

    public AuthService(PasswordEncoder passwordEncoder, AuthUserRepository repository, JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    public String login(String username, String password) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        authenticationManager.authenticate(authentication);
        Map<String, Object> claims = Map.of("name", "Muhammadkomil");
        return jwtUtils.generateAccessToken(username, claims);
    }

    public List<AuthUserDto> getAll() {
        List<AuthUser> users = repository.findAll();

        return users.stream().map(u -> AuthUserDto.builder()
                .id(u.getId())
                .username(u.getUsername())
                .role(u.getRole())
                .blocked(u.getBlocked())
                .build()).toList();
    }
}
