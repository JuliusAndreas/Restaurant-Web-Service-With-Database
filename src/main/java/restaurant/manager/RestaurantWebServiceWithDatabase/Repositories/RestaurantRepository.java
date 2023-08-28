package restaurant.manager.RestaurantWebServiceWithDatabase.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import restaurant.manager.RestaurantWebServiceWithDatabase.DAOExtensions.RestaurantDAO;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Food;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Reservation;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Restaurant;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>, RestaurantDAO {

    @Query("select r from Restaurant r WHERE r.id = :queryId")
    Restaurant findByRestaurantId(@Param("queryId") Integer id);

    @Query("SELECT r from Restaurant r JOIN FETCH r.foods where r.id = :queryId")
    Restaurant findByRestaurantIdJoinFetchFoods(@Param("queryId") Integer id);

    @Query("SELECT r from Restaurant r JOIN FETCH r.foods JOIN FETCH r.owner where r.id = :queryId")
    Restaurant findByRestaurantIdJoinFetchFoodsOwner(@Param("queryId") Integer id);

    @Query("SELECT f FROM Restaurant r JOIN r.foods f WHERE r.id = :queryId")
    List<Food> getFoodsByRestaurantId(@Param("queryId") Integer id);

    @Query("SELECT res FROM Restaurant r JOIN r.foods f JOIN f.reservations res WHERE r.id = :queryId")
    List<Reservation> getReservationsByRestaurantId(@Param("queryId") Integer id);

}
