package restaurant.manager.RestaurantWebServiceWithDatabase.DAOExtensions;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.User;
import restaurant.manager.RestaurantWebServiceWithDatabase.Exceptions.NotFoundException;

public class UserDAOImpl implements UserDAO {

    private EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public void update(Integer id, User user) {
        User userToBeUpdated = entityManager.find(User.class, id);
        if (userToBeUpdated == null) {
            throw new NotFoundException("Such food does not exist");
        }
        if ((user.getRestaurants() != null) &&
                (!user.getRestaurants().equals(userToBeUpdated.getRestaurants()))) {
            throw new RuntimeException("Can't change the restaurants");
        }
        if (user.equals(userToBeUpdated)) {
            throw new RuntimeException("Updating with same credentials is not allowed");
        }
        userToBeUpdated.setUsername(user.getUsername());
        userToBeUpdated.setPassword(user.getPassword());
        entityManager.merge(userToBeUpdated);
    }
}
