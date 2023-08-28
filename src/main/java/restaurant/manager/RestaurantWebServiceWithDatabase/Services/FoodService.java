package restaurant.manager.RestaurantWebServiceWithDatabase.Services;

import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Food;
import restaurant.manager.RestaurantWebServiceWithDatabase.Repositories.FoodRepository;
import restaurant.manager.RestaurantWebServiceWithDatabase.Repositories.RestaurantRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class FoodService {

    private FoodRepository foodRepository;
    private RestaurantRepository restaurantRepository;

    public List<Food> fetchAllFoodsFromOneRestaurantByName(@NonNull String restaurantName) {
        return foodRepository.findByRestaurantName(restaurantName);
    }

    public List<Food> fetchAllFoodsFromOneRestaurantById(@NonNull Integer id) {
        return restaurantRepository.getFoodsByRestaurantId(id);
    }

    public void addFoodToOneRestaurant(@NonNull Integer id, @NonNull Food food) {
        restaurantRepository.findByRestaurantId(id).add(food);
    }

    public void updateOneFoodFromOneRestaurant(@NonNull Integer foodId, @NonNull Food food) {
        foodRepository.update(foodId, food);
    }

    public void removeOneFoodFromOneRestaurant(@NonNull Integer foodId) {
        foodRepository.deleteById(foodId);
    }
}
