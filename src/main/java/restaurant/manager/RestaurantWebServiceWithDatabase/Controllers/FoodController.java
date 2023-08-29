package restaurant.manager.RestaurantWebServiceWithDatabase.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Food;
import restaurant.manager.RestaurantWebServiceWithDatabase.Exceptions.NotFoundException;
import restaurant.manager.RestaurantWebServiceWithDatabase.Services.FoodService;
import restaurant.manager.RestaurantWebServiceWithDatabase.Utilities.OkResponse;

import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurantId}/foods")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping("/")
    public List<Food> getAllFoodsFromOneRestaurant(@PathVariable int restaurantId) {
        List<Food> responseList = foodService.fetchAllFoodsFromOneRestaurantById(restaurantId);
        if (responseList.isEmpty()) {
            throw new NotFoundException("No Food was found");
        }
        return responseList;
    }

    @PostMapping("/")
    public ResponseEntity addFoodToOneRestaurant(@PathVariable int restaurantId
            , @RequestBody Food food) {
        foodService.addFoodToOneRestaurant(restaurantId, food);
        return new ResponseEntity<>(new OkResponse("Food successfully added"), HttpStatus.OK);
    }

    @PutMapping("/{foodId}")
    public ResponseEntity updateFoodFromOneRestaurant(@PathVariable int foodId
            , @RequestBody Food food) {
        foodService.updateOneFoodFromOneRestaurant(foodId, food);
        return new ResponseEntity<>(new OkResponse("Food successfully updated"), HttpStatus.OK);
    }

    @DeleteMapping("/{foodId}")
    public ResponseEntity deleteFoodFromOneRestaurant(@PathVariable int foodId) {
        foodService.removeOneFoodFromOneRestaurant(foodId);
        return new ResponseEntity<>(new OkResponse("Food successfully removed"), HttpStatus.OK);
    }
}

