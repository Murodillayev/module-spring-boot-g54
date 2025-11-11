package uz.pdp.todo.validator;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.todo.model.dto.database.ProjectDatabaseCreateDto;
import uz.pdp.todo.model.entity.ProjectDatabase;
import uz.pdp.todo.repository.ProjectDatabaseRepository;

@Component
@AllArgsConstructor
public class ProjectDatabaseValidator implements BaseValidator {
    private final ProjectDatabaseRepository repository;

    public ProjectDatabase validateId(String id) {
        return repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Project database not found!"));
    }

    public void validateOnCreate(ProjectDatabaseCreateDto createDto) {

    }
}
