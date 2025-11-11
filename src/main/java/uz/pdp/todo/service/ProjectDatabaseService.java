package uz.pdp.todo.service;

import org.springframework.stereotype.Service;
import uz.pdp.todo.mapper.ProjectDatabaseMapper;
import uz.pdp.todo.model.dto.database.ProjectDatabaseCreateDto;
import uz.pdp.todo.model.dto.database.ProjectDatabaseDto;
import uz.pdp.todo.model.dto.database.ProjectDatabaseUpdateDto;
import uz.pdp.todo.model.entity.ProjectDatabase;
import uz.pdp.todo.repository.ProjectDatabaseRepository;
import uz.pdp.todo.validator.ProjectDatabaseValidator;

import java.util.List;
@Service

public class ProjectDatabaseService extends AbstractService<
        ProjectDatabaseRepository,
        ProjectDatabaseMapper,
        ProjectDatabaseValidator> implements CRUDService<ProjectDatabaseDto, ProjectDatabaseCreateDto, ProjectDatabaseUpdateDto,String>{

    public ProjectDatabaseService(ProjectDatabaseRepository repository, ProjectDatabaseMapper mapper, ProjectDatabaseValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public ProjectDatabaseDto create(ProjectDatabaseCreateDto createDto) {
        validator.validateOnCreate(createDto);
        ProjectDatabase entity = mapper.toEntityFromCreate(createDto);
        ProjectDatabase save = repository.save(entity);
        return mapper.toDto(save);
    }

    @Override
    public ProjectDatabaseDto update(String id, ProjectDatabaseUpdateDto dto) {
        ProjectDatabase projectDatabase = validator.validateId(id);
        mapper.mapUpdate(projectDatabase,dto);
        return mapper.toDto(repository.save(projectDatabase));
    }

    @Override
    public ProjectDatabaseDto get(String id) {
        ProjectDatabase projectDatabase = validator.validateId(id);
        return mapper.toDto(projectDatabase);
    }

    @Override
    public List<ProjectDatabaseDto> getAll() {
        return mapper.toDtoList();
    }

    @Override
    public void delete(String id) {
        ProjectDatabase projectDatabase = validator.validateId(id);
        projectDatabase.setDeleted(true);
        repository.save(projectDatabase);
    }
}
