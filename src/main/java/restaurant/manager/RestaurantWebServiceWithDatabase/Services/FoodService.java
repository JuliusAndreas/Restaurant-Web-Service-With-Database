package restaurant.manager.RestaurantWebServiceWithDatabase.Services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Food;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Restaurant;
import restaurant.manager.RestaurantWebServiceWithDatabase.Exceptions.NotFoundException;
import restaurant.manager.RestaurantWebServiceWithDatabase.Repositories.FoodRepository;
import restaurant.manager.RestaurantWebServiceWithDatabase.Repositories.RestaurantRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    public void addFoodToOneRestaurant(@NonNull Integer id, @NonNull Food food) {
        Restaurant targetRestaurant = restaurantRepository.findByRestaurantId(id);
        food.setRestaurant(targetRestaurant);
        foodRepository.save(food);
    }

    public void updateOneFoodFromOneRestaurant(@NonNull Integer restaurantId,
                                               @NonNull Integer foodId, @NonNull Food food) {
        Restaurant restaurant = restaurantRepository.findByRestaurantId(restaurantId);
        Food foodToBeUpdated = foodRepository.findByFoodId(foodId);
        if (!restaurant.getFoods().contains(foodToBeUpdated)) {
            throw new RuntimeException("This food can not be updated because the specified restaurant does not serve it");
        }
        foodRepository.update(foodId, food);
    }

    public List<Food> fetchAllFoodsFromOneRestaurantByName(@NonNull String restaurantName) {
        return foodRepository.findByRestaurantName(restaurantName);
    }

    public List<Food> fetchAllFoodsFromOneRestaurantById(@NonNull Integer id, Integer page,
                                                         Integer itemsPerPage, String sortedBy) {
        Pageable pageable = PageRequest.of(page, itemsPerPage, Sort.by(sortedBy));
        return foodRepository.getFoodsByRestaurantId(id, pageable);
    }

    public void removeOneFoodFromOneRestaurant(@NonNull Integer foodId) {
        if (!foodRepository.existsById(foodId)) {
            throw new NotFoundException("No Food was found to be deleted");
        }
        foodRepository.deleteById(foodId);
    }

    public Boolean isUserTheOwnerOfRestaurant(@NonNull String username, @NonNull Integer restaurantId) {
        Restaurant targetRestaurant = restaurantRepository.findByRestaurantId(restaurantId);
        return targetRestaurant.getOwner().getUsername().equals(username);
    }
}
