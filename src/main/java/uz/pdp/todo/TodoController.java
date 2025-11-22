package uz.pdp.todo;

import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TodoController.java
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import uz.pdp.todo.dto.TodoRequestDto;
import uz.pdp.todo.dto.TodoResponseDto;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Frontend uchun, agar kerak bo'lsa
public class TodoController {

    private final TodoService todoService;
    private final CacheService cache;

    // GET /api/todos
    @GetMapping
    @SneakyThrows
    public ResponseEntity<List<TodoResponseDto>> getAllTodos(@RequestParam(required = false) Boolean completed) {

        return ResponseEntity.ok(todoService.getAllTodos(completed));
    }

    // GET /api/todos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> getTodoById(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.getTodoById(id));
    }

    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodo(@RequestBody TodoRequestDto request) {
        return new ResponseEntity<>(todoService.createTodo(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponseDto> updateTodo(
            @PathVariable Long id,
            @RequestBody TodoRequestDto request) {
        return ResponseEntity.ok(todoService.updateTodo(id, request));
    }

    // PATCH /api/todos/{id}/toggle
    @PatchMapping("/{id}/toggle")
    public ResponseEntity<TodoResponseDto> toggleCompleted(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.toggleCompleted(id));
    }

    // DELETE /api/todos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}
