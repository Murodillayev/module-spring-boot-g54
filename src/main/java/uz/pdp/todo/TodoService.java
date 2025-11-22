package uz.pdp.todo;

// TodoService.java

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.todo.dto.TodoRequestDto;
import uz.pdp.todo.dto.TodoResponseDto;

import java.util.List;
import java.util.stream.Collectors;


// Map todos = new
// Map todo = new  1, todo, 2, todo
// todos pit todos,List<Todo>

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    @SneakyThrows
    @Cacheable(value = "todos", key = "#completed", condition = "#completed != null")
    public List<TodoResponseDto> getAllTodos(Boolean completed) {
        Thread.sleep(2000);

        if (completed != null) {
            return todoRepository.findAllByCompleted(completed).stream()
                    .map(this::toResponseDto)
                    .collect(Collectors.toList());
        } else {
            return todoRepository.findAll().stream()
                    .map(this::toResponseDto)
                    .collect(Collectors.toList());
        }

    }


    @Cacheable(value = "todo", key = "#id")
    public TodoResponseDto getTodoById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo topilmadi: " + id));
        return toResponseDto(todo);
    }

    @Transactional
    @CacheEvict(cacheNames = "todos", allEntries = true)
    public TodoResponseDto createTodo(TodoRequestDto request) {
        Todo todo = new Todo();
        todo.setTitle(request.title());
        todo.setDescription(request.description());
        todo.setCompleted(request.completed() != null ? request.completed() : false);

        todo = todoRepository.save(todo);
        return toResponseDto(todo);
    }

    @Transactional
    @CachePut(cacheNames = "todos", key = "#id")
    public TodoResponseDto updateTodo(Long id, TodoRequestDto request) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo topilmadi: " + id));

        todo.setTitle(request.title());
        todo.setDescription(request.description());
        if (request.completed() != null) {
            todo.setCompleted(request.completed());
        }

        todo = todoRepository.save(todo);
        return toResponseDto(todo);
    }

    // Faqat completed holatini o'zgartirish (masalan, checkbox bosilganda)
    @Transactional
    @CacheEvict(cacheNames = "todos", allEntries = true)
    public TodoResponseDto toggleCompleted(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo topilmadi: " + id));

        todo.setCompleted(!todo.getCompleted());
        todo = todoRepository.save(todo);
        return toResponseDto(todo);
    }

    @Transactional
    @CacheEvict(cacheNames = "todos", allEntries = true)
    public void deleteTodo(Long id) {

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
