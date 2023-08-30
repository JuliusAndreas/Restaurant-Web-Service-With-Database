package restaurant.manager.RestaurantWebServiceWithDatabase.Controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Restaurant;
import restaurant.manager.RestaurantWebServiceWithDatabase.Exceptions.NotFoundException;
import restaurant.manager.RestaurantWebServiceWithDatabase.Services.RestaurantService;
import restaurant.manager.RestaurantWebServiceWithDatabase.Utilities.ErrorResponse;
import restaurant.manager.RestaurantWebServiceWithDatabase.Utilities.OkResponse;
import restaurant.manager.RestaurantWebServiceWithDatabase.Utilities.Views;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @JsonView(Views.Public.class)
    @GetMapping("/")
    public List<Restaurant> getRestaurants(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "5") int itemsPerPage,
                                           @RequestParam(defaultValue = "restaurantName") String sortedBy) {
        List<Restaurant> responseList = restaurantService.fetchAllRestaurants(page, itemsPerPage, sortedBy);
        if (responseList.isEmpty()) {
            throw new NotFoundException("No Restaurant was found");
        }
        return responseList;
    }

    @JsonView(Views.Public.class)
    @GetMapping("/{id}")
    public Restaurant getOneRestaurant(@PathVariable int id) {
        Restaurant fetchedRestaurant = restaurantService.fetchOneRestaurant(id);
        if (fetchedRestaurant == null){
            throw new NotFoundException("No Restaurant was found");
        }
        return fetchedRestaurant;
    }

    @PostMapping("/")
    public ResponseEntity addOneRestaurant(@RequestBody Restaurant restaurant,
                                           @RequestParam Integer ownerId) {
        restaurantService.addOneRestaurant(restaurant, ownerId);
        return new ResponseEntity<>(new OkResponse("Restaurant successfully added"), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateOneRestaurant(@PathVariable int id
            , @RequestBody Restaurant restaurant) {
        restaurantService.updateOneRestaurant(id, restaurant);
        return new ResponseEntity<>(new OkResponse("Restaurant successfully updated"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOneRestaurant(@PathVariable int id) {
        restaurantService.removeOneRestaurant(id);
        return new ResponseEntity<>(new OkResponse("Restaurant succesfully deleted"), HttpStatus.OK);
    }
}