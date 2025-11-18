package uz.pdp.todo.validator;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.todo.model.dto.databaseUser.ProjectDatabaseUserCreateDto;
import uz.pdp.todo.model.entity.ProjectDatabaseUser;
import uz.pdp.todo.repository.ProjectDatabaseUserRepository;

import java.util.Optional;

@Component
@AllArgsConstructor

public class ProjectDatabaseUserValidator implements BaseValidator {
    private final ProjectDatabaseUserRepository repository;

    public void validateOnCreate(ProjectDatabaseUserCreateDto createDto) {
        if (createDto.getDbUsername() == null || createDto.getDbUsername().isBlank()) {
            throw new RuntimeException("username is required");
        }
        if (createDto.getDbPassword() == null || createDto.getDbPassword().isBlank()) {
            throw new RuntimeException("password is required");
        }
        if (createDto.getDbUsername().trim().contains(" ")){
            throw new RuntimeException("username cannot contain spaces");
        }if (createDto.getDbPassword().trim().contains(" ")){
            throw new RuntimeException("password cannot contain spaces");
        }
    }

    public ProjectDatabaseUser validateId(String id) {
       return repository.findByIdAndDeleted(id,false)
                .orElseThrow(() -> new RuntimeException("member not found"));
    }
}
