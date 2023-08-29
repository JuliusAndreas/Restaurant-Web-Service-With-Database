package restaurant.manager.RestaurantWebServiceWithDatabase;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Food;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Reservation;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Restaurant;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.User;
import restaurant.manager.RestaurantWebServiceWithDatabase.DAOExtensions.FoodDAO;
import restaurant.manager.RestaurantWebServiceWithDatabase.DAOExtensions.ReservationDAO;
import restaurant.manager.RestaurantWebServiceWithDatabase.DAOExtensions.RestaurantDAO;
import restaurant.manager.RestaurantWebServiceWithDatabase.Repositories.FoodRepository;
import restaurant.manager.RestaurantWebServiceWithDatabase.Repositories.ReservationRepository;
import restaurant.manager.RestaurantWebServiceWithDatabase.Repositories.RestaurantRepository;
import restaurant.manager.RestaurantWebServiceWithDatabase.Repositories.UserRepository;
import restaurant.manager.RestaurantWebServiceWithDatabase.Utilities.FoodQuantity;

import java.util.List;

@SpringBootApplication
public class RestaurantWebServiceWithDatabaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantWebServiceWithDatabaseApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(RestaurantRepository restaurantRepository
            , UserRepository userRepository
            , ReservationRepository reservationRepository
            , FoodRepository foodRepository) {
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
//            tempRestaurant.setOwner(userRepository.findByUserId(2));
//
//            restaurantRepository.save(tempRestaurant);
//
//            List<Food> fetchedFoods = foodRepository.findByRestaurant(tempRestaurant);

//            Food tempFood1 = foodRepository.findByFoodId(9);
//            Food tempFood2 = foodRepository.findByFoodId(10);
//            User tempUser = userRepository.findByUserId(2);
//            Reservation reservation1 = new Reservation(tempFood1, tempUser);
//            Reservation reservation2 = new Reservation(tempFood2, tempUser);
//            reservationRepository.save(reservation1);
//            reservationRepository.save(reservation2);
//
//            System.out.println(reservationRepository.findByUser(tempUser));

//            Restaurant restaurant = restaurantRepository.findByRestaurantId(8);
//            System.out.println(foodRepository.findByRestaurantName("asghar mashti"));
//
//            System.out.println(userRepository.findUsersByFood(2));
//            Food food = new Food("Gheymeh", FoodQuantity.HIGH, 10.0, 20.0);
//            Restaurant restaurant = new Restaurant("Asghar mashti");
//            restaurant.setOwner(userRepository.findByUserId(5));
//            food.setRestaurant(restaurant);
//            foodRepository.update(9, food);
//            Food tempFood = foodDAO.findById(3);
//            User tempUser = userDAO.findById(2);
//            Reservation reservation = new Reservation(tempFood, tempUser);
//            reservationDAO.save(reservation);
//            Restaurant restaurant = restaurantDAO.findById(5);
//            restaurantDAO.delete(5);
//            User newUser = new User("Mali", "ali123");
//            userRepository.fi;
        };
    }

}
