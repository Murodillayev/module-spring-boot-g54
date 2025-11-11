package uz.pdp.todo.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.todo.model.dto.database.ProjectDatabaseCreateDto;
import uz.pdp.todo.model.dto.database.ProjectDatabaseDto;
import uz.pdp.todo.model.dto.database.ProjectDatabaseUpdateDto;
import uz.pdp.todo.model.entity.ProjectDatabase;
import uz.pdp.todo.repository.ProjectDatabaseRepository;
import uz.pdp.todo.repository.ProjectDatabaseUserRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class ProjectDatabaseMapper implements BaseMapper {
    private final ProjectDatabaseRepository repository;
    private final ProjectDatabaseUserMapper databaseUserMapper;
    private final ProjectAgentRepository projectAgentRepository;
    private final ProjectDatabaseUserRepository projectDatabaseUserRepository;

    public ProjectDatabase toEntityFromCreate(ProjectDatabaseCreateDto createDto) {
        ProjectDatabase projectDatabase = new ProjectDatabase();
        projectDatabase.setName(createDto.getName());
        projectDatabase.setDescription(createDto.getDescription());
        projectDatabase.setAgent(projectAgentRepository.findById(createDto.getAgentId()));
        return projectDatabase;
    }

    public ProjectDatabaseDto toDto(ProjectDatabase save) {
        ProjectDatabaseDto projectDatabaseDto = new ProjectDatabaseDto();
        projectDatabaseDto.setId(save.getId());
        projectDatabaseDto.setName(save.getName());
        projectDatabaseDto.setDescription(save.getDescription());
//        projectDatabaseDto.setAgent();
        projectDatabaseDto.setMembers(databaseUserMapper.mapToDtoList(save.getMembers()));
        return projectDatabaseDto;
    }

    public void mapUpdate(ProjectDatabase projectDatabase, ProjectDatabaseUpdateDto dto) {
        projectDatabase.setName(dto.getName());
        projectDatabase.setDescription(dto.getDescription());
        projectDatabase.setAgent(projectAgentRepository.findById(dto.getAgentId()));
        projectDatabase.setMembers(projectDatabaseUserRepository.findAllByIdIn(dto.getMembersId()));
    }

    public List<ProjectDatabaseDto> toDtoList() {
        return repository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }
}
