package uz.pdp.todo.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.todo.model.dto.AuthRoleChangeDto;
import uz.pdp.todo.model.entity.AuthRole;
import uz.pdp.todo.repository.AuthRoleRepository;

@Component
@RequiredArgsConstructor
public class AuthRoleValidator implements BaseValidator {

    private final AuthRoleRepository repository;
    public AuthRole existsAndGet(String roleId) {
        return repository.findById(roleId).orElseThrow(
                () -> new RuntimeException("Role with id " + roleId + " not found")
        );
    }

    public void validateOnCreate(AuthRoleChangeDto dto) {
        if (dto.getName() == null||dto.getName().isBlank()) {
            throw new RuntimeException("Name is required");
        }
        if (dto.getCode() == null||dto.getCode().isBlank()) {
            throw new RuntimeException("Code is required");
        }
    }
}
