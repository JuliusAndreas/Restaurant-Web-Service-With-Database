package restaurant.manager.RestaurantWebServiceWithDatabase.Repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import restaurant.manager.RestaurantWebServiceWithDatabase.DAOExtensions.RestaurantDAO;
import restaurant.manager.RestaurantWebServiceWithDatabase.DTOs.RestaurantDTO;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Restaurant;
import restaurant.manager.RestaurantWebServiceWithDatabase.Projections.RestaurantView;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>, RestaurantDAO {

    @Query("select r from Restaurant r WHERE r.id = :queryId")
    Restaurant findByRestaurantId(@Param("queryId") Integer id);

    @Query("SELECT r.id as id, r.restaurantName as  from Restaurant r")
    List<Restaurant> findAllRestaurantsWithoutPaging();


    @Query("select r from Restaurant r")
    List<Restaurant> findAllRestaurants(Pageable pageable);

    @Query("SELECT r from Restaurant r JOIN FETCH r.owner")
    List<Restaurant> findAllRestaurantsJoinFetchOwner();


    @Query("SELECT r from Restaurant r LEFT JOIN FETCH r.foods")
    List<Restaurant> findAllRestaurantsJoinFetchFoods();

    @Query("SELECT r from Restaurant r JOIN FETCH r.foods where r.id = :queryId")
    Restaurant findByRestaurantIdJoinFetchFoods(@Param("queryId") Integer id);

    @Query("SELECT r from Restaurant r where r.id = :queryId")
    Restaurant findByRestaurantIdJoinFetchFoodsOwner(@Param("queryId") Integer id);

    @Query(value = "SELECT r.id, r.restaurantname, r.owner_id from reservation res inner join food f on res.food_id = f.id inner join restaurant r on f.restaurant_id = r.id where res.id = :queryId",
            nativeQuery = true)
    Restaurant findByReservationId(@Param("queryId") Integer id);
}
