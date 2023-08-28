package restaurant.manager.RestaurantWebServiceWithDatabase.DAOExtensions;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Restaurant;

public class RestaurantDAOImpl implements RestaurantDAO {

    private EntityManager entityManager;

    @Autowired
    public RestaurantDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void update(Restaurant restaurant) {
        entityManager.merge(restaurant);
    }
}
