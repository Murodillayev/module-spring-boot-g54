package uz.pdp.todo.model.dto.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.todo.model.dto.ProjectAgentDTO;
import uz.pdp.todo.model.dto.databaseUser.ProjectDatabaseUserDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDatabaseDto {
    private String id;
    private String name;
    private String description;

    private ProjectAgentDTO agent;

    private List<ProjectDatabaseUserDto> members;
}
