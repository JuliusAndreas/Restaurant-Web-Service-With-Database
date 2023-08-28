package restaurant.manager.RestaurantWebServiceWithDatabase.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Food;
import restaurant.manager.RestaurantWebServiceWithDatabase.Services.FoodService;

import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurantId}/")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping("foods")
    public ResponseEntity getAllFoodsFromOneRestaurant(@PathVariable int restaurantId) {
        List<Food> fetchedFoods = foodService.fetchAllFoodsFromOneRestaurantById(restaurantId);
        if (fetchedFoods != null) {
            return new ResponseEntity<List<Food>>(fetchedFoods, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Such restaurant does not exist", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("addfood")
    public ResponseEntity<String> addFoodToOneRestaurant(@PathVariable int restaurantId
            , @RequestBody Food food) {
        foodService.addFoodToOneRestaurant(restaurantId, food);
        return new ResponseEntity<>("Successfully updated the food", HttpStatus.OK);
    }

    @PutMapping("foods/{foodId}/update")
    public ResponseEntity<String> updateFoodFromOneRestaurant(@PathVariable int foodId
            , @RequestBody Food food) {
        foodService.updateOneFoodFromOneRestaurant(foodId, food);
        return new ResponseEntity<>("Successfully updated the food", HttpStatus.OK);
    }

    @DeleteMapping("foods/{foodId}/delete")
    public ResponseEntity<String> deleteFoodFromOneRestaurant(@PathVariable int foodId) {
        foodService.removeOneFoodFromOneRestaurant(foodId);
        return new ResponseEntity<>("Successfully updated the food", HttpStatus.OK);
    }
}

