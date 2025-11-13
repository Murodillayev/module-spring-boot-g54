package uz.pdp.todo.model.dto.databaseUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDatabaseUserUpdateDto {
    private String dbUsername;
    private String dbPassword;
    private String databaseId;
    private List<String> roleIds;
}
