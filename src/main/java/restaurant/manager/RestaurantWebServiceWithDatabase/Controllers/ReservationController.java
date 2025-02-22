package restaurant.manager.RestaurantWebServiceWithDatabase.Controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant.manager.RestaurantWebServiceWithDatabase.DTOs.ReservationDTO;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Reservation;
import restaurant.manager.RestaurantWebServiceWithDatabase.Exceptions.ForbiddenException;
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
                                                             @PathVariable Integer id,
                                                             @RequestAttribute("client-username") String clientUsername) {
        if (!reservationService.isUserTheOwnerOfRestaurant(clientUsername, id)) {
            throw new ForbiddenException("The user is not the owner of this restaurant so he/she can not see all the reservation made for this restaurant.");
        }
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
                                                       @PathVariable Integer id,
                                                       @RequestAttribute("client-username") String clientUsername) {
        if (!reservationService.isUserGivingHisOwnId(clientUsername, id)) {
            throw new ForbiddenException("Access Denied");
        }
        List<Reservation> userFetchedReservations = reservationService
                .fetchAllReservationsOfOneUser(page, itemsPerPage, id);
        if (userFetchedReservations.isEmpty()) {
            throw new NotFoundException("No Reservation was found");
        }
        return userFetchedReservations;
    }

    @JsonView(value = Views.Public.class)
    @GetMapping("/{id}")
    public Reservation getOneReservation(@PathVariable Integer id,
                                         @RequestAttribute("client-username") String clientUsername) {
        if (!reservationService.isUserTheOwnerOfTheOrderedFoodRestaurant(clientUsername, id)) {
            throw new ForbiddenException("The user is not the owner of this restaurant so he/she can not access the reservations of the restaurant.");
        }
        Reservation fetchedReservation = reservationService.fetchOneReservation(id);
        if (fetchedReservation == null) {
            throw new NotFoundException("No Reservation was found");
        }
        return fetchedReservation;
    }

    @PostMapping("/")
    public ResponseEntity addReservation(@RequestBody ReservationDTO reservationDTO,
                                         @RequestAttribute("client-username") String clientUsername) {
        if (reservationDTO.getUserId() == null ||
                !reservationService.isUserGivingHisOwnId(clientUsername, reservationDTO.getUserId())) {
            throw new ForbiddenException("Access Denied");
        }
        reservationService.addReservation(reservationDTO);
        return new ResponseEntity<>(new OkResponse("Reservation successfully added"), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateReservation(@PathVariable Integer id,
                                            @RequestBody ReservationDTO reservationDTO,
                                            @RequestAttribute("client-username") String clientUsername) {
        if (!reservationService.isUserTheOwnerOfTheOrderedFoodRestaurant(clientUsername, id)) {
            throw new ForbiddenException("The user is not the owner of this restaurant so he/she can not edit the reservations.");
        }
        reservationService.updateReservation(id, reservationDTO);
        return new ResponseEntity<>(new OkResponse("Reservation successfully updated"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteReservation(@PathVariable Integer id,
                                            @RequestAttribute("client-username") String clientUsername) {
        if (!reservationService.isUserTheOwnerOfRestaurant(clientUsername, id)) {
            throw new ForbiddenException("The user is not the owner of this restaurant so he/she can not delete any reservations from it.");
        }
        reservationService.removeOneReservation(id);
        return new ResponseEntity<>(new OkResponse("Reservation successfully removed"), HttpStatus.OK);
    }

    @PatchMapping("/{restaurantId}/{id}")
    public ResponseEntity completeOrder(@PathVariable Integer restaurantId,
                                        @PathVariable Integer id,
                                        @RequestAttribute("client-username") String clientUsername) {
        if (!reservationService.isUserTheOwnerOfRestaurant(clientUsername, restaurantId)
                || !reservationService.doesReservationBelongToTheRestaurant(restaurantId, id)) {
            throw new ForbiddenException("Access denied");
        }
        reservationService.setStatusToCompleted(id);
        return new ResponseEntity<>(new OkResponse("Reservation successfully completed"), HttpStatus.OK);
    }
}