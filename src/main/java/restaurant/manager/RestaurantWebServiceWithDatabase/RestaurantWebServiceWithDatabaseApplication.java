package restaurant.manager.RestaurantWebServiceWithDatabase;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class RestaurantWebServiceWithDatabaseApplication {
    @Autowired
    RedissonClient client;

    public static void main(String[] args) {
        SpringApplication.run(RestaurantWebServiceWithDatabaseApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return runner -> {

        };
    }
}
