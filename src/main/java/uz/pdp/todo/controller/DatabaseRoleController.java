package uz.pdp.todo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.todo.model.dto.DatabaseRoleCreateDTO;
import uz.pdp.todo.model.dto.DatabaseRoleDTO;
import uz.pdp.todo.service.DatabaseRoleService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/database-role")
public record DatabaseRoleController(
        DatabaseRoleService service
) {

    @GetMapping
    public ResponseEntity<List<DatabaseRoleDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatabaseRoleDTO> get(@PathVariable String id) {
        return ResponseEntity.ok(service.get(id));
    }

    @PostMapping
    public ResponseEntity<DatabaseRoleDTO> create(@RequestBody DatabaseRoleCreateDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatabaseRoleDTO> update(@PathVariable("id") String id, @RequestBody DatabaseRoleDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        service.delete(id);
        return ResponseEntity.ok("Role has deleted successfully!");
    }

}
