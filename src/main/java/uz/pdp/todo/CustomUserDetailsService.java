package uz.pdp.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthUserRepository authUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AuthUser authUser = authUserRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(username)
        );

        return CustomUserDetails.builder()
                .password(authUser.getPassword())
                .role(authUser.getRole())
                .userId(authUser.getId())
                .blocked(authUser.getBlocked())
                .username(authUser.getUsername())
                .build();
    }
}
