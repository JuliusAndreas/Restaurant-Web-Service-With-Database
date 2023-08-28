package restaurant.manager.RestaurantWebServiceWithDatabase.DAOExtensions;

import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Restaurant;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.User;

import java.util.List;

public interface RestaurantDAO {
    void update(Integer id, Restaurant restaurant);
}
