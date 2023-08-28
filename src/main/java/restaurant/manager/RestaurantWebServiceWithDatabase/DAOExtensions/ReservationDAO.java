package restaurant.manager.RestaurantWebServiceWithDatabase.DAOExtensions;

import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Reservation;

public interface ReservationDAO {
    void update(Integer id, Reservation reservation);
}
