package uz.pdp.todo.model.dto.databaseUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.todo.model.entity.DatabaseRole;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDatabaseUserCreateDto {
    private String dbUsername;
    private String dbPassword;
    private String databaseId;
    private String authUserId;
    private List<String> roleIds = Collections.emptyList();
}