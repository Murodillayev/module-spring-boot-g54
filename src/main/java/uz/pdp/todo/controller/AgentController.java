package uz.pdp.todo.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.todo.model.dto.UserDto;
import uz.pdp.todo.service.DatabaseUserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/agent")
public class AgentController {

    private final DatabaseUserService databaseUserService;

    public AgentController(DatabaseUserService databaseUserService) {
        this.databaseUserService = databaseUserService;
    }

    @GetMapping("/updates")
    public ResponseEntity<List<?>> create(@RequestParam String agentId, @RequestParam Long version) {
        List<UserDto> users = databaseUserService.getAllForAgent(agentId, version);
        return ResponseEntity.ok(users);

    }
}
