package restaurant.manager.RestaurantWebServiceWithDatabase.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Food;

public interface FoodRepository extends JpaRepository<Food, Integer> {

}
