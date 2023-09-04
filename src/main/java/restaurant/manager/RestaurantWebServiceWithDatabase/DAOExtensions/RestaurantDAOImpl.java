package restaurant.manager.RestaurantWebServiceWithDatabase.DAOExtensions;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Restaurant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantDAOImpl implements RestaurantDAO {

    private EntityManager entityManager;

    @Autowired
    public RestaurantDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void update(Integer id, Restaurant restaurant) {
        Restaurant restaurantToBeUpdated = entityManager.find(Restaurant.class, id);
        restaurantToBeUpdated.setRestaurantName(restaurant.getRestaurantName());
        if (restaurant.getOwner() != null) restaurantToBeUpdated.setOwner(restaurant.getOwner());
        entityManager.merge(restaurantToBeUpdated);
    }

    @Override
    public List<Restaurant> findAllByEntityGraph() {
        EntityGraph entityGraph = entityManager.getEntityGraph("restaurant-graph");
        Map<String, Object> properties = new HashMap<>();
        return entityManager.createQuery("select r from Restaurant r", Restaurant.class)
                .setHint("jakarta.persistence.fetchgraph", entityGraph)
                .getResultList();
    }
}
