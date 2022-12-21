package web.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import web.model.User;

@Component
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT user FROM User user", User.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        entityManager.remove(entityManager.find(User.class, id));
    }
}