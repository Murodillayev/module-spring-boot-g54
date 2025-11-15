package uz.pdp.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.todo.model.dto.database.ProjectDatabaseCreateDto;
import uz.pdp.todo.model.dto.database.ProjectDatabaseDto;
import uz.pdp.todo.model.dto.database.ProjectDatabaseUpdateDto;
import uz.pdp.todo.service.ProjectDatabaseService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/database")
@AllArgsConstructor
public class ProjectDatabaseController {
    private final ProjectDatabaseService service;

    @PostMapping
    public ResponseEntity<ProjectDatabaseDto> create(@RequestBody ProjectDatabaseCreateDto createDto) {
        return new ResponseEntity<>(service.create(createDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProjectDatabaseDto>> getAll() {
        List<ProjectDatabaseDto> all = service.getAll();
        return new ResponseEntity<>(all,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDatabaseDto> get(@PathVariable("id") String id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProjectDatabaseDto> update(@RequestBody ProjectDatabaseUpdateDto updateDto, @PathVariable("id") String id) {
        return new ResponseEntity<>(service.update(id,updateDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        service.delete(id);
    }
}
