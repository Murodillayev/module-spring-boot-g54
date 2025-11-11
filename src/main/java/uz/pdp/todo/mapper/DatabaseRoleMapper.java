package uz.pdp.todo.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.todo.model.dto.DatabaseRoleCreateDTO;
import uz.pdp.todo.model.dto.DatabaseRoleDTO;
import uz.pdp.todo.model.entity.DatabaseRole;

@Component
public class DatabaseRoleMapper implements BaseMapper {

    public DatabaseRole toEntityOnCreate(DatabaseRoleCreateDTO dto) {
        DatabaseRole databaseRole = new DatabaseRole();
        databaseRole.setName(dto.getName());
        databaseRole.setDescription(dto.getDescription());
        databaseRole.setCode(dto.getCode());
        return databaseRole;
    }

    public DatabaseRole toEntity(DatabaseRoleDTO dto) {
        DatabaseRole databaseRole = new DatabaseRole();
        databaseRole.setId(dto.getId());
        databaseRole.setName(dto.getName());
        databaseRole.setDescription(dto.getDescription());
        databaseRole.setCode(dto.getCode());
        return databaseRole;
    }

    public DatabaseRoleDTO toDto(DatabaseRole databaseRole) {
        DatabaseRoleDTO dto = new DatabaseRoleDTO();
        dto.setId(databaseRole.getId());
        dto.setName(databaseRole.getName());
        dto.setDescription(databaseRole.getDescription());
        dto.setCode(databaseRole.getCode());
        return dto;
    }
}
