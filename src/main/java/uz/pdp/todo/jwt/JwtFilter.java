package uz.pdp.todo.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.webmvc.core.service.RequestService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.todo.AuthUser;
import uz.pdp.todo.AuthUserRepository;
import uz.pdp.todo.CustomUserDetails;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


// -> [ cF ->  Authentification => isAuthenticate()  ->  sf1 -> sf2 -> sf3 -> sf4 .... ] -> controller

// 1. create filter
// 2. BU filterni security authen ni tekshirish filteridan avval ishlashini taminlash -
// 3. Filter ichida tokenni olib, validate qilib, ichidan sub ni olib load from db
// 4. put to security contex holder (isAuth = true)
// 5. doFilterChain

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final AuthUserRepository authUserRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.replace("Bearer ", "");

            Claims claims = extractClaims(token);

            String username = claims.getSubject();
//            AuthenticationManager
            AuthUser authUser = authUserRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

            CustomUserDetails customUserDetails = CustomUserDetails.builder().userId(authUser.getId()).username(username).password(authUser.getPassword()).build();

            SecurityContext context = SecurityContextHolder.getContext();

            Authentication authentication = new UsernamePasswordAuthenticationToken(authUser, null, customUserDetails.getAuthorities());
            context.setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }


    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor("bu_secret_key_uzunligi_kamida_32_byte_bolishi_zarur".getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
