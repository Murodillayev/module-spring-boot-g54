package uz.pdp.todo.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtils {

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor("bu_secret_key_uzunligi_kamida_32_byte_bolishi_zarur".getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String generateAccessToken(String subject, Map<String, Object> claims) {
        SecretKey secret = Keys.hmacShaKeyFor("bu_secret_key_uzunligi_kamida_32_byte_bolishi_zarur".getBytes(StandardCharsets.UTF_8));
        Date exp = new Date(System.currentTimeMillis() + 1000 * 60 * 3);
        return Jwts.builder()
                .subject(subject)
                .expiration(exp)
                .signWith(secret)
                .issuedAt(new Date())
                .claims(claims)
                .compact();
    }
}
