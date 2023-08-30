package restaurant.manager.RestaurantWebServiceWithDatabase.Repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import restaurant.manager.RestaurantWebServiceWithDatabase.DAOExtensions.FoodDAO;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Food;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Restaurant;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Integer>, FoodDAO {
    @Query("SELECT f FROM Food f WHERE f.id = :queryId")
    Food findByFoodId(@Param("queryId") Integer id);

    @Query("SELECT f FROM Food f WHERE f.foodName = :queryName")
    List<Food> findByFoodName(@Param("queryName") String name);

    @Query("SELECT f FROM Food f WHERE f.restaurant = :queryRestaurant")
    List<Food> findByRestaurant(@Param("queryRestaurant") Restaurant restaurant);

    @Query("SELECT f FROM Food f WHERE f.restaurant.id = :queryId")
    List<Food> getFoodsByRestaurantId(@Param("queryId") Integer id, Pageable pageable);

    @Query("SELECT f FROM Restaurant r JOIN r.foods f WHERE r.restaurantName = :queryName")
    List<Food> findByRestaurantName(@Param("queryName") String name);

}
