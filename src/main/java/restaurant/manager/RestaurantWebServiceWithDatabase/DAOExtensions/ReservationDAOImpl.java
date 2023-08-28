package restaurant.manager.RestaurantWebServiceWithDatabase.DAOExtensions;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Reservation;

public class ReservationDAOImpl implements ReservationDAO {

    private EntityManager entityManager;

    @Autowired
    public ReservationDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void update(Reservation reservation) {
        entityManager.merge(reservation);
    }
}
