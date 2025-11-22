package uz.pdp.todo;

import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import uz.pdp.todo.dto.TodoResponseDto;

import java.util.List;

@Configuration
public class AppConfig {

//    @Bean
//    public RedisTemplate<String, List<TodoResponseDto>> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<String, List<TodoResponseDto>> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//        return redisTemplate;
//    }

    @Bean
    public CacheManager getCacheManager() {
        return new CaffeineCacheManager();
    }
}
