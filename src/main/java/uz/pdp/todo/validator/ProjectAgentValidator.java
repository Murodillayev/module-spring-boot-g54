package uz.pdp.todo.validator;

import org.springframework.stereotype.Component;
import uz.pdp.todo.model.dto.agent.ProjectAgentCreateDTO;
import uz.pdp.todo.model.entity.ProjectAgent;
import uz.pdp.todo.model.entity.ProjectDatabase;
import uz.pdp.todo.repository.ProjectAgentRepository;
import uz.pdp.todo.repository.ProjectDatabaseRepository;

import java.util.Optional;

@Component
public record ProjectAgentValidator(
        ProjectAgentRepository repository,
        ProjectDatabaseRepository databaseRepository
) implements BaseValidator{

    public void validateOnCreate(ProjectAgentCreateDTO dto) {
        //validate logic
    }

    public ProjectAgent existsAndGet(String id) {
        return repository.findByIdAndDeleted(id,false).orElseThrow(
                () -> new RuntimeException("Project agent with id " + id + " not found")
        );
    }

    public Optional<String> checkIfAlreadyExist(ProjectAgentCreateDTO dto) {
        Optional<ProjectDatabase> dbByName = databaseRepository.findDbByName(dto.getName());
        return dbByName.map(projectDatabase -> projectDatabase.getAgent().getId());
    }
}
