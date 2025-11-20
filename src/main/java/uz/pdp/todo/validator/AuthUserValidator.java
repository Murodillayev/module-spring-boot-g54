package uz.pdp.todo.validator;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.todo.model.dto.authUser.AuthUserCreateDto;
import uz.pdp.todo.model.entity.AuthUser;
import uz.pdp.todo.repository.AuthUserRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthUserValidator implements BaseValidator {
    private final AuthUserRepository repository;

    public void validateOnCreate(AuthUserCreateDto dto) {
        Optional<AuthUser> byUsername =
                repository.findByUsername(dto.getUsername());
        if (byUsername.isPresent()) {
            throw new RuntimeException("user with username: '%s' already exists".formatted(dto.getUsername()));
        }
        if (dto.getEmail()==null||dto.getEmail().isBlank()){
            throw new RuntimeException("Email is required");
        }
        if (dto.getName()==null||dto.getName().isBlank()){
            throw new RuntimeException("Name is required");
        }
        if (dto.getPassword()==null||dto.getPassword().isBlank()){
            throw new RuntimeException("Password is required");
        }
        if (dto.getUsername()==null||dto.getUsername().isBlank()){
            throw new RuntimeException("Username is required");
        }
    }

    public AuthUser existsAndGet(String id) {
        return repository.findByIdAndDeleted(id,false).orElseThrow(
                () -> new RuntimeException("User with id " + id + " not found")
        );
    }
}
