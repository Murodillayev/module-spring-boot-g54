package uz.pdp.todo.validator;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.todo.model.dto.databaseUser.ProjectDatabaseUserCreateDto;
import uz.pdp.todo.model.entity.ProjectDatabaseUser;
import uz.pdp.todo.repository.ProjectDatabaseUserRepository;

@Component
@AllArgsConstructor

public class ProjectDatabaseUserValidator implements  BaseValidator {
    private final ProjectDatabaseUserRepository repository;

    public void validateOnCreate(ProjectDatabaseUserCreateDto createDto) {

    }

    public ProjectDatabaseUser validateId(String id) {
        return repository.findById(id)
                .orElseThrow(()-> new RuntimeException("database user not found!"));
    }
}
