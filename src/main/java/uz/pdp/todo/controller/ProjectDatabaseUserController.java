package uz.pdp.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.todo.model.dto.databaseUser.ProjectDatabaseUserCreateDto;
import uz.pdp.todo.model.dto.databaseUser.ProjectDatabaseUserDto;
import uz.pdp.todo.model.dto.databaseUser.ProjectDatabaseUserUpdateDto;
import uz.pdp.todo.service.ProjectDatabaseUserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/databaseUser")
@AllArgsConstructor
public class ProjectDatabaseUserController {
    private final ProjectDatabaseUserService service;

    @PostMapping
    public ResponseEntity<ProjectDatabaseUserDto> create(@RequestBody ProjectDatabaseUserCreateDto createDto) {
        return new ResponseEntity<>(service.create(createDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProjectDatabaseUserDto>> getAll() {
        return new ResponseEntity<>(service.getAll(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDatabaseUserDto> get(@PathVariable("id") String id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProjectDatabaseUserDto> update(@RequestBody ProjectDatabaseUserUpdateDto updateDto, @PathVariable("id") String id) {
        return new ResponseEntity<>(service.update(id,updateDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteDatabase(@PathVariable("id") String id) {
        service.delete(id);
    }
}
