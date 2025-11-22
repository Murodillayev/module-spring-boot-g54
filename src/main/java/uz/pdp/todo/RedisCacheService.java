package uz.pdp.todo;

import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import uz.pdp.todo.dto.TodoResponseDto;

import java.util.List;

@Service
@Profile("redis")
public class RedisCacheService implements CacheService {
    private final RedisTemplate<String, List<TodoResponseDto>> redisTemplate;
    private final ValueOperations<String, List<TodoResponseDto>> operations;


    public RedisCacheService(RedisTemplate<String, List<TodoResponseDto>> redisTemplate) {
        this.operations = redisTemplate.opsForValue();
        this.redisTemplate = redisTemplate;
    }


    @Override
    public void put(String key, List<TodoResponseDto> todos) {
        operations.set(key, todos);
    }

    @Override
    public List<TodoResponseDto> get(String key) {
        return operations.get(key);
    }

    @Override
    public void remove(String todos) {
        redisTemplate.delete(todos);
    }
}

// webhook-telegrambot + serverga app qoyish
// websocket
// Spring shell
// graphql
// unit test
// microservices
// docker
// nats, rabbit

