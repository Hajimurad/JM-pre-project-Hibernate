package dao;

import model.User;
import util.Util;
import org.hibernate.Session;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {

        try(Session currentSession = Util.getSessionFromConfig()) {
            currentSession.beginTransaction();
            currentSession.createSQLQuery("CREATE TABLE IF NOT EXISTS table1 (" +
                    "id BIGINT NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR(45), " +
                    "lastname VARCHAR(45), " +
                    "age TINYINT," +
                    "PRIMARY KEY (id)" + ");").executeUpdate();
            currentSession.getTransaction().commit();

        } catch(Throwable t) {
            t.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {

        try(Session currentSession = Util.getSessionFromConfig()) {
            currentSession.beginTransaction();
            currentSession.createSQLQuery("DROP TABLE IF EXISTS table1;").executeUpdate();
            currentSession.getTransaction().commit();

        } catch(Throwable t) {
            t.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);

        try(Session currentSession = Util.getSessionFromConfig()) {
            currentSession.beginTransaction();
            currentSession.save(user);
            currentSession.getTransaction().commit();

        } catch(Throwable t) {
            t.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {

        try(Session currentSession = Util.getSessionFromConfig()) {
            currentSession.beginTransaction();
            currentSession.createQuery("DELETE FROM User u WHERE u.id=:id")
                    .setParameter("id", id)
                    .executeUpdate();
            currentSession.getTransaction().commit();

        } catch(Throwable t) {
            t.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        
        try(Session currentSession = Util.getSessionFromConfig()) {
            currentSession.beginTransaction();
            users = currentSession.createQuery("from User", User.class).list();
            currentSession.getTransaction().commit();

        } catch(Throwable t) {
            t.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {

        try(Session currentSession = Util.getSessionFromConfig()) {
            currentSession.beginTransaction();
            currentSession.createSQLQuery("TRUNCATE TABLE table1;").executeUpdate();
            currentSession.getTransaction().commit();

        } catch(Throwable t) {
            t.printStackTrace();
        }
    }
}
