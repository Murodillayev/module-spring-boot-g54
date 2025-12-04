package uz.pdp.todo.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.todo.model.TodoCreateDto;
import uz.pdp.todo.model.TodoDto;
import uz.pdp.todo.model.TodoUpdateDto;
import uz.pdp.todo.service.TodoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
@RequiredArgsConstructor
public class TodoResource {
    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<List<TodoDto>> getAll() {
        return new ResponseEntity<>(todoService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> get(@PathVariable Long id) {
        return new ResponseEntity<>(todoService.get(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TodoDto> create(@RequestBody TodoCreateDto createDto) {
        return new ResponseEntity<>(todoService.create(createDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoDto> update(@PathVariable Long id, @RequestBody TodoUpdateDto updateDto) {
        return new ResponseEntity<>(todoService.update(id, updateDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        todoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
