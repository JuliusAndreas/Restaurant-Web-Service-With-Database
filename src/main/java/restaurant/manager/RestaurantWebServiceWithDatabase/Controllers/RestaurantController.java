package restaurant.manager.RestaurantWebServiceWithDatabase.Controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant.manager.RestaurantWebServiceWithDatabase.DTOs.RestaurantDTO;
import restaurant.manager.RestaurantWebServiceWithDatabase.Exceptions.NotFoundException;
import restaurant.manager.RestaurantWebServiceWithDatabase.Services.RestaurantService;
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
    public List<RestaurantDTO> getRestaurants(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "5") int itemsPerPage,
                                              @RequestParam(defaultValue = "restaurantName") String sortedBy) {
        return restaurantService.fetchAllRestaurants(page, itemsPerPage, sortedBy);
    }

    @JsonView(Views.Public.class)
    @GetMapping("/{id}")
    public RestaurantDTO getOneRestaurant(@PathVariable int id) {
        RestaurantDTO fetchedRestaurant = restaurantService.fetchOneRestaurant(id);
        if (fetchedRestaurant == null) {
            throw new NotFoundException("No Restaurant was found");
        }
        return fetchedRestaurant;
    }

    @PostMapping("/")
    public ResponseEntity addOneRestaurant(@RequestBody RestaurantDTO restaurantDTO,
                                           @RequestParam Integer ownerId) {
        restaurantService.addOneRestaurant(restaurantDTO, ownerId);
        return new ResponseEntity<>(new OkResponse("Restaurant successfully added"), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateOneRestaurant(@PathVariable int id,
                                              @RequestBody RestaurantDTO restaurantDTO) {
        restaurantService.updateOneRestaurant(id, restaurantDTO);
        return new ResponseEntity<>(new OkResponse("Restaurant successfully updated"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOneRestaurant(@PathVariable int id) {
        restaurantService.removeOneRestaurant(id);
        return new ResponseEntity<>(new OkResponse("Restaurant succesfully deleted"), HttpStatus.OK);
    }
}