package uz.pdp.todo;

import uz.pdp.todo.dto.TodoResponseDto;

import java.util.List;

public interface CacheService {

    void putUpdate(String key, Long id, TodoResponseDto dto);

    void put(String key, List<TodoResponseDto> todos);

    List<TodoResponseDto> get(String key);

    void remove(String todos);
}
