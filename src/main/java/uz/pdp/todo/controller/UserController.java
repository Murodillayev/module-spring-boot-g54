package uz.pdp.todo.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.todo.model.dto.authUser.AuthUserCreateDto;
import uz.pdp.todo.model.dto.authUser.AuthUserDto;
import uz.pdp.todo.model.dto.authUser.AuthUserUpdateDto;
import uz.pdp.todo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Tag(name = "Foydalanuvchilar bilan ishlash uchun apilar")
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<AuthUserDto> create(@RequestBody AuthUserCreateDto dto) {
        AuthUserDto authUserDto = service.create(dto);
        return new ResponseEntity<>(authUserDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthUserDto> update(@RequestBody AuthUserUpdateDto dto, @PathVariable String id) {
        AuthUserDto authUserDto = service.update(id, dto);
        return new ResponseEntity<>(authUserDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AuthUserDto> updateIgnoreNull(@RequestBody AuthUserUpdateDto dto, @PathVariable String id) {
        AuthUserDto authUserDto = service.updateIgnoreNull(id, dto);
        return new ResponseEntity<>(authUserDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthUserDto> get(@PathVariable String id) {
        AuthUserDto authUserDto = service.get(id);
        return new ResponseEntity<>(authUserDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AuthUserDto>> getAll() {
        List<AuthUserDto> users = service.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
