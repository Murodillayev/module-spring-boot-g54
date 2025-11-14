package uz.pdp.todo.model.dto.databaseUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.todo.model.dto.authUser.AuthUserDto;
import uz.pdp.todo.model.entity.DatabaseRole;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDatabaseUserDto {
    private String id;
    private String dbUsername;
    private String dbPassword;
    private AuthUserDto authUserDto;
    private String databaseId;

    private List<DatabaseRole> roles;
}
