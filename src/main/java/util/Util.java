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
        if (sessionFactory == null) {
            try {
                Configuration config = new Configuration()
                        .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                        .setProperty("hibernate.show_sql", "true")
                        .setProperty("hibernate.connection.url", URL)
                        .setProperty("hibernate.connection.username", LOGIN)
                        .setProperty("hibernate.connection.password", PASSWORD)
                        .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
                        .setProperty("hibernate.hbm2ddl.auto", "update")
                        .addAnnotatedClass(model.User.class);

                sessionFactory = config.buildSessionFactory();
            } catch (Throwable t) {
                throw new ExceptionInInitializerError(t);
            }
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
