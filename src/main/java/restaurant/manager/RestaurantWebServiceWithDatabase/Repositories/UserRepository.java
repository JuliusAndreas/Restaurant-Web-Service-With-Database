package restaurant.manager.RestaurantWebServiceWithDatabase.Repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.User;

import java.util.List;

@Repository
public class UserRepository implements UserDAO {

    private EntityManager entityManager;

    @Autowired
    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public User findById(Integer id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> theQuery = entityManager.createQuery("FROM User order by username asc", User.class);
        return theQuery.getResultList();
    }

    @Override
    public List<User> findByUsername(String username) {
        TypedQuery<User> theQuery = entityManager.createQuery("FROM User WHERE username=:queryUsername"
                , User.class);
        theQuery.setParameter("queryUsername", username);
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public Integer deleteAll() {
        int rowsDeleted = entityManager.createQuery("DELETE FROM User").executeUpdate();
        return rowsDeleted;
    }


}
