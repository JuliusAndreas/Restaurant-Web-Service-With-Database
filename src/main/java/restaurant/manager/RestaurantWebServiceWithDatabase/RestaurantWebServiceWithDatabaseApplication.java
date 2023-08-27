package restaurant.manager.RestaurantWebServiceWithDatabase;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Food;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Restaurant;
import restaurant.manager.RestaurantWebServiceWithDatabase.Repositories.FoodDAO;
import restaurant.manager.RestaurantWebServiceWithDatabase.Repositories.ReservationDAO;
import restaurant.manager.RestaurantWebServiceWithDatabase.Repositories.RestaurantDAO;
import restaurant.manager.RestaurantWebServiceWithDatabase.Repositories.UserDAO;

import java.util.List;

@SpringBootApplication
public class RestaurantWebServiceWithDatabaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantWebServiceWithDatabaseApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(RestaurantDAO restaurantDAO
            , UserDAO userDAO
            , ReservationDAO reservationDAO
            , FoodDAO foodDAO) {
        return runner -> {
//            Restaurant tempRestaurant =
//                    new Restaurant("The Cherry Blossoms");
//
//            Food food1 = new Food("Sushi", FoodQuantity.MEDIUM, 8.0, 11.5);
//            Food food2 = new Food("Ramen", FoodQuantity.HIGH, 9.0, 10.5);
//
//            tempRestaurant.add(food1);
//            tempRestaurant.add(food2);
//
//            tempRestaurant.setOwner(userDAO.findById(2));
//
//            restaurantDAO.save(tempRestaurant);

//            Food tempFood = foodDAO.findById(3);
//            User tempUser = userDAO.findById(2);
//            Reservation reservation = new Reservation(tempFood, tempUser);
//            reservationDAO.save(reservation);
            Restaurant restaurant = restaurantDAO.findByIdJoinFetch(5);
            System.out.println("foods: " + restaurant.getFoods());


        };
    }

}
