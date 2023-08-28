package restaurant.manager.RestaurantWebServiceWithDatabase.DAOExtensions;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Food;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Reservation;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.User;

public class UserDAOImpl implements UserDAO {

    private EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public void update(Integer id, User user) {
        User userToBeUpdated = entityManager.find(User.class, id);
        userToBeUpdated.setUsername(user.getUsername());
        userToBeUpdated.setPassword(user.getPassword());
        userToBeUpdated.setRestaurants(user.getRestaurants());
        entityManager.merge(userToBeUpdated);

    }
}
