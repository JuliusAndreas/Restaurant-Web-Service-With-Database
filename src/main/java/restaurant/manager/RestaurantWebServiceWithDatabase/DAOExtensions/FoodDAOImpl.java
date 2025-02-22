package restaurant.manager.RestaurantWebServiceWithDatabase.DAOExtensions;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Food;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Reservation;
import restaurant.manager.RestaurantWebServiceWithDatabase.Exceptions.NotFoundException;

public class FoodDAOImpl implements FoodDAO {

    private EntityManager entityManager;

    @Autowired
    public FoodDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void update(Integer id, Food food) {
        Food foodToBeUpdated = entityManager.find(Food.class, id);
        if (foodToBeUpdated == null) {
            throw new NotFoundException("Such food does not exist");
        }
        if ((food.getRestaurant() != null) &&
                (!food.getRestaurant().equals(foodToBeUpdated.getRestaurant()))) {
            throw new RuntimeException("Can't change the restaurant");
        }
        foodToBeUpdated.setFoodName(food.getFoodName());
        foodToBeUpdated.setPrice(food.getPrice());
        foodToBeUpdated.setRating(food.getRating());
        foodToBeUpdated.setFoodQuantity(food.getFoodQuantity());
        for (Reservation reservation : foodToBeUpdated.getReservations()) {
            entityManager.remove(reservation);
        }
        foodToBeUpdated.setReservations(food.getReservations());
        entityManager.merge(foodToBeUpdated);
    }
}
