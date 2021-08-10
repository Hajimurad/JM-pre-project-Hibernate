package util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String LOGIN = "root";
    private static final String PASSWORD = "Hajimurad";
    private static final String URL = "jdbc:mysql://localhost:3306/task113db";
    private static Connection connection;
    private static SessionFactory sessionFactory;


    public static Session getSessionFromConfig() throws HibernateException {

        try{
            Configuration config = new Configuration().configure();
            sessionFactory = config.buildSessionFactory();
        } catch(Throwable t) {
            throw new ExceptionInInitializerError(t);
        }
        return sessionFactory.openSession();
    }

    public static Connection connect(){

        if(connection != null){
            return connection;
        }

        try{
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return connection;
    }
}
