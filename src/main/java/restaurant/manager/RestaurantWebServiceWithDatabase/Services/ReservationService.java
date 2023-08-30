package restaurant.manager.RestaurantWebServiceWithDatabase.Services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Food;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Reservation;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.User;
import restaurant.manager.RestaurantWebServiceWithDatabase.Exceptions.NotFoundException;
import restaurant.manager.RestaurantWebServiceWithDatabase.Repositories.FoodRepository;
import restaurant.manager.RestaurantWebServiceWithDatabase.Repositories.ReservationRepository;
import restaurant.manager.RestaurantWebServiceWithDatabase.Repositories.RestaurantRepository;
import restaurant.manager.RestaurantWebServiceWithDatabase.Repositories.UserRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class ReservationService {

    private FoodRepository foodRepository;
    private RestaurantRepository restaurantRepository;
    private ReservationRepository reservationRepository;
    private UserRepository userRepository;

    public List<Reservation> fetchAllReservations(Integer page, Integer itemsPerPage) {
        Pageable pageable = PageRequest.of(page, itemsPerPage);
        return reservationRepository.findAllReservations(pageable);
    }

    public List<Reservation> fetchAllReservationsOfOneRestaurant(Integer page, Integer itemsPerPage,
                                                                 @NonNull Integer id) {
        Pageable pageable = PageRequest.of(page, itemsPerPage);
        return reservationRepository.getReservationsByRestaurantId(id, pageable);
    }

    public List<Reservation> fetchAllReservationsOfOneUser(Integer page, Integer itemsPerPage,
                                                           @NonNull Integer id) {
        Pageable pageable = PageRequest.of(page, itemsPerPage);
        return reservationRepository.findByUserId(id, pageable);
    }

    public void addReservation(@NonNull Integer userId, @NonNull Integer foodId) {
        Food orderedFood = foodRepository.findByFoodId(foodId);
        User ordererUser = userRepository.findByUserId(userId);
        if (orderedFood == null || ordererUser == null) {
            throw new RuntimeException("Bad Parameters were sent");
        }
        Reservation reservation = new Reservation(orderedFood, ordererUser);
        reservationRepository.save(reservation);
    }

    public void updateReservation(@NonNull Integer id, @NonNull Integer userId,
                                  @NonNull Integer foodId) {
        Food orderedFood = foodRepository.findByFoodId(foodId);
        User ordererUser = userRepository.findByUserId(userId);
        Reservation reservation = new Reservation(orderedFood, ordererUser);
        reservationRepository.update(id, reservation);
    }

    public void removeOneReservation(@NonNull Integer id) {
        if (!reservationRepository.existsById(id)) {
            throw new NotFoundException("No Reservation was found to be deleted");
        }
        reservationRepository.deleteById(id);
    }
}
