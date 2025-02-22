package restaurant.manager.RestaurantWebServiceWithDatabase.Redis;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.stereotype.Component;
import restaurant.manager.RestaurantWebServiceWithDatabase.DTOs.RestaurantDTO;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Restaurant;
import restaurant.manager.RestaurantWebServiceWithDatabase.Mappers.RestaurantMapper;
import restaurant.manager.RestaurantWebServiceWithDatabase.Repositories.RestaurantRepository;

import java.util.List;


@Component
@Setter
@Getter
@RequiredArgsConstructor
public class RestaurantCacheManager {

    @NonNull
    private final RedissonClient client;

    @NonNull
    private final RestaurantRepository restaurantRepository;

    @NonNull
    private final RestaurantMapper restaurantMapper;

    private RList<RestaurantDTO> restaurants;

    @PostConstruct
    private void postConstruct() {
        restaurants = client.getList(CacheConfig.GLOBAL_RESTAURANTS_CACHE_NAME,
                new TypedJsonJacksonCodec(RestaurantDTO.class));
    }

    public List<RestaurantDTO> getRestaurantsCached() {
        return restaurants;
    }

    public RestaurantDTO getRestaurantByIdCached(Integer id) {
        for (int i = 0; i < restaurants.size(); i++) {
            if (restaurants.get(i).getId().equals(id)) {
                return restaurants.get(i);
            }
        }
        return null;
    }

    @Transactional(rollbackOn = {Exception.class})
    public void addRestaurantCached(Restaurant restaurant) {
        restaurants.add(restaurantMapper.toDTO(restaurant));
    }

    @Transactional(rollbackOn = {Exception.class})
    public void addRestaurantCached(RestaurantDTO restaurantDTO) {
        restaurants.add(restaurantDTO);
    }

    @Transactional(rollbackOn = {Exception.class})
    public Boolean updateRestaurantCached(Integer id, Restaurant restaurant) {
        RestaurantDTO restaurantDTO = restaurantMapper.toDTO(restaurant);
        for (int i = 0; i < restaurants.size(); i++) {
            if (restaurants.get(i).getId().equals(id)) {
                restaurantDTO.setId(restaurants.get(i).getId());
                restaurants.set(i, restaurantDTO);
                return true;
            }
        }
        return false;
    }

    @Transactional(rollbackOn = {Exception.class})
    public Boolean updateRestaurantCached(Integer id, RestaurantDTO restaurantDTO) {
        for (int i = 0; i < restaurants.size(); i++) {
            if (restaurants.get(i).getId().equals(id)) {
                restaurantDTO.setId(restaurants.get(i).getId());
                restaurants.set(i, restaurantDTO);
                return true;
            }
        }
        return false;
    }

    @Transactional(rollbackOn = {Exception.class})
    public Boolean deleteRestaurantCached(Integer id) {
        for (int i = 0; i < restaurants.size(); i++) {
            if (restaurants.get(i).getId().equals(id)) {
                restaurants.remove(i);
                return true;
            }
        }
        return false;
    }

    public void fillTheCache() {
        restaurants.addAll(restaurantMapper.
                toDTO(restaurantRepository.findAllRestaurantsWithoutPaging()));
    }

    public void flushCache() {
        restaurants.clear();
    }
}
