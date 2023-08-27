package restaurant.manager.RestaurantWebServiceWithDatabase.Repositories;

import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Food;

import java.util.List;

public interface FoodDAO {
    void save(Food food);
    Food findById(Integer id);
    List<Food> findAll();
    List<Food> findByName(String name);
    void update(Food food);
    void delete(Integer id);
    Integer deleteAll();
}
