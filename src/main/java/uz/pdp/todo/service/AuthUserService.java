package uz.pdp.todo.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.todo.AppProps;
import uz.pdp.todo.AuthUser;
import uz.pdp.todo.AuthUserCreateDto;
import uz.pdp.todo.AuthUserRepository;
import uz.pdp.todo.events.UserCreateEvent;

import java.util.Random;

@Service
public class AuthUserService {
    private final AuthUserRepository authUserRepository;
    private final ApplicationEventPublisher publisher;

    public AuthUserService(AuthUserRepository authUserRepository, ApplicationEventPublisher publisher) {
        this.authUserRepository = authUserRepository;

        this.publisher = publisher;
    }

//    @Transactional
    public AuthUser create(AuthUserCreateDto dto) {
        AuthUser authUser = AuthUser.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .fullName(dto.getFullName())
                .username(dto.getUsername())
                .build();
        AuthUser save = authUserRepository.save(authUser);
        UserCreateEvent event = new UserCreateEvent(this, save);
        publisher.publishEvent(event);

        if (dto.getPassword().equals("123")) {
            throw new RuntimeException("Error sodir boldi");
        }
        return save;
    }
}
