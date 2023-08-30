package restaurant.manager.RestaurantWebServiceWithDatabase.DAOExtensions;

import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Food;

public interface FoodDAO {
    void update(Integer id, Food food);
}
