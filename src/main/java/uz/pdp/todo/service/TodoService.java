package uz.pdp.todo.service;

import uz.pdp.todo.model.TodoCreateDto;
import uz.pdp.todo.model.TodoDto;
import uz.pdp.todo.model.TodoUpdateDto;

import java.util.List;

public interface TodoService {
    /**
     * @param dto
     * @return
     */
    TodoDto create(TodoCreateDto dto);

    /**
     * @param dto
     * @param id
     * @return
     */
    TodoDto update(Long id, TodoUpdateDto dto);


    /**
     * @param id
     */
    void delete(Long id);

    TodoDto get(Long id);

    List<TodoDto> getAll();
}
