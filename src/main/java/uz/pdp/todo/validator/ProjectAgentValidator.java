package uz.pdp.todo.validator;

import org.springframework.stereotype.Component;
import uz.pdp.todo.model.dto.AuthUserCreateDto;
import uz.pdp.todo.model.dto.ProjectAgentCreateDTO;
import uz.pdp.todo.model.entity.AuthUser;
import uz.pdp.todo.model.entity.ProjectAgent;
import uz.pdp.todo.repository.AuthUserRepository;
import uz.pdp.todo.repository.ProjectAgentRepository;

@Component
public record ProjectAgentValidator(
        ProjectAgentRepository repository
) implements BaseValidator{

    public void validateOnCreate(ProjectAgentCreateDTO dto) {
        //validate logic
    }

    public ProjectAgent existsAndGet(String id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("Project agent with id " + id + " not found")
        );
    }
}
