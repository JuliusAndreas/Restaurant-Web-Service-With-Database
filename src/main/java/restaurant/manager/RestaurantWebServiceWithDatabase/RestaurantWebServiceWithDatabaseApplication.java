package restaurant.manager.RestaurantWebServiceWithDatabase;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Food;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Restaurant;
import restaurant.manager.RestaurantWebServiceWithDatabase.Repositories.RestaurantDAO;
import restaurant.manager.RestaurantWebServiceWithDatabase.Utilities.FoodQuantity;

@SpringBootApplication
public class RestaurantWebServiceWithDatabaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantWebServiceWithDatabaseApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(RestaurantDAO restaurantDAO) {
        return runner -> {
            Restaurant tempRestaurant =
                    new Restaurant("The Cherry Blossoms");

            Food food1 = new Food("Sushi", FoodQuantity.MEDIUM, 8.0, 11.5);
            Food food2 = new Food("Ramen", FoodQuantity.HIGH, 9.0, 10.5);

            tempRestaurant.add(food1);
            tempRestaurant.add(food2);

            restaurantDAO.save(tempRestaurant);
        };
    }

}
