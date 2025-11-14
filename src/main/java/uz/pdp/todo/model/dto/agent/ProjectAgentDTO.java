package uz.pdp.todo.model.dto.agent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectAgentDTO {
    private String id;
    private String name;
    private String databaseUsername;
    private String databasePassword;
    private String databaseUrl;
}
