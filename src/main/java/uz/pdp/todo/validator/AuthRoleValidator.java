package uz.pdp.todo.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.todo.model.entity.AuthRole;
import uz.pdp.todo.repository.AuthRoleRepository;

@Component
@RequiredArgsConstructor
public class AuthRoleValidator {

    private final AuthRoleRepository repository;
    public AuthRole existsAndGet(String roleId) {

        if (roleId == null) {
            return null;
            //todo buni olib tashlash kerak.Bu vaqtincha
        }
        return repository.findById(roleId).orElseThrow(
                () -> new RuntimeException("Role with id " + roleId + " not found")
        );
    }
}
