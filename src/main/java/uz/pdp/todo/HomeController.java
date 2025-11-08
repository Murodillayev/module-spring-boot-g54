package uz.pdp.todo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class HomeController {
    private final TodoRemoteService todoRemoteService;

    public HomeController(TodoRemoteService todoRemoteService) {
        this.todoRemoteService = todoRemoteService;
    }
    @GetMapping
    public List<Todo> getAll() {
        return todoRemoteService.getTodos();
    }

    @GetMapping("/{id}")
    public Todo get(@PathVariable Integer id) {
        return todoRemoteService.getTodo(id);
    }

    @PostMapping
    public Todo create(@RequestBody Todo todo) {
        return todoRemoteService.createTodo(todo);
    }
}
