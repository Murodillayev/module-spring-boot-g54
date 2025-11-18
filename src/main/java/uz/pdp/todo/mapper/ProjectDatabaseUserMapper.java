package uz.pdp.todo.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.todo.model.dto.AuthUserDbsResponse;
import uz.pdp.todo.model.dto.databaseUser.ProjectDatabaseUserCreateDto;
import uz.pdp.todo.model.dto.databaseUser.ProjectDatabaseUserDto;
import uz.pdp.todo.model.dto.databaseUser.ProjectDatabaseUserUpdateDto;
import uz.pdp.todo.model.entity.AuthUser;
import uz.pdp.todo.model.entity.DatabaseRole;
import uz.pdp.todo.model.entity.ProjectDatabase;
import uz.pdp.todo.model.entity.ProjectDatabaseUser;
import uz.pdp.todo.repository.DatabaseRoleRepository;
import uz.pdp.todo.repository.ProjectDatabaseRepository;
import uz.pdp.todo.repository.ProjectDatabaseUserRepository;
import uz.pdp.todo.service.VersionProviderService;
import uz.pdp.todo.validator.AuthUserValidator;
import uz.pdp.todo.validator.ProjectDatabaseValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ProjectDatabaseUserMapper implements BaseMapper {
    private final ProjectDatabaseUserRepository repository;
    private final ProjectDatabaseRepository projectDatabaseRepository;
    private final VersionProviderService versionProviderService;
    private final DatabaseRoleRepository databaseRoleRepository;
    private final AuthUserValidator authUserValidator;
    private final AuthUserMapper authUserMapper;
    private final DatabaseRoleMapper databaseRoleMapper;
    private final ProjectDatabaseValidator projectDatabaseValidator;

    public List<ProjectDatabaseUserDto> mapToDtoList(List<ProjectDatabaseUser> all) {

        if (all == null) {
            return Collections.emptyList();
        }
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
        databaseUserDto.setAuthUserDto(authUserMapper.toDto(databaseUser.getAuthUser()));
        databaseUserDto.setRoles(databaseUser.getRoles());
        return databaseUserDto;
    }

    public ProjectDatabaseUser mapToEntityOnCreate(ProjectDatabaseUserCreateDto createDto) {
        ProjectDatabaseUser projectDatabaseUser = new ProjectDatabaseUser();
        ProjectDatabase database = projectDatabaseRepository.findById(createDto.getDatabaseId()).orElseThrow(() -> new RuntimeException("database not found"));
        AuthUser authUser = authUserValidator.existsAndGet(createDto.getAuthUserId());
        projectDatabaseValidator.checkIfMemberAlreadyExists(database,createDto.getAuthUserId());
        List<DatabaseRole> roles = databaseRoleRepository.findAllByIdIn(createDto.getRoleIds());
        projectDatabaseUser.setDatabase(database);
        projectDatabaseUser.setAuthUser(authUser);
        projectDatabaseUser.setPassword(createDto.getDbPassword());
        projectDatabaseUser.setUsername(createDto.getDbUsername());
        projectDatabaseUser.setRoles(roles);
        projectDatabaseUser.setVersion(versionProviderService.getMaxVersionAndAddOne(database));
        return projectDatabaseUser;
    }

    public void mapUpdate(ProjectDatabaseUser projectDatabaseUser, ProjectDatabaseUserUpdateDto dto) {
        List<DatabaseRole> roles = databaseRoleRepository.findAllById(dto.getRoleIds());
        projectDatabaseUser.setPassword(dto.getDbPassword());
        projectDatabaseUser.setUsername(dto.getDbUsername());
        projectDatabaseUser.setRoles(roles);
        ProjectDatabase database = projectDatabaseRepository.findById(dto.getDatabaseId()).orElseThrow(() -> new RuntimeException("database not found"));
        projectDatabaseUser.setDatabase(database);
        projectDatabaseUser.setVersion(versionProviderService.getMaxVersionAndAddOne(database));
    }

    public List<ProjectDatabaseUserDto> toListDto() {
        return repository.findAll()
                .stream()
                .filter(m->!m.getDeleted())
                .map(this::toDto)
                .toList();
    }

    public List<AuthUserDbsResponse> mapToAuthUserDbResponse(List<ProjectDatabase> allByMemberId,String authUserId) {
        return allByMemberId
                .stream()
                  .flatMap(db->db.getMembers().stream()
                    .filter(m->m.getAuthUser().getId().equals(authUserId)&& !m.getDeleted())
                      .map(m-> new AuthUserDbsResponse(
                              db.getName(),
                              m.getUsername(),
                              m.getPassword(),
                              databaseRoleMapper.toListDto(m.getRoles()))))
                .      toList();
    }

    public void mapToDelete(ProjectDatabaseUser projectDatabaseUser) {
        projectDatabaseUser.setDeleted(true);
        projectDatabaseUser.setVersion(versionProviderService.getMaxVersionAndAddOne(projectDatabaseUser.getDatabase()));
    }
}
