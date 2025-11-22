package uz.pdp.todo;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
public class AppConfig {


//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//        RedisCacheManager cacheManager = RedisCacheManager.builder(redisConnectionFactory).build();
//
//        return cacheManager;
//    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {

        // Umumiy (default) TTL — agar cache nomida maxsus TTL berilmagan bo‘lsa shu ishlaydi
        RedisCacheConfiguration defaultConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(12))        // 10 daqiqa default TTL
//                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                .disableCachingNullValues();             // null qiymatlarni keshlamaslik

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(defaultConfig)  // default sozlamalar

//                // Agar ba’zi cache’larga boshqa TTL kerak bo‘lsa — quyidagicha qo‘shiladi
//                .withCacheConfiguration("users",
//                        defaultConfig.entryTtl(Duration.ofHours(1)))           // users cache — 1 soat
//                .withCacheConfiguration("products",
//                        defaultConfig.entryTtl(Duration.ofMinutes(30)))        // products — 30 daqiqa
//                .withCacheConfiguration("shortLivedCache",
//                        defaultConfig.entryTtl(Duration.ofSeconds(60)))        // 1 daqiqa
//                .withCacheConfiguration("veryLongCache",
//                        defaultConfig.entryTtl(Duration.ofDays(30)))           // 30 kun

                .build();
    }
}
