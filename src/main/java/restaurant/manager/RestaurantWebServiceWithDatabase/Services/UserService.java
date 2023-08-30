package restaurant.manager.RestaurantWebServiceWithDatabase.Services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.User;
import restaurant.manager.RestaurantWebServiceWithDatabase.Exceptions.NotFoundException;
import restaurant.manager.RestaurantWebServiceWithDatabase.Repositories.UserRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;

    public List<User> fetchAllUsers(int page, int itemsPerPage, String sortedBy) {
        Pageable pageable = PageRequest.of(page, itemsPerPage, Sort.by(sortedBy));
        return userRepository.findAllUsers(pageable);
    }

    public User fetchOneUser(@NonNull Integer id) {
        return userRepository.findByUserId(id);
    }

    public void addOneUser(@NonNull User user) {
        userRepository.save(user);
    }

    public void updateOneUser(@NonNull Integer id, @NonNull User user) {
        userRepository.update(id, user);
    }

    public void removeOneUser(@NonNull Integer id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("No User was found to be deleted");
        }
        userRepository.deleteById(id);
    }
}
