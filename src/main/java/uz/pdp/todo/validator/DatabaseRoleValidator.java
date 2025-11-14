package uz.pdp.todo.validator;

import org.springframework.stereotype.Component;
import uz.pdp.todo.model.dto.DatabaseRoleCreateDTO;
import uz.pdp.todo.model.entity.DatabaseRole;
import uz.pdp.todo.repository.DatabaseRoleRepository;

@Component
public record DatabaseRoleValidator(
        DatabaseRoleRepository repository
) implements BaseValidator{

    public void validateOnCreate(DatabaseRoleCreateDTO dto) {
        //validate logic
    }

    public DatabaseRole existsAndGet(String id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("Database role with id " + id + " not found")
        );
    }
}
