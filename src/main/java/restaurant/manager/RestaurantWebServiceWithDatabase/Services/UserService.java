package restaurant.manager.RestaurantWebServiceWithDatabase.Services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Role;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.User;
import restaurant.manager.RestaurantWebServiceWithDatabase.Exceptions.NotFoundException;
import restaurant.manager.RestaurantWebServiceWithDatabase.Repositories.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder bcryptEncoder;

    public List<User> fetchAllUsers(int page, int itemsPerPage, String sortedBy) {
        Pageable pageable = PageRequest.of(page, itemsPerPage, Sort.by(sortedBy));
        return userRepository.findAllUsers(pageable);
    }

    public User fetchOneUser(@NonNull Integer id) {
        return userRepository.findByUserId(id);
    }

    public void addOneUser(@NonNull User user) {
        for (Role role : user.getRoles()) {
            role.setUser(user);
        }
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void updateOneUser(@NonNull Integer id, @NonNull User user) {
        for (Role role : user.getRoles()) {
            role.setUser(user);
        }
        userRepository.update(id, user);
    }

    public void removeOneUser(@NonNull Integer id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("No User was found to be deleted");
        }
        userRepository.deleteById(id);
    }

    public Boolean isUserGivingHisOwnId(@NonNull String username, @NonNull Integer userId) {
        User user = userRepository.findByUserId(userId);
        return user.getUsername().equals(username);
    }
}
