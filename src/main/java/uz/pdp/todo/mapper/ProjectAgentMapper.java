package uz.pdp.todo.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.todo.model.dto.ProjectAgentCreateDTO;
import uz.pdp.todo.model.dto.ProjectAgentResponseDTO;
import uz.pdp.todo.model.entity.ProjectAgent;

@Component
public class ProjectAgentMapper implements BaseMapper {

    public ProjectAgent toEntityOnCreate(ProjectAgentCreateDTO dto) {
        ProjectAgent projectAgent = new ProjectAgent();
        projectAgent.setName(dto.getName());
        projectAgent.setDatabaseUsername(dto.getDatabaseUsername());
        projectAgent.setDatabasePassword(dto.getDatabasePassword());
        projectAgent.setDatabaseUrl(dto.getDatabaseUrl());
        return projectAgent;
    }

    public ProjectAgent toEntity(ProjectAgentResponseDTO dto) {
        ProjectAgent projectAgent = new ProjectAgent();
        projectAgent.setId(dto.getId());
        projectAgent.setName(dto.getName());
        projectAgent.setDatabaseUsername(dto.getDatabaseUsername());
        projectAgent.setDatabasePassword(dto.getDatabasePassword());
        projectAgent.setDatabaseUrl(dto.getDatabaseUrl());
        return projectAgent;
    }

    public ProjectAgentResponseDTO toDto(ProjectAgent projectAgent) {
        ProjectAgentResponseDTO dto = new ProjectAgentResponseDTO();
        dto.setId(projectAgent.getId());
        dto.setName(projectAgent.getName());
        dto.setDatabaseUsername(projectAgent.getDatabaseUsername());
        dto.setDatabasePassword(projectAgent.getDatabasePassword());
        dto.setDatabaseUrl(projectAgent.getDatabaseUrl());
        return dto;
    }

}
