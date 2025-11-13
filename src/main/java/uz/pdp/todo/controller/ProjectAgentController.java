package uz.pdp.todo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.todo.model.dto.ProjectAgentCreateDTO;
import uz.pdp.todo.model.dto.ProjectAgentDTO;
import uz.pdp.todo.model.dto.ProjectAgentUpdateDTO;
import uz.pdp.todo.service.ProjectAgentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project-agent")
public record ProjectAgentController(
        ProjectAgentService service
) {

    @GetMapping
    public ResponseEntity<List<ProjectAgentDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectAgentDTO> get(@PathVariable("id") String id) {
        return ResponseEntity.ok(service.get(id));
    }

    @PostMapping
    public ResponseEntity<ProjectAgentDTO> create(@RequestBody ProjectAgentCreateDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectAgentDTO> update(@PathVariable("id") String id, @RequestBody ProjectAgentUpdateDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        service.delete(id);
        return ResponseEntity.ok("Agent has deleted successfully!");
    }

    @GetMapping("/getByDBUrl")
    public ResponseEntity<ProjectAgentDTO> getByDBUrl(@RequestParam String dbUrl) {
        return ResponseEntity.ok(service.getAgentByDBUrl(dbUrl));
    }
}
