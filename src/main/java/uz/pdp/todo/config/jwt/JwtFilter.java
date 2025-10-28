package uz.pdp.todo.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.todo.model.AuthUser;
import uz.pdp.todo.repository.AuthUserRepository;
import uz.pdp.todo.config.CustomUserDetails;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


//client ->  [   sf1 -> sf2  -> customfilter(Authentification->isAuth=true, contexHolder)
// -> upF (Authentification => isAuthenticate()=false) -> sf3 -> sf4 ....  -> isAuthenticate()=true ] -> controller

// 1. create filter
// 2. BU filterni security authen ni tekshirish filteridan avval ishlashini taminlash -
// 3. Filter ichida tokenni olib, validate qilib, ichidan sub ni olib load from db
// 4. put to security contex holder (isAuth = true)
// 5. doFilterChain

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final AuthUserRepository authUserRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.replace("Bearer ", "");

            Claims claims = jwtUtils.extractClaims(token);

            String username = claims.getSubject();

            AuthUser authUser = authUserRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

            CustomUserDetails customUserDetails = CustomUserDetails.builder().userId(authUser.getId()).username(username).password(authUser.getPassword()).build();

            Authentication authentication = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }


}
