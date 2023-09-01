package restaurant.manager.RestaurantWebServiceWithDatabase.DAOExtensions;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Food;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Reservation;
import restaurant.manager.RestaurantWebServiceWithDatabase.Exceptions.NotFoundException;

public class ReservationDAOImpl implements ReservationDAO {

    private EntityManager entityManager;

    @Autowired
    public ReservationDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void update(Integer id, Reservation reservation) {
        Reservation reservationToBeUpdated = entityManager.find(Reservation.class, id);
        if (reservationToBeUpdated == null) {
            throw new NotFoundException("Such reservation does not exist");
        }
        reservationToBeUpdated.setFood(reservation.getFood());
        reservationToBeUpdated.setUser(reservation.getUser());
        reservationToBeUpdated.setQuantity(reservation.getQuantity());
        entityManager.merge(reservationToBeUpdated);
    }
}
