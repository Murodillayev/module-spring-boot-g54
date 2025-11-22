package uz.pdp.todo;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import uz.pdp.todo.dto.TodoResponseDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Profile("!redis")
public class InMemCacheService implements CacheService {

    private final Cache cache;

    public InMemCacheService(CacheManager manager) {
        this.cache = manager.getCache("todos");
    }
//    private final Map<String, List<TodoResponseDto>> cache = new HashMap<>();

    @Override
    public void put(String key, List<TodoResponseDto> todos) {
        cache.put(key, todos);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<TodoResponseDto> get(String key) {
         return (List<TodoResponseDto>) cache.get(key);
    }

    @Override
    public void remove(String todos) {
       cache.evict(todos);
    }
}
