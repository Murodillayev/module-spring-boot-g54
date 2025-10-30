package uz.pdp.todo.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.todo.config.jwt.JwtUtils;
import uz.pdp.todo.model.entity.AuthUser;
import uz.pdp.todo.model.dto.AuthUserDto;
import uz.pdp.todo.model.dto.LoginResponse;
import uz.pdp.todo.model.dto.TokenDto;
import uz.pdp.todo.repository.AuthUserRepository;

import java.util.List;
import java.util.Map;

@Service
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final AuthUserRepository repository;
    private final JwtUtils jwtUtils;
    private final AuthUserRepository authUserRepository;

    public AuthService(PasswordEncoder passwordEncoder, AuthUserRepository repository, JwtUtils jwtUtils, AuthUserRepository authUserRepository) {
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
        this.jwtUtils = jwtUtils;
        this.authUserRepository = authUserRepository;
    }


    public LoginResponse login(String username, String password) {

        AuthUser authUser = authUserRepository.findByUsername(username).orElseThrow(
                () -> new BadCredentialsException("Invalid username or password")
        );

        if (!passwordEncoder.matches(password, authUser.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        Map<String, Object> stringObjectMap = jwtUtils.prepareClaims(authUser);
        TokenDto accessToken = jwtUtils.generateAccessToken(username, stringObjectMap);
        TokenDto refreshToken = jwtUtils.generateRefreshToken(username, null);

        return LoginResponse.builder()
                .token(accessToken.getToken())
                .expiry(accessToken.getExpiry())
                .refreshToken(refreshToken.getToken())
                .refreshExpiry(refreshToken.getExpiry())
                .build();
    }

    public List<AuthUserDto> getAll() {
        List<AuthUser> users = repository.findAll();

        return users.stream().map(u -> AuthUserDto.builder()
                .id(u.getId())
                .username(u.getUsername())
                .build()).toList();
    }

    public LoginResponse refreshToken(String refreshToken) {
        Claims claims = jwtUtils.extractClaims(refreshToken);
        String username = claims.getSubject();
        AuthUser authUser = authUserRepository.findByUsername(username).orElseThrow(
                () -> new BadCredentialsException("Invalid refresh token")
        );
        TokenDto access = jwtUtils.generateAccessToken(username, jwtUtils.prepareClaims(authUser));
        TokenDto refresh = jwtUtils.generateRefreshToken(username, null);
        return LoginResponse.builder()
                .token(access.getToken())
                .expiry(access.getExpiry())
                .refreshExpiry(refresh.getExpiry())
                .refreshToken(refresh.getToken())
                .build();
    }


}
