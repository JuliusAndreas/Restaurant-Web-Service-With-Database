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
import restaurant.manager.RestaurantWebServiceWithDatabase.Utilities.OrderStatus;
import restaurant.manager.RestaurantWebServiceWithDatabase.Utilities.Views;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @JsonView(value = Views.Public.class)
    @GetMapping("/")
    public List<Reservation> getAllReservations(@RequestParam(defaultValue = "0") Integer page,
                                                @RequestParam(defaultValue = "5") Integer itemsPerPage) {
        List<Reservation> fetchedReservations = reservationService.fetchAllReservations(page, itemsPerPage);
        if (fetchedReservations.isEmpty()) {
            throw new NotFoundException("No Reservation was found");
        }
        return fetchedReservations;
    }

    @JsonView(value = Views.Public.class)
    @GetMapping("/restaurant/{id}")
    public List<Reservation> getAllReservationsPerRestaurant(@RequestParam(defaultValue = "0") Integer page,
                                                             @RequestParam(defaultValue = "5") Integer itemsPerPage,
                                                             @PathVariable int id) {
        List<Reservation> fetchedReservations = reservationService.fetchAllReservationsOfOneRestaurant(page, itemsPerPage, id);
        if (fetchedReservations.isEmpty()) {
            throw new NotFoundException("No Reservation was found");
        }
        return fetchedReservations;
    }

    @JsonView(value = Views.Public.class)
    @GetMapping("/user/{id}")
    public List<Reservation> getAllReservationsPerUser(@RequestParam(defaultValue = "0") Integer page,
                                                       @RequestParam(defaultValue = "5") Integer itemsPerPage,
                                                       @PathVariable int id) {
        List<Reservation> userFetchedReservations = reservationService
                .fetchAllReservationsOfOneUser(page, itemsPerPage, id);
        if (userFetchedReservations.isEmpty()) {
            throw new NotFoundException("No Reservation was found");
        }
        return userFetchedReservations;
    }

    @JsonView(value = Views.Public.class)
    @GetMapping("/{id}")
    public Reservation getOneReservation(@PathVariable int id){
        Reservation fetchedReservation = reservationService.fetchOneReservation(id);
        if (fetchedReservation == null) {
            throw new NotFoundException("No Reservation was found");
        }
        return fetchedReservation;
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

    @PatchMapping("/{restaurantId}/{id}")
    public ResponseEntity completeOrder(@PathVariable int restaurantId,
                                        @PathVariable int id){
        reservationService.setStatusToCompleted(id);
        return new ResponseEntity<>(new OkResponse("Reservation successfully completed"), HttpStatus.OK);
    }
}

