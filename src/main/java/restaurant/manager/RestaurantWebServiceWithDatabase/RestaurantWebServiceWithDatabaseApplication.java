package restaurant.manager.RestaurantWebServiceWithDatabase;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.User;
import restaurant.manager.RestaurantWebServiceWithDatabase.DAOExtensions.FoodDAO;
import restaurant.manager.RestaurantWebServiceWithDatabase.DAOExtensions.ReservationDAO;
import restaurant.manager.RestaurantWebServiceWithDatabase.DAOExtensions.RestaurantDAO;
import restaurant.manager.RestaurantWebServiceWithDatabase.Repositories.UserRepository;

@SpringBootApplication
public class RestaurantWebServiceWithDatabaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantWebServiceWithDatabaseApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(RestaurantDAO restaurantDAO
            , UserRepository userRepository
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
//            Restaurant restaurant = restaurantDAO.findById(5);
//            restaurantDAO.delete(5);
            User newUser = new User("Mali", "ali123");
            userRepository.deleteById(7);
        };
    }

}
