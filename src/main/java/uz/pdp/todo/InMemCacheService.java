package uz.pdp.todo;

import org.springframework.stereotype.Service;
import uz.pdp.todo.dto.TodoResponseDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InMemCacheService implements CacheService {

    private final Map<String, List<TodoResponseDto>> cache = new HashMap<>();

    @Override
    public void put(String key, List<TodoResponseDto> todos) {
        cache.put(key, todos);
    }

    @Override
    public List<TodoResponseDto> get(String key) {
        return cache.get(key);
    }

    @Override
    public void remove(String todos) {
        cache.remove(todos);
    }
}
