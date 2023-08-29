package restaurant.manager.RestaurantWebServiceWithDatabase.DAOExtensions;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Food;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Reservation;

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
        foodToBeUpdated.setFoodName(food.getFoodName());
        foodToBeUpdated.setRestaurant(food.getRestaurant());
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
