package restaurant.manager.RestaurantWebServiceWithDatabase.Services;

import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Reservation;
import restaurant.manager.RestaurantWebServiceWithDatabase.Repositories.ReservationRepository;
import restaurant.manager.RestaurantWebServiceWithDatabase.Repositories.RestaurantRepository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ReservationService {

    private RestaurantRepository restaurantRepository;
    private ReservationRepository reservationRepository;

    public List<Reservation> fetchAllReservations() {
        return reservationRepository.findAll();
    }

    public List<Reservation> fetchAllReservationsOfOneRestaurant(@NonNull Integer id) {
        return restaurantRepository.getReservationsByRestaurantId(id);
    }

    public List<Reservation> fetchAllReservationsOfOneUser(@NonNull Integer id) {
        return reservationRepository.findByUserId(id);
    }

    public void addReservation(@NonNull Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public void updateReservation(@NonNull Integer id, @NonNull Reservation reservation) {
        reservationRepository.update(id, reservation);
    }

    public void removeOneReservation(@NonNull Integer id) {
        reservationRepository.deleteById(id);
    }
}
