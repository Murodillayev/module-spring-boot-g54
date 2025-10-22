package uz.pdp.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.todo.AuthUserDto;
import uz.pdp.todo.exception.NotFoundException;
import uz.pdp.todo.model.AuthUser;
import uz.pdp.todo.model.dto.AuthUserSaveDto;
import uz.pdp.todo.model.dto.TodoInfo;
import uz.pdp.todo.repository.UserRepository;

import java.util.List;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserRepository repository;

    @PostMapping
    public AuthUser create(@RequestBody AuthUserSaveDto user) {
        AuthUser authUser = new AuthUser();
        authUser.setUsername(user.getUsername());
        authUser.setPassword(user.getPassword());
        return repository.save(authUser);
    }

    @PutMapping("/{id}")
    public AuthUser update(@RequestBody AuthUserSaveDto user, @PathVariable String id) {
        AuthUser authUser = repository.findById(id).orElseThrow(
                () -> new NotFoundException("User with id " + id + " not found")
        );
        authUser.setUsername(user.getUsername());
        authUser.setPassword(user.getPassword());
        return repository.save(authUser);
    }

    @GetMapping
    public List<AuthUserDto> getAll() {
        List<AuthUser> all = repository.findAll();
        return all.stream().map(a -> AuthUserDto.builder()
                .username(a.getUsername())
                .password(a.getPassword())
                .todos(a.getTodos().stream().map(t -> TodoInfo.builder()
                        .description(t.getDescription())
                        .title(t.getTitle())
                        .id(t.getId())
                        .build()
                ).toList())
                .build()).toList();

    }
}
