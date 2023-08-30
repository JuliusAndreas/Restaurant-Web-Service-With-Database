package restaurant.manager.RestaurantWebServiceWithDatabase.Repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import restaurant.manager.RestaurantWebServiceWithDatabase.DAOExtensions.UserDAO;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Food;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer>, UserDAO {

    @Query("SELECT u FROM User u")
    List<User> findAllUsers(Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.id = :queryId")
    User findByUserId(@Param("queryId") Integer id);

    @Query("SELECT u FROM User u WHERE u.username = :queryUsername")
    List<User> findByUsername(@Param("queryUsername") String username);

    @Query("SELECT u FROM User u JOIN Reservation res WHERE res.food = :queryFood")
    List<User> findUsersByFood(@Param("queryFood") Food food);

    @Query("SELECT f FROM User u JOIN Reservation res JOIN Food f WHERE u.id = :queryId")
    List<Food> findFoodsByUserId(@Param("queryId") Integer id);
}
