package uz.pdp.todo.validator;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.todo.model.dto.database.ProjectDatabaseCreateDto;
import uz.pdp.todo.model.entity.ProjectDatabase;
import uz.pdp.todo.model.entity.ProjectDatabaseUser;
import uz.pdp.todo.repository.ProjectDatabaseRepository;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ProjectDatabaseValidator implements BaseValidator {
    private final ProjectDatabaseRepository repository;

    public ProjectDatabase validateId(String id) {
        return repository.findByIdAndDeleted(id,false)
                .orElseThrow(() -> new RuntimeException("Project database not found!"));
    }

    public void validateOnCreate(ProjectDatabaseCreateDto createDto) {
        if (createDto.getName() == null || createDto.getName().isBlank()) {
            throw new RuntimeException("Name is required");
        }
        if (createDto.getAgentId() == null || createDto.getAgentId().isBlank()) {
            throw new RuntimeException("Agent id cannot be empty");
        }
    }

    public void checkIfMemberAlreadyExists(ProjectDatabase database, String authUserId) {
        Optional<ProjectDatabaseUser> first = database.getMembers()
                .stream()
                .filter(m -> m.getAuthUser().getId().equals(authUserId))
                .findFirst();
        if (first.isPresent()) {
            throw new RuntimeException("user already exists in the database");
        }

    }
}
