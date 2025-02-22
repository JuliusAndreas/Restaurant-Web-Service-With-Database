package restaurant.manager.RestaurantWebServiceWithDatabase.Services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import restaurant.manager.RestaurantWebServiceWithDatabase.DTOs.ReservationDTO;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Food;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Reservation;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Restaurant;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.User;
import restaurant.manager.RestaurantWebServiceWithDatabase.Exceptions.NotFoundException;
import restaurant.manager.RestaurantWebServiceWithDatabase.Repositories.FoodRepository;
import restaurant.manager.RestaurantWebServiceWithDatabase.Repositories.ReservationRepository;
import restaurant.manager.RestaurantWebServiceWithDatabase.Repositories.RestaurantRepository;
import restaurant.manager.RestaurantWebServiceWithDatabase.Repositories.UserRepository;
import restaurant.manager.RestaurantWebServiceWithDatabase.Utilities.OrderStatus;

import java.util.List;

@AllArgsConstructor
@Service
public class ReservationService {

    private FoodRepository foodRepository;
    private ReservationRepository reservationRepository;
    private UserRepository userRepository;
    private RestaurantRepository restaurantRepository;

    public List<Reservation> fetchAllReservations(Integer page, Integer itemsPerPage) {
        Pageable pageable = PageRequest.of(page, itemsPerPage);
        return reservationRepository.findAllReservations(pageable);
    }

    public Reservation fetchOneReservation(Integer id) {
        return reservationRepository.findByReservationId(id);
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

    public void addReservation(@NonNull ReservationDTO reservationDTO) {
        Integer foodId = reservationDTO.getFoodId();
        Integer userId = reservationDTO.getUserId();
        Integer quantity = reservationDTO.getQuantity();
        Food orderedFood = foodRepository.findByFoodId(foodId);
        User ordererUser = userRepository.findByUserId(userId);
        if (orderedFood == null || ordererUser == null || quantity == null) {
            throw new RuntimeException("Bad Parameters were sent");
        }
        Reservation reservation = new Reservation(orderedFood, ordererUser, OrderStatus.ONGOING, quantity);
        reservationRepository.save(reservation);
    }

    public void updateReservation(@NonNull Integer id, @NonNull ReservationDTO reservationDTO) {
        Integer foodId = reservationDTO.getFoodId();
        Integer userId = reservationDTO.getUserId();
        Integer quantity = reservationDTO.getQuantity();
        Food orderedFood = foodRepository.findByFoodId(foodId);
        User ordererUser = userRepository.findByUserId(userId);
        if (orderedFood == null || ordererUser == null || quantity == null) {
            throw new RuntimeException("Bad Parameters were sent");
        }
        Reservation reservation = new Reservation(orderedFood, ordererUser, OrderStatus.ONGOING, quantity);
        reservationRepository.update(id, reservation);
    }

    public void removeOneReservation(@NonNull Integer id) {
        if (!reservationRepository.existsById(id)) {
            throw new NotFoundException("No Reservation was found to be deleted");
        }
        reservationRepository.deleteById(id);
    }

    public void setStatusToCompleted(@NonNull Integer id) {
        if (!reservationRepository.existsById(id)) {
            throw new NotFoundException("No Reservation was found to be completed");
        }
        Reservation reservation = reservationRepository.findByReservationId(id);
        if (reservation.getStatus() == OrderStatus.DONE) {
            throw new RuntimeException("This reservation is already completed");
        }
        reservation.setStatus(OrderStatus.DONE);
        reservationRepository.save(reservation);
    }

    public Boolean isUserTheOwnerOfRestaurant(@NonNull String username, @NonNull Integer restaurantId) {
        Restaurant targetRestaurant = restaurantRepository.findByRestaurantId(restaurantId);
        return targetRestaurant.getOwner().getUsername().equals(username);
    }

    public Boolean isUserTheOwnerOfTheOrderedFoodRestaurant(@NonNull String username,
                                                            @NonNull Integer reservationId) {
        Restaurant targetRestaurant = restaurantRepository.findByReservationId(reservationId);
        return targetRestaurant.getOwner().getUsername().equals(username);
    }

    public Boolean isUserGivingHisOwnId(@NonNull String username, @NonNull Integer userId) {
        User user = userRepository.findByUserId(userId);
        return user.getUsername().equals(username);
    }

    public Boolean doesReservationBelongToTheRestaurant(@NonNull Integer restaurantId,
                                                        @NonNull Integer id) {
        Reservation reservation = reservationRepository.findByReservationId(id);
        Restaurant restaurant = restaurantRepository.findByRestaurantId(restaurantId);
        for (Food food : restaurant.getFoods()) {
            if (food.getReservations().contains(reservation)) return true;
        }
        return false;
    }
}
