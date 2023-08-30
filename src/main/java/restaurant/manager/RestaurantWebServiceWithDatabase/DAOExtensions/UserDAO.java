package restaurant.manager.RestaurantWebServiceWithDatabase.DAOExtensions;

import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.User;

public interface UserDAO {
    public void update(Integer id, User user);
}
