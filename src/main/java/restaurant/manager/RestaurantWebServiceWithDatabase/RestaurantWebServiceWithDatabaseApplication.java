package restaurant.manager.RestaurantWebServiceWithDatabase;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.User;
import restaurant.manager.RestaurantWebServiceWithDatabase.Repositories.UserDAO;

import java.util.List;

@SpringBootApplication
public class RestaurantWebServiceWithDatabaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantWebServiceWithDatabaseApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(UserDAO userDAO) {
        return runner -> {

        };
    }

}
