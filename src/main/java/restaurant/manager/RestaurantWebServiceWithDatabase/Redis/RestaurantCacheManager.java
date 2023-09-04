//package restaurant.manager.RestaurantWebServiceWithDatabase.Redis;
//
//import jakarta.annotation.PostConstruct;
//import jakarta.transaction.Transactional;
//import lombok.Getter;
//import lombok.Setter;
//import org.redisson.api.RList;
//import org.redisson.api.RedissonClient;
//import org.springframework.stereotype.Component;
//import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Restaurant;
//import restaurant.manager.RestaurantWebServiceWithDatabase.Repositories.RestaurantRepository;
//
//import java.util.List;
//
//
//@Component
//@Setter
//@Getter
//public class RestaurantCacheManager {
//
//    private RedissonClient client;
//
//    private RestaurantRepository restaurantRepository;
//
//    private RList<RestaurantDTO> restaurants;
//
//    public RestaurantCacheManager(RedissonClient client, RestaurantRepository restaurantRepository) {
//        this.client = client;
//        this.restaurantRepository = restaurantRepository;
//    }
//
//    @Transactional
//    @PostConstruct
//    private void postConstruct() {
//        this.restaurants = client.getList(CacheConfig.GLOBAL_RESTAURANTS_NAME);
//        this.restaurants.clear();
//        List<RestaurantDTO> fetchedRestaurantFromDatabase = restaurantRepository.findAllRestaurantsWithoutPaging()
//        restaurants.addAll(fetchedRestaurantFromDatabase);
//    }
//
//    @Transactional
//    public void addToCache(Restaurant restaurant) {
//        this.restaurants.add();
//    }
//
//    @Transactional
//    public void updateCache(Restaurant restaurant) {
//
//    }
//}
