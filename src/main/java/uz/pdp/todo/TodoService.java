package uz.pdp.todo;

// TodoService.java

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.todo.dto.TodoRequestDto;
import uz.pdp.todo.dto.TodoResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final CacheService cacheService;

    @SneakyThrows
    public List<TodoResponseDto> getAllTodos() {

        List<TodoResponseDto> todos = cacheService.get("todos");
        if (todos != null) {
            return todos;

        } else {
            Thread.sleep(2000);
            todos = todoRepository.findAll().stream()
                    .map(this::toResponseDto)
                    .collect(Collectors.toList());

            cacheService.put("todos", todos);
        }

        return todos;
    }

    public TodoResponseDto getTodoById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo topilmadi: " + id));
        return toResponseDto(todo);
    }

    @Transactional
    public TodoResponseDto createTodo(TodoRequestDto request) {
        cacheService.remove("todos");
        Todo todo = new Todo();
        todo.setTitle(request.title());
        todo.setDescription(request.description());
        todo.setCompleted(request.completed() != null ? request.completed() : false);

        todo = todoRepository.save(todo);
        return toResponseDto(todo);
    }

    @Transactional
    public TodoResponseDto updateTodo(Long id, TodoRequestDto request) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo topilmadi: " + id));

        todo.setTitle(request.title());
        todo.setDescription(request.description());
        if (request.completed() != null) {
            todo.setCompleted(request.completed());
        }

        todo = todoRepository.save(todo);
        TodoResponseDto responseDto = toResponseDto(todo);
        cacheService.putUpdate("todos", id, responseDto);
        return responseDto;
    }

    // Faqat completed holatini o'zgartirish (masalan, checkbox bosilganda)
    @Transactional
    public TodoResponseDto toggleCompleted(Long id) {
        cacheService.remove("todos");
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo topilmadi: " + id));

        todo.setCompleted(!todo.getCompleted());
        todo = todoRepository.save(todo);
        return toResponseDto(todo);
    }

    @Transactional
    public void deleteTodo(Long id) {

        cacheService.remove("todos");
        if (!todoRepository.existsById(id)) {
            throw new RuntimeException("Todo topilmadi: " + id);
        }
        todoRepository.deleteById(id);
    }

    private TodoResponseDto toResponseDto(Todo todo) {
        return new TodoResponseDto(
                todo.getId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.getCompleted()
        );
    }
}
