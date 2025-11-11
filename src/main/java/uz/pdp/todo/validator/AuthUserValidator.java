package uz.pdp.todo.validator;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.todo.model.dto.AuthUserCreateDto;
import uz.pdp.todo.model.entity.AuthUser;
import uz.pdp.todo.repository.AuthUserRepository;

@Component
@RequiredArgsConstructor
public class AuthUserValidator implements BaseValidator {
    private final AuthUserRepository repository;

    public void validateOnCreate(AuthUserCreateDto dto) {
        //validate logic
    }

    public AuthUser existsAndGet(String id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("User with id " + id + " not found")
        );
    }
}
