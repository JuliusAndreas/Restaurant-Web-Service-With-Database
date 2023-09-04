package restaurant.manager.RestaurantWebServiceWithDatabase.Services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import restaurant.manager.RestaurantWebServiceWithDatabase.DTOs.RestaurantDTO;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Restaurant;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.User;
import restaurant.manager.RestaurantWebServiceWithDatabase.Exceptions.NotFoundException;
import restaurant.manager.RestaurantWebServiceWithDatabase.Mappers.RestaurantMapper;
import restaurant.manager.RestaurantWebServiceWithDatabase.Repositories.RestaurantRepository;
import restaurant.manager.RestaurantWebServiceWithDatabase.Repositories.UserRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class RestaurantService {

    private RestaurantRepository restaurantRepository;
    private UserRepository userRepository;
    private RestaurantMapper restaurantMapper;
//    private RestaurantCacheManager restaurantCacheManager;

    public List<RestaurantDTO> fetchAllRestaurantsWithoutPaging() {
        List<RestaurantDTO> responseList = restaurantMapper.toDTO(restaurantRepository.findAllRestaurantsWithoutPaging());
        if (responseList.isEmpty()) {
            throw new NotFoundException("No Restaurant was found");
        }
        return responseList;
    }

    public List<RestaurantDTO> fetchAllRestaurants(Integer page, Integer itemsPerPage, String sortedBy) {
        Pageable pageable = PageRequest.of(page, itemsPerPage, Sort.by(sortedBy));
        List<RestaurantDTO> responseList = restaurantMapper.toDTO(restaurantRepository.findAllRestaurants(pageable));
        if (responseList.isEmpty()) {
            throw new NotFoundException("No Restaurant was found");
        }
        return responseList;
    }

    public RestaurantDTO fetchOneRestaurant(@NonNull Integer id) {
        return restaurantMapper.toDTO(restaurantRepository.findByRestaurantId(id));
    }

    public void addOneRestaurant(@NonNull RestaurantDTO restaurantDTO, @NonNull Integer ownerId) {
        Restaurant restaurant = restaurantMapper.fromDTO(restaurantDTO);
        User owner = userRepository.findByUserId(ownerId);
        restaurant.setOwner(owner);
        restaurantRepository.save(restaurant);
    }

    public void updateOneRestaurant(@NonNull Integer id, @NonNull RestaurantDTO restaurantDTO) {
        Restaurant restaurant = restaurantMapper.fromDTO(restaurantDTO);
        restaurantRepository.update(id, restaurant);
    }

    public void removeOneRestaurant(@NonNull Integer id) {
        if (!restaurantRepository.existsById(id)) {
            throw new NotFoundException("No Restaurant was found to be deleted");
        }
        restaurantRepository.deleteById(id);
    }

    public List<RestaurantDTO> findAllByEntityGraph() {
        return restaurantMapper.toDTO(restaurantRepository.findAllByEntityGraph());
    }
}
