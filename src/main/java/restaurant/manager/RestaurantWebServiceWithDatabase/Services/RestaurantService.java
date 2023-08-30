package restaurant.manager.RestaurantWebServiceWithDatabase.Services;

import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Restaurant;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.User;
import restaurant.manager.RestaurantWebServiceWithDatabase.Exceptions.NotFoundException;
import restaurant.manager.RestaurantWebServiceWithDatabase.Repositories.RestaurantRepository;
import restaurant.manager.RestaurantWebServiceWithDatabase.Repositories.UserRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class RestaurantService {

    private RestaurantRepository restaurantRepository;
    private UserRepository userRepository;

    public List<Restaurant> fetchAllRestaurants() {
        return restaurantRepository.findAllRestaurantsJoinFetchFoodsOwner();
    }

    public Restaurant fetchOneRestaurant(@NonNull Integer id) {
        return restaurantRepository.findByRestaurantIdJoinFetchFoodsOwner(id);
    }

    public void addOneRestaurant(@NonNull Restaurant restaurant,
                                 @NonNull Integer ownerId) {
        User owner = userRepository.findByUserId(ownerId);
        restaurant.setOwner(owner);
        restaurantRepository.save(restaurant);
    }

    public void updateOneRestaurant(@NonNull Integer id, @NonNull Restaurant restaurant) {
        restaurantRepository.update(id, restaurant);
    }

    public void removeOneRestaurant(@NonNull Integer id) {
        if (!restaurantRepository.existsById(id)) {
            throw new NotFoundException("No Restaurant was found to be deleted");
        }
        restaurantRepository.deleteById(id);
    }
}
