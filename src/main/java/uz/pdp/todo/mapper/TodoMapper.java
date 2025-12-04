package uz.pdp.todo.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.todo.model.Todo;
import uz.pdp.todo.model.TodoCreateDto;
import uz.pdp.todo.model.TodoDto;
import uz.pdp.todo.model.TodoUpdateDto;

@Component
public class TodoMapper {
    public TodoDto toDto(Todo todo) {
        return TodoDto.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .completed(todo.isCompleted())
                .description(todo.getDescription())
                .build();
    }

    public Todo fromDto(TodoCreateDto dto) {
        Todo todo = new Todo();
        todo.setTitle(dto.getTitle());
        todo.setDescription(dto.getDescription());
        todo.setCompleted(false);
        return todo;
    }

    public void fromDto(TodoUpdateDto dto, Todo todo) {
        todo.setTitle(dto.getTitle());
        todo.setDescription(dto.getDescription());
        todo.setCompleted(dto.getCompleted());
    }
}
