package restaurant.manager.RestaurantWebServiceWithDatabase.Repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Restaurant;

import java.util.List;

@Repository
public class RestaurantRepository implements RestaurantDAO {

    private EntityManager entityManager;

    @Autowired
    public RestaurantRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Restaurant restaurant) {
        entityManager.persist(restaurant);
    }

    @Override
    public Restaurant findById(Integer id) {
        return entityManager.find(Restaurant.class, id);
    }

    @Override
    public Restaurant findByIdJoinFetch(Integer id) {
        TypedQuery<Restaurant> query = entityManager.createQuery(
                "select r from Restaurant r JOIN FETCH r.foods JOIN FETCH r.owner where r.id = :queryId"
                , Restaurant.class
        );
        query.setParameter("queryId", id);
        return query.getSingleResult();
    }

    @Override
    public List<Restaurant> findAll() {
        TypedQuery<Restaurant> theQuery = entityManager.createQuery(
                "FROM Restaurant order by restaurantName asc", Restaurant.class);
        return theQuery.getResultList();
    }

    @Override
    public List<Restaurant> findByName(String name) {
        TypedQuery<Restaurant> theQuery = entityManager.createQuery(
                "FROM Restaurant WHERE restaurantName=:queryName", Restaurant.class);
        theQuery.setParameter("queryName", name);
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Restaurant restaurant) {
        entityManager.merge(restaurant);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Restaurant restaurant = entityManager.find(Restaurant.class, id);
        entityManager.remove(restaurant);
    }

    @Override
    public Integer deleteAll() {
        int rowsDeleted = entityManager.createQuery("DELETE FROM Restaurant").executeUpdate();
        return rowsDeleted;
    }
}
