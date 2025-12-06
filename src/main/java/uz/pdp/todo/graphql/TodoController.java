package uz.pdp.todo.graphql;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.todo.model.TodoCreateDto;
import uz.pdp.todo.model.TodoDto;
import uz.pdp.todo.service.TodoService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TodoController {

    private final TodoService service;

    @SchemaMapping(typeName = "Query", value = "getTodos")
    public List<TodoDto> getAll() {
        return service.getAll();
    }


    @SchemaMapping(typeName = "Query", value = "getTodo")
    public TodoDto get(@Argument Long id) {
        return service.get(id);
    }

    @SchemaMapping(typeName = "Mutation", value = "createTodo")
    public TodoDto get(@Argument TodoCreateDto dto) {
        return service.create(dto);
    }

    @SchemaMapping(typeName = "Mutation", value = "deleteTodo")
    public Boolean delete(@Argument Long id) {
        service.delete(id);
        return true;
    }
}
