package restaurant.manager.RestaurantWebServiceWithDatabase.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Restaurant;
import restaurant.manager.RestaurantWebServiceWithDatabase.Services.RestaurantService;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/")
    public ResponseEntity<List<Restaurant>> getRestaurants() {
        return new ResponseEntity<List<Restaurant>>(
                restaurantService.fetchAllRestaurants(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneRestaurant(@PathVariable int id) {
        Restaurant fetchedRestaurant = restaurantService.fetchOneRestaurant(id);
        if (fetchedRestaurant != null) {
            return new ResponseEntity<Restaurant>(
                    fetchedRestaurant, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Such restaurant does not exist", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addOneRestaurant(@RequestBody Restaurant restaurant) {
        restaurantService.addOneRestaurant(restaurant);
        return new ResponseEntity<>("Successfully added the restaurant", HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<String> updateOneRestaurant(@PathVariable int id
            , @RequestBody Restaurant restaurant) {
        restaurantService.updateOneRestaurant(id, restaurant);
        return new ResponseEntity<>("Successfully added the restaurant", HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteOneRestaurant(@NonNull @PathVariable int id) {
        restaurantService.removeOneRestaurant(id);
        return new ResponseEntity<>("Successfully added the restaurant", HttpStatus.OK);
    }
}

