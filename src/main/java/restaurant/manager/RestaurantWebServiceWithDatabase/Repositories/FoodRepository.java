package restaurant.manager.RestaurantWebServiceWithDatabase.Repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Food;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Restaurant;

import java.util.List;

@Repository
public class FoodRepository implements FoodDAO{

    private EntityManager entityManager;

    @Autowired
    public FoodRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    @Transactional
    public void save(Food food) {
        entityManager.persist(food);
    }

    @Override
    public Food findById(Integer id) {
        return entityManager.find(Food.class, id);
    }

    @Override
    public List<Food> findAll() {
        TypedQuery<Food> theQuery = entityManager.createQuery(
                "FROM Food order by foodName asc", Food.class);
        return theQuery.getResultList();
    }

    @Override
    public List<Food> findByName(String name) {
        TypedQuery<Food> theQuery = entityManager.createQuery(
                "FROM Food WHERE foodName=:queryName", Food.class);
        theQuery.setParameter("queryName", name);
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Food food) {
        entityManager.merge(food);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Food food = entityManager.find(Food.class, id);
        entityManager.remove(food);
    }

    @Override
    public Integer deleteAll() {
        int rowsDeleted = entityManager.createQuery("DELETE FROM Food").executeUpdate();
        return rowsDeleted;
    }
}
