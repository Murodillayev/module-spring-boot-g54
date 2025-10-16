package uz.pdp.todo;


import org.springframework.web.bind.annotation.*;
import uz.pdp.todo.dao.TodoDao;
import uz.pdp.todo.model.Todo;

import java.util.List;
import java.util.UUID;

@RequestMapping("/todo")
@RestController
public class TodoController {

    private final TodoDao dao;

    public TodoController(TodoDao dao) {
        this.dao = dao;
    }

    //
    @GetMapping("/{id}")
    public Todo get(@PathVariable String id) {
        Todo todo = dao.findById(id).orElseThrow(
                () -> new NotFoundException("Id = %s not found".formatted(id))
        );
        return todo;
    }

    @GetMapping
    public List<Todo> getAll() {
        return dao.findAll();
    }

    @DeleteMapping("/{id}")
    public List<Todo> delete(@PathVariable String id) {
        dao.deleteById(id);
        return dao.findAll();

    }

    @PostMapping
    public Todo create(@RequestBody TodoDto dto) {
        Todo todo = Todo.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .completed(false)
                .id(UUID.randomUUID().toString())
                .build();

        return dao.save(todo);

    }

    @PutMapping("/{id}")
    public Todo update(@PathVariable String id, @RequestBody TodoDto dto) {
        Todo todo = dao.findById(id).orElseThrow(
                () -> new NotFoundException("Id = %s not found".formatted(id))
        );
        todo.setTitle(dto.getTitle());
        todo.setDescription(dto.getDescription());
        return dao.save(todo);

    }


}
