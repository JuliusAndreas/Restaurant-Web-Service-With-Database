package restaurant.manager.RestaurantWebServiceWithDatabase.Controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Reservation;
import restaurant.manager.RestaurantWebServiceWithDatabase.Exceptions.NotFoundException;
import restaurant.manager.RestaurantWebServiceWithDatabase.Services.ReservationService;
import restaurant.manager.RestaurantWebServiceWithDatabase.Utilities.OkResponse;
import restaurant.manager.RestaurantWebServiceWithDatabase.Utilities.Views;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @JsonView(value = Views.Public.class)
    @GetMapping("/")
    public List<Reservation> getAllReservations() {
        List<Reservation> fetchedReservations = reservationService.fetchAllReservations();
        if (fetchedReservations.isEmpty()) {
            throw new NotFoundException("No Reservation was found");
        }
        return fetchedReservations;
    }

    @JsonView(value = Views.Public.class)
    @GetMapping("/restaurant/{id}")
    public List<Reservation> getAllReservationsPerRestaurant(@PathVariable int id) {
        List<Reservation> fetchedReservations = reservationService.fetchAllReservationsOfOneRestaurant(id);
        if (fetchedReservations.isEmpty()) {
            throw new NotFoundException("No Reservation was found");
        }
        return fetchedReservations;
    }

    @JsonView(value = Views.Public.class)
    @GetMapping("/user/{id}")
    public List<Reservation> getAllReservationsPerUser(@PathVariable int id) {
        List<Reservation> userFetchedReservations = reservationService
                .fetchAllReservationsOfOneUser(id);
        if (userFetchedReservations.isEmpty()) {
            throw new NotFoundException("No Reservation was found");
        }
        return userFetchedReservations;
    }

    @PostMapping("/")
    public ResponseEntity addReservation(@RequestParam Integer userId,
                                         @RequestParam Integer foodId) {
        reservationService.addReservation(userId, foodId);
        return new ResponseEntity<>(new OkResponse("Reservation successfully added"), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateReservation(@PathVariable int id,
                                            @RequestParam Integer userId,
                                            @RequestParam Integer foodId) {
        reservationService.updateReservation(id, userId, foodId);
        return new ResponseEntity<>(new OkResponse("Reservation successfully updated"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteReservation(@PathVariable int id) {
        reservationService.removeOneReservation(id);
        return new ResponseEntity<>(new OkResponse("Reservation successfully added"), HttpStatus.OK);
    }
}

