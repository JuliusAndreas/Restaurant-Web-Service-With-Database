package restaurant.manager.RestaurantWebServiceWithDatabase.Services;

import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Restaurant;
import restaurant.manager.RestaurantWebServiceWithDatabase.Repositories.RestaurantRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class RestaurantService {

    private RestaurantRepository restaurantRepository;

    public List<Restaurant> fetchAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant fetchOneRestaurant(@NonNull Integer id) {
        return restaurantRepository.findByRestaurantId(id);
    }

    public void addOneRestaurant(@NonNull Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    public void updateOneRestaurant(@NonNull Integer id, @NonNull Restaurant restaurant) {
        restaurantRepository.update(id, restaurant);
    }

    public void removeOneRestaurant(@NonNull Integer id) {
        restaurantRepository.deleteById(id);
    }
}
