package restaurant.manager.RestaurantWebServiceWithDatabase.Repositories;

import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Restaurant;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.User;

import java.util.List;

public interface RestaurantDAO {
    void save(Restaurant restaurant);
    Restaurant findById(Integer id);
    Restaurant findByIdJoinFetch(Integer id);
    List<Restaurant> findAll();
    List<Restaurant> findByName(String name);
    void update(Restaurant restaurant);
    void delete(Integer id);
    Integer deleteAll();
}
