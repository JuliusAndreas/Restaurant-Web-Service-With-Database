package restaurant.manager.RestaurantWebServiceWithDatabase.Repositories;

import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Reservation;

import java.util.List;

public interface ReservationDAO {
    void save(Reservation reservation);
    Reservation findById(Integer id);
    List<Reservation> findAll();
    List<Reservation> findByUserId(Integer userID);
    List<Reservation> findByFoodId(Integer foodID);
    void update(Reservation reservation);
    void delete(Integer id);
    Integer deleteAll();
}
