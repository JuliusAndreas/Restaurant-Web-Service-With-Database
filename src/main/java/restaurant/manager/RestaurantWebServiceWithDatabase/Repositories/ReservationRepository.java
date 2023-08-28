package restaurant.manager.RestaurantWebServiceWithDatabase.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import restaurant.manager.RestaurantWebServiceWithDatabase.DAOExtensions.ReservationDAO;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer>, ReservationDAO {

}
