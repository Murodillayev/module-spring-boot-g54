package uz.pdp.todo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final AuthUserRepository repository;

    public AuthService(PasswordEncoder passwordEncoder, AuthUserRepository repository) {
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
    }

    public String login(String username, String password) {
        // find user by username
        AuthUser authUser = repository.findByUsername(username).orElseThrow(
                () -> new BadCredentialsException("Bad credentials")
        );

        // validate password
        if (!passwordEncoder.matches(password, authUser.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }

        // generate token
        SecretKey secret = Keys.hmacShaKeyFor("bu_secret_key_uzunligi_kamida_32_byte_bolishi_zarur".getBytes(StandardCharsets.UTF_8));
        Date exp = new Date(System.currentTimeMillis() + 1000 * 60 * 3);
        Map<String, String> claims = Map.of("name", "Muhammadkomil");
        return Jwts.builder()
                .subject(username)
                .expiration(exp)
                .signWith(secret)
                .issuedAt(new Date())
                .claims(claims)
                .compact();
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
