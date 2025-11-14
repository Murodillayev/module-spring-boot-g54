package uz.pdp.todo.model.dto.agent;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectAgentCreateDTO {
    private String name;
    private String databaseUsername;
    private String databasePassword;
    private String databaseUrl;
}
