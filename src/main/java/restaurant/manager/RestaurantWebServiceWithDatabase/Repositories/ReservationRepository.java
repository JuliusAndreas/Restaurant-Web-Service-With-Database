package restaurant.manager.RestaurantWebServiceWithDatabase.Repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Reservation;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Restaurant;

import java.util.List;

@Repository
public class ReservationRepository implements ReservationDAO{

    private EntityManager entityManager;

    @Autowired
    public ReservationRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Reservation reservation) {
        entityManager.persist(reservation);
    }

    @Override
    public Reservation findById(Integer id) {
        return entityManager.find(Reservation.class, id);
    }

    @Override
    public List<Reservation> findAll() {
        TypedQuery<Reservation> theQuery = entityManager.createQuery(
                "FROM Reservation", Reservation.class);
        return theQuery.getResultList();
    }

    @Override
    public List<Reservation> findByUserId(Integer userID) {
        TypedQuery<Reservation> theQuery = entityManager.createQuery(
                "FROM Reservation WHERE user.id=:queryId", Reservation.class);
        theQuery.setParameter("queryId", userID);
        return theQuery.getResultList();
    }

    @Override
    public List<Reservation> findByFoodId(Integer foodID) {
        TypedQuery<Reservation> theQuery = entityManager.createQuery(
                "FROM Reservation WHERE food.id=:queryId", Reservation.class);
        theQuery.setParameter("queryId", foodID);
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Reservation reservation) {
        entityManager.merge(reservation);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Reservation reservation = entityManager.find(Reservation.class, id);
        entityManager.remove(reservation);
    }

    @Override
    public Integer deleteAll() {
        int rowsDeleted = entityManager.createQuery("DELETE FROM Reservation").executeUpdate();
        return rowsDeleted;
    }
}
