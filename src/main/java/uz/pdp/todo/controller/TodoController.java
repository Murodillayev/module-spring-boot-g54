package uz.pdp.todo.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.todo.exception.NotFoundException;
import uz.pdp.todo.dao.TodoDao;
import uz.pdp.todo.model.Todo;
import uz.pdp.todo.model.dto.TodoDto;
import uz.pdp.todo.repository.TodoRepository;

import java.util.List;
import java.util.UUID;

@RequestMapping("/todo")
@RestController
@RequiredArgsConstructor
public class TodoController {
    private final TodoRepository repository;

    //
    @GetMapping("/{id}")
    public Todo get(@PathVariable String id) {
        Todo todo = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Id = %s not found".formatted(id))
        );
        return todo;
    }

    @GetMapping
    public List<Todo> getAll(
            @RequestParam(defaultValue = "") String search
    ) {

        return repository.findAll();
    }

    @DeleteMapping("/{id}")
    public List<Todo> delete(@PathVariable String id) {
        repository.deleteById(id);
        return repository.findAll();

    }

    @PostMapping
    public Todo create(@RequestBody TodoDto dto) {
        Todo todo = Todo.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .completed(false)
                .id(UUID.randomUUID().toString())
                .build();

        return repository.save(todo);

    }

    @PutMapping("/{id}")
    public Todo update(@PathVariable String id, @RequestBody TodoDto dto) {
        Todo todo = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Id = %s not found".formatted(id))
        );
        todo.setTitle(dto.getTitle());
        todo.setDescription(dto.getDescription());
        return repository.save(todo);
    }


}
