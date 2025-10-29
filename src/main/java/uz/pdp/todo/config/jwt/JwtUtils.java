package uz.pdp.todo.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import uz.pdp.todo.model.AuthUser;
import uz.pdp.todo.model.TokenDto;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
public class JwtUtils {

    @Value("${app-security.token.access-exp:1800}")
    private Long accessTokenExpiration;

    @Value("${app-security.token.refresh-exp:86400000}")
    private Long refreshTokenExpiration;

    @Value("${app-security.token.secret:bu_secret_key_uzunligi_kamida_32_byte_bolishi_zarur}")
    private String secretKey;

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public TokenDto generateAccessToken(String subject, Map<String, Object> claims) {
        Date exp = new Date(System.currentTimeMillis() + accessTokenExpiration);
        String token = Jwts.builder()
                .subject(subject)
                .expiration(exp)
                .signWith(getSecretKey())
                .issuedAt(new Date())
                .claims(claims)
                .compact();
        return TokenDto.builder()
                .expiry(exp)
                .token(token)
                .build();
    }

    public TokenDto generateRefreshToken(String subject, Map<String, Object> claims) {
        Date exp = new Date(System.currentTimeMillis() + refreshTokenExpiration);
        String token = Jwts.builder()
                .subject(subject)
                .expiration(exp)
                .signWith(getSecretKey())
                .issuedAt(new Date())
                .claims(claims)
                .compact();

        return TokenDto.builder()
                .expiry(exp)
                .token(token)
                .build();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public Map<String, Object> prepareClaims(AuthUser authUser) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", authUser.getId());
        claims.put("blocked", authUser.getBlocked());
        claims.put("role", authUser.getRole());
        return claims;
    }

}
