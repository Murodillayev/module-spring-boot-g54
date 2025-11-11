package uz.pdp.todo.model.dto.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDatabaseUpdateDto {
    private String name;
    private String description;
    private String agentId;

    private List<Long> membersId;
}
