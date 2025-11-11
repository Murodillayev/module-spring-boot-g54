package uz.pdp.todo.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.todo.model.dto.databaseUser.ProjectDatabaseUserCreateDto;
import uz.pdp.todo.model.dto.databaseUser.ProjectDatabaseUserDto;
import uz.pdp.todo.model.dto.databaseUser.ProjectDatabaseUserUpdateDto;
import uz.pdp.todo.model.entity.ProjectDatabase;
import uz.pdp.todo.model.entity.ProjectDatabaseUser;
import uz.pdp.todo.repository.ProjectDatabaseRepository;
import uz.pdp.todo.repository.ProjectDatabaseUserRepository;
import uz.pdp.todo.service.VersionProviderService;

import java.util.List;

@Component
@AllArgsConstructor
public class ProjectDatabaseUserMapper implements BaseMapper {
    private final ProjectDatabaseUserRepository repository;
    private final ProjectDatabaseRepository projectDatabaseRepository;
    private final VersionProviderService  versionProviderService;


    public List<ProjectDatabaseUserDto> mapToDtoList(List<ProjectDatabaseUser> all) {
        return all
                .stream()
                .filter(dbU -> !dbU.getDeleted())
                .map(this::toDto)
                .toList();
    }
    public ProjectDatabaseUserDto toDto(ProjectDatabaseUser databaseUser) {
        ProjectDatabaseUserDto databaseUserDto = new ProjectDatabaseUserDto();
        databaseUserDto.setId(databaseUser.getId());
        databaseUserDto.setDbPassword(databaseUser.getPassword());
        databaseUserDto.setDbUsername(databaseUser.getUsername());
        databaseUserDto.setDatabaseId(databaseUser.getDatabase().getId());
        databaseUserDto.setRoles(databaseUser.getRoles());
        return databaseUserDto;
    }

    public ProjectDatabaseUser mapToEntityOnCreate(ProjectDatabaseUserCreateDto createDto) {
        ProjectDatabaseUser projectDatabaseUser = new ProjectDatabaseUser();
        ProjectDatabase database = projectDatabaseRepository.findById(createDto.getDatabaseId()).orElseThrow(() -> new RuntimeException("database not found"));
        projectDatabaseUser.setDatabase(database);
        projectDatabaseUser.setPassword(createDto.getDbPassword());
        projectDatabaseUser.setUsername(createDto.getDbUsername());
        projectDatabaseUser.setVersion(versionProviderService.getMaxVersion(database));
        return projectDatabaseUser;
    }

    public void mapUpdate(ProjectDatabaseUser projectDatabaseUser, ProjectDatabaseUserUpdateDto dto) {
        projectDatabaseUser.setPassword(dto.getDbPassword());
        projectDatabaseUser.setUsername(dto.getDbUsername());
        projectDatabaseUser.setRoles(dto.getRoles());
        ProjectDatabase database = projectDatabaseRepository.findById(dto.getDatabaseId()).orElseThrow(() -> new RuntimeException("database not found"));
        projectDatabaseUser.setDatabase(database);
        projectDatabaseUser.setVersion(versionProviderService.getMaxVersion(database));
    }

    public List<ProjectDatabaseUserDto> toListDto() {
        return repository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }
}
