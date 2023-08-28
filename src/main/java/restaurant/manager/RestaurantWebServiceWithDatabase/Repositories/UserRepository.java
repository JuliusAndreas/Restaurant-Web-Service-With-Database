package restaurant.manager.RestaurantWebServiceWithDatabase.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import restaurant.manager.RestaurantWebServiceWithDatabase.DAOExtensions.UserDAO;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer>, UserDAO {
    @Query("SELECT u FROM User u WHERE u.id = :queryId")
    User findByUserId(@Param("queryId") Integer id);

    @Query("SELECT u FROM User u")
    List<User> findAll();

    @Query("SELECT u FROM User u WHERE u.username = :queryUsername")
    List<User> findByUsername(@Param("queryUsername") String username);
}
