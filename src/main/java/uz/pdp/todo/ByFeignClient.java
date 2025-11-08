package uz.pdp.todo;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "todo-service", url = "https://jsonplaceholder.typicode.com/todos")
public interface ByFeignClient extends TodoRemoteService {

    @GetMapping
    List<Todo> getTodos();

    @GetMapping("/{id}")
    Todo getTodo(@PathVariable Integer id);

    @PostMapping
    Todo createTodo(@RequestBody Todo todo);

    @DeleteMapping("/{id}")
    void deleteTodo(@PathVariable Integer id);
}
