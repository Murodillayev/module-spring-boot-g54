package uz.pdp.todo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class HomeController {
    private final TodoRemoteService todoRemoteService;

    public HomeController(TodoRemoteService todoRemoteService) {
        this.todoRemoteService = todoRemoteService;
    }
    // HttpClient

    // RestTemplate // deprecated -> spring 6

    // WebClient
    // FeignClient


    @GetMapping
    public List<Todo> getAll() {
        return todoRemoteService.getTodos();
    }

    @PostMapping
    public Todo create(@RequestBody Todo todo) {
        return todoRemoteService.createTodo(todo);
    }
}
