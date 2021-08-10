package dao;

import model.User;
import util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final Connection connect = Util.connect();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try(Statement st = connect.createStatement()) {

            st.executeUpdate("CREATE TABLE IF NOT EXISTS users (" +
                            "id BIGINT NOT NULL AUTO_INCREMENT," +
                            "name VARCHAR(255), " +
                            "lastname VARCHAR(255), " +
                            "age TINYINT," +
                            "PRIMARY KEY (id)" + ");");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try(Statement st = connect.createStatement()) {

            st.executeUpdate("DROP TABLE IF EXISTS users;");

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(PreparedStatement st =
                    connect.prepareStatement("INSERT INTO users (name, lastname, age) VALUES (?,?,?);")) {

            st.setString(1, name); // index от 1
            st.setString(2, lastName);
            st.setByte(3, age);

            st.executeUpdate(); // Выполнить запрос

            System.out.println("Пользователь с именем " + name + " добавлен в базу данных");

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void removeUserById(long id) { //
        try(Statement st = connect.createStatement()) {

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try (Statement st = connect.createStatement();
             ResultSet resultSet = st.executeQuery("SELECT * FROM users;")){

            while(resultSet.next()){
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                Byte age = resultSet.getByte("age");

                User user = new User(name, lastName, age);
                user.setId(id);
                userList.add(user);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try(Statement st = connect.createStatement()) {

            st.executeUpdate("TRUNCATE TABLE users;");

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
