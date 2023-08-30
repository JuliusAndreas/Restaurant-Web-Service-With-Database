package restaurant.manager.RestaurantWebServiceWithDatabase.Controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.User;
import restaurant.manager.RestaurantWebServiceWithDatabase.Exceptions.NotFoundException;
import restaurant.manager.RestaurantWebServiceWithDatabase.Services.UserService;
import restaurant.manager.RestaurantWebServiceWithDatabase.Utilities.OkResponse;
import restaurant.manager.RestaurantWebServiceWithDatabase.Utilities.Views;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    @JsonView(Views.Public.class)
    public List<User> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int itemsPerPage,
                                  @RequestParam(defaultValue = "username") String sortedBy) {
        List<User> responseList = userService.fetchAllUsers(page, itemsPerPage, sortedBy);
        if (responseList.isEmpty()) {
            throw new NotFoundException("No User was found");
        }
        return responseList;
    }

    @GetMapping("/{id}")
    @JsonView(Views.Public.class)
    public User getUserByID(@PathVariable Integer id) {
        User fetchedUser = userService.fetchOneUser(id);
        if (fetchedUser == null){
            throw new NotFoundException("No User was found");
        }
        return fetchedUser;
    }

    @PostMapping("/")
    public ResponseEntity addUser(@RequestBody User user) {
        userService.addOneUser(user);
        return new ResponseEntity<>(new OkResponse("User successfully added"), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody User user) {
        userService.updateOneUser(id, user);
        return new ResponseEntity<>(new OkResponse("User successfully updated"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        userService.removeOneUser(id);
        return new ResponseEntity<>(new OkResponse("User successfully removed"), HttpStatus.OK);
    }
}

