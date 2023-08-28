package restaurant.manager.RestaurantWebServiceWithDatabase.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import restaurant.manager.RestaurantWebServiceWithDatabase.DAOExtensions.RestaurantDAO;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Restaurant;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>, RestaurantDAO {

}
