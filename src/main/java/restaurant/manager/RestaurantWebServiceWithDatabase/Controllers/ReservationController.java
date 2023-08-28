package restaurant.manager.RestaurantWebServiceWithDatabase.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Reservation;
import restaurant.manager.RestaurantWebServiceWithDatabase.Services.ReservationService;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.fetchAllReservations();
        return new ResponseEntity<>(
                reservationService.fetchAllReservations(), HttpStatus.OK
        );
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity getAllReservationsPerRestaurant(@PathVariable int id) {
        List<Reservation> fetchedReservations = reservationService.fetchAllReservationsOfOneRestaurant(id);
        if (fetchedReservations != null) {
            return new ResponseEntity<List<Reservation>>(fetchedReservations, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Such restaurant does not exist", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity getAllReservationsPerUser(@PathVariable int id) {
        List<Reservation> userFetchedReservations = reservationService
                .fetchAllReservationsOfOneUser(id);
        if (userFetchedReservations != null) {
            return new ResponseEntity<List<Reservation>>(userFetchedReservations, HttpStatus.OK);
        }
        return new ResponseEntity<String>("invalid user", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addReservation(@RequestBody Reservation reservation) {
        reservationService.addReservation(reservation);
        return new ResponseEntity<>("Successfully added the reservation", HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<String> updateReservation(@PathVariable int id,
                                                    @RequestBody Reservation reservation) {
        reservationService.updateReservation(id, reservation);
        return new ResponseEntity<>("Successfully updated the reservation", HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteReservation(@PathVariable int id) {
        reservationService.removeOneReservation(id);
        return new ResponseEntity<>("Successfully added the reservation", HttpStatus.OK);
    }
}

