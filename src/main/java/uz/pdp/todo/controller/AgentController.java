package uz.pdp.todo.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.todo.model.dto.agent.ProjectAgentCreateDTO;
import uz.pdp.todo.model.dto.agent.ProjectAgentDTO;
import uz.pdp.todo.model.dto.UserDto;
import uz.pdp.todo.service.DatabaseUserService;
import uz.pdp.todo.service.ProjectAgentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/agent")
@RequiredArgsConstructor
public class AgentController {

    private final DatabaseUserService databaseUserService;
    private final ProjectAgentService projectAgentService;

    @PostMapping("/register")
    public ResponseEntity<ProjectAgentDTO> register(@RequestBody ProjectAgentCreateDTO dto) {
        ProjectAgentDTO projectAgentDTO = projectAgentService.createWithDb(dto);
        return ResponseEntity.ok(projectAgentDTO);

    }

    @GetMapping("/updates")
    public ResponseEntity<List<UserDto>> create(@RequestParam String agentId, @RequestParam Long version) {
        List<UserDto> users = databaseUserService.getAllForAgent(agentId, version);
        return ResponseEntity.ok(users);

    }
}
