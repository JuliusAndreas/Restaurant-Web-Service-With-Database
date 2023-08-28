package restaurant.manager.RestaurantWebServiceWithDatabase.DAOExtensions;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Food;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Reservation;

public class ReservationDAOImpl implements ReservationDAO {

    private EntityManager entityManager;

    @Autowired
    public ReservationDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public void update(Integer id, Reservation reservation) {
        Reservation reservationToBeUpdated = entityManager.find(Reservation.class, id);
        reservationToBeUpdated.setFood(reservation.getFood());
        reservationToBeUpdated.setUser(reservation.getUser());
        entityManager.merge(reservationToBeUpdated);
    }
}
