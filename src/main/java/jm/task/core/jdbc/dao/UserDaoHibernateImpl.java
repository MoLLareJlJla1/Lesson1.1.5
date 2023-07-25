package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static SessionFactory factory = Util.getSessionFactory();

    @Override
    public void createUsersTable() {
        try (Session session = factory.getCurrentSession();) {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS User (id INT PRIMARY KEY AUTO_INCREMENT," +
                    " name VARCHAR(45), lastname VARCHAR(45), age INT)").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = factory.getCurrentSession();) {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS User").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = factory.getCurrentSession();) {
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.saveOrUpdate(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = factory.getCurrentSession();) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> user = new ArrayList<>();
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            user = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
            return user;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = factory.getCurrentSession();) {
            session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            session.getTransaction().commit();
        }
    }
}
