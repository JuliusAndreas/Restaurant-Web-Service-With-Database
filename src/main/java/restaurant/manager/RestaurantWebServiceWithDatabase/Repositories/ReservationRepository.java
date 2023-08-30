package restaurant.manager.RestaurantWebServiceWithDatabase.Repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import restaurant.manager.RestaurantWebServiceWithDatabase.DAOExtensions.ReservationDAO;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Food;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Reservation;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.User;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer>, ReservationDAO {

    @Query("SELECT r FROM Reservation r WHERE r.id = :queryId")
    Reservation findByReservationId(@Param("queryId") Integer id);

    @Query("SELECT r FROM Reservation r WHERE r.user = :queryUser")
    List<Reservation> findByUser(@Param("queryUser") User user);

    @Query("SELECT r FROM Reservation r WHERE r.food = :queryFood")
    List<Reservation> findByFood(@Param("queryFood") Food food);

    @Query("SELECT r FROM User u JOIN u.reservations r JOIN r.food f WHERE u.id = :queryId")
    List<Reservation> findByUserId(@Param("queryId") Integer id, Pageable pageable);

    @Query("select r from Reservation r JOIN r.food f")
    List<Reservation> findAllReservations(Pageable pageable);

    @Query("SELECT res FROM Reservation res JOIN Food f JOIN Restaurant r WHERE r.id = :queryId")
    List<Reservation> getReservationsByRestaurantId(@Param("queryId") Integer id, Pageable pageable);


}
