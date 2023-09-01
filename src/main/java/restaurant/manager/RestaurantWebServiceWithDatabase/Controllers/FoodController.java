package restaurant.manager.RestaurantWebServiceWithDatabase.Controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Food;
import restaurant.manager.RestaurantWebServiceWithDatabase.Exceptions.ForbiddenException;
import restaurant.manager.RestaurantWebServiceWithDatabase.Exceptions.NotFoundException;
import restaurant.manager.RestaurantWebServiceWithDatabase.Services.FoodService;
import restaurant.manager.RestaurantWebServiceWithDatabase.Utilities.OkResponse;
import restaurant.manager.RestaurantWebServiceWithDatabase.Utilities.Views;

import java.util.List;

@RestController
@RequestMapping("/restaurants/foods")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @JsonView(value = Views.Public.class)
    @GetMapping("/{restaurantId}")
    public List<Food> getAllFoodsFromOneRestaurant(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "5") int itemsPerPage,
                                                   @RequestParam(defaultValue = "foodName") String sortedBy,
                                                   @PathVariable int restaurantId) {
        List<Food> responseList = foodService.fetchAllFoodsFromOneRestaurantById(restaurantId,
                page, itemsPerPage, sortedBy);
        if (responseList.isEmpty()) {
            throw new NotFoundException("No Food was found");
        }
        return responseList;
    }

    @PostMapping("/{restaurantId}")
    public ResponseEntity addFoodToOneRestaurant(@PathVariable Integer restaurantId,
                                                 @RequestBody Food food,
                                                 @RequestAttribute("client-username") String clientUsername) {
        if (!foodService.isUserTheOwnerOfRestaurant(clientUsername, restaurantId)) {
            throw new ForbiddenException("The user is not the owner of this restaurant so he/she can not add any foods to it.");
        }
        foodService.addFoodToOneRestaurant(restaurantId, food);
        return new ResponseEntity<>(new OkResponse("Food successfully added"), HttpStatus.OK);
    }

    @PutMapping("/{restaurantId}/{foodId}")
    public ResponseEntity updateFoodFromOneRestaurant(@PathVariable int restaurantId,
                                                      @PathVariable int foodId,
                                                      @RequestBody Food food,
                                                      @RequestAttribute("client-username") String clientUsername) {
        if (!foodService.isUserTheOwnerOfRestaurant(clientUsername, restaurantId)) {
            throw new ForbiddenException("The user is not the owner of this restaurant so he/she can not add any foods to it.");
        }
        foodService.updateOneFoodFromOneRestaurant(restaurantId, foodId, food);
        return new ResponseEntity<>(new OkResponse("Food successfully updated"), HttpStatus.OK);
    }

    @DeleteMapping("/{restaurantId}/{foodId}")
    public ResponseEntity deleteFoodFromOneRestaurant(@PathVariable int restaurantId,
                                                      @PathVariable int foodId,
                                                      @RequestAttribute("client-username") String clientUsername) {
        if (!foodService.isUserTheOwnerOfRestaurant(clientUsername, restaurantId)) {
            throw new ForbiddenException("The user is not the owner of this restaurant so he/she can not add any foods to it.");
        }
        foodService.removeOneFoodFromOneRestaurant(foodId);
        return new ResponseEntity<>(new OkResponse("Food successfully removed"), HttpStatus.OK);
    }
}