package uz.pdp.todo.model.dto.database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectDatabaseCreateDto {
    private String name;
    private String description;
    private String agentId;
    private List<Long> membersId;
}
