package uz.pdp.todo.service;

import org.springframework.stereotype.Component;
import uz.pdp.todo.mapper.ProjectDatabaseUserMapper;
import uz.pdp.todo.model.dto.databaseUser.ProjectDatabaseUserCreateDto;
import uz.pdp.todo.model.dto.databaseUser.ProjectDatabaseUserDto;
import uz.pdp.todo.model.dto.databaseUser.ProjectDatabaseUserUpdateDto;
import uz.pdp.todo.model.entity.ProjectDatabase;
import uz.pdp.todo.model.entity.ProjectDatabaseUser;
import uz.pdp.todo.repository.ProjectDatabaseRepository;
import uz.pdp.todo.repository.ProjectDatabaseUserRepository;
import uz.pdp.todo.validator.ProjectDatabaseUserValidator;

import java.util.List;

@Component
public class ProjectDatabaseUserService extends AbstractService<
        ProjectDatabaseUserRepository,
        ProjectDatabaseUserMapper,
        ProjectDatabaseUserValidator> implements CRUDService<ProjectDatabaseUserDto, ProjectDatabaseUserCreateDto, ProjectDatabaseUserUpdateDto, String> {

    private final ProjectDatabaseRepository projectDatabaseRepository;

    public ProjectDatabaseUserService(ProjectDatabaseUserRepository repository, ProjectDatabaseUserMapper mapper, ProjectDatabaseUserValidator validator, ProjectDatabaseRepository projectDatabaseRepository) {
        super(repository, mapper, validator);
        this.projectDatabaseRepository = projectDatabaseRepository;
    }


    @Override
    public ProjectDatabaseUserDto create(ProjectDatabaseUserCreateDto createDto) {
        validator.validateOnCreate(createDto);
        var databaseUser = mapper.mapToEntityOnCreate(createDto);
        repository.save(databaseUser);
        ProjectDatabase database = databaseUser.getDatabase();
        database.getMembers().add(databaseUser);
        projectDatabaseRepository.save(database);
        return mapper.toDto(databaseUser);
    }

    @Override
    public ProjectDatabaseUserDto update(String id, ProjectDatabaseUserUpdateDto dto) {
        ProjectDatabaseUser projectDatabaseUser = validator.validateId(id);
        mapper.mapUpdate(projectDatabaseUser, dto);
        repository.save(projectDatabaseUser);
        return mapper.toDto(projectDatabaseUser);
    }

    @Override
    public ProjectDatabaseUserDto get(String id) {
        ProjectDatabaseUser projectDatabaseUser = validator.validateId(id);
        return mapper.toDto(projectDatabaseUser);
    }

    @Override
    public List<ProjectDatabaseUserDto> getAll() {
        return mapper.toListDto();
    }

    @Override
    public void delete(String id) {
        ProjectDatabaseUser projectDatabaseUser = validator.validateId(id);
        projectDatabaseUser.setDeleted(true);
        repository.save(projectDatabaseUser);
    }
}
