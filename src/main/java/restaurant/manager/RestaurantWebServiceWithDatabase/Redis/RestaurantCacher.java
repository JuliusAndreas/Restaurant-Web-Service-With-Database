package restaurant.manager.RestaurantWebServiceWithDatabase.Redis;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Restaurant;
import restaurant.manager.RestaurantWebServiceWithDatabase.Services.RestaurantService;

import java.util.List;

@Component
@Setter
@Getter
public class RestaurantCacher {

    @Autowired
    private RedissonClient client;

    @Autowired
    private RestaurantService restaurantService;

    private RList<Restaurant> restaurants;

    public RestaurantCacher(RedissonClient client, RestaurantService restaurantService) {
        this.client = client;
        this.restaurantService = restaurantService;
    }

    @Transactional
    @PostConstruct
    private void postConstruct() {
        this.restaurants = client.getList(CacheConfig.GLOBAL_RESTAURANTS_NAME);
        this.restaurants.clear();
        List<Restaurant> fetchedRestaurantFromDatabase = restaurantService.
                fetchAllRestaurantsWithoutPaging();
        for (Restaurant restaurant : fetchedRestaurantFromDatabase) {
            Restaurant tempRestaurant = new Restaurant(restaurant.getRestaurantName());
            this.restaurants.add(tempRestaurant);
        }
    }
}
