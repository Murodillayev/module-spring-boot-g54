package uz.pdp.todo.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.todo.model.dto.AuthRoleChangeDto;
import uz.pdp.todo.model.dto.AuthRoleDto;
import uz.pdp.todo.service.AuthRoleService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
public class AuthRoleController {

    private final AuthRoleService service;


    @PostMapping
    public ResponseEntity<AuthRoleDto> create(@RequestBody AuthRoleChangeDto dto) {
        AuthRoleDto response = service.create(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthRoleDto> update(@RequestBody AuthRoleChangeDto dto, @PathVariable String id) {
        AuthRoleDto response = service.update(id, dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthRoleDto> get(@PathVariable String id) {
        AuthRoleDto resp = service.get(id);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AuthRoleDto>> getAll() {
        List<AuthRoleDto> roles = service.getAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
