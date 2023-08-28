package restaurant.manager.RestaurantWebServiceWithDatabase.DAOExtensions;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Food;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Reservation;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Restaurant;

public class RestaurantDAOImpl implements RestaurantDAO {

    private EntityManager entityManager;

    @Autowired
    public RestaurantDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void update(Integer id, Restaurant restaurant) {
        Restaurant restaurantToBeUpdated = entityManager.find(Restaurant.class, id);
        restaurantToBeUpdated.setRestaurantName(restaurant.getRestaurantName());
        restaurantToBeUpdated.setOwner(restaurant.getOwner());
        restaurantToBeUpdated.setFoods(restaurant.getFoods());
        entityManager.merge(restaurantToBeUpdated);
    }
}
