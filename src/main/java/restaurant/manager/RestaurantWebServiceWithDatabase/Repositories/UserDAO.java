package restaurant.manager.RestaurantWebServiceWithDatabase.Repositories;

import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.User;

import java.util.List;

public interface UserDAO {
    void save(User user);
    User findById(Integer id);
    List<User> findAll();
    List<User> findByUsername(String lastName);
    void update(User user);
    void delete(Integer id);
    Integer deleteAll();
}
