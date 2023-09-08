package restaurant.manager.RestaurantWebServiceWithDatabase.Configurations;

import lombok.AllArgsConstructor;
import org.redisson.api.RedissonClient;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Configuration
@ComponentScan
@EnableCaching
public class CacheConfig {

    public static String GLOBAL_RESTAURANTS_CACHE_NAME = "restaurants-from-db";

    @Autowired
    private RedissonClient client;

    @Bean
    CacheManager cacheManager(RedissonClient redissonClient) {
        Map<String, org.redisson.spring.cache.CacheConfig> config = new HashMap<>();
        config.put("testMap", new org.redisson.spring.cache.CacheConfig(24*60*1000, 12*60*1000));
        return new RedissonSpringCacheManager(redissonClient, config);
    }
}
