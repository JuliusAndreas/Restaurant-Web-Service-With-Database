package restaurant.manager.RestaurantWebServiceWithDatabase.Controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.User;
import restaurant.manager.RestaurantWebServiceWithDatabase.Services.UserService;
import restaurant.manager.RestaurantWebServiceWithDatabase.Utilities.Views;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    @JsonView(Views.Public.class)
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<List<User>>(
                userService.fetchAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @JsonView(Views.Public.class)
    public ResponseEntity<?> getUserByID(@PathVariable Integer id) {
        User user = userService.fetchOneUser(id);
        if (user != null) {
            return new ResponseEntity<User>(
                    user, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Such user does not exist", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        userService.addOneUser(user);
        return new ResponseEntity<>("Successfully added the user", HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody User user) {
        userService.updateOneUser(id, user);
        return new ResponseEntity<>("Successfully added the user", HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        userService.removeOneUser(id);
        return new ResponseEntity<>("Successfully added the user", HttpStatus.OK);
    }
}

