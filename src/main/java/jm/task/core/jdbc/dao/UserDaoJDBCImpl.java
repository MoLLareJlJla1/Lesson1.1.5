package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.con();

    public void createUsersTable() {
        try (PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS user (id INT PRIMARY KEY AUTO_INCREMENT," +
                " name VARCHAR(45), lastname VARCHAR(45), age INT)");) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (PreparedStatement statement = connection.prepareStatement("DROP TABLE IF EXISTS user");) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO user (name, lastName, age) VALUES (?, ?, ?)");) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void removeUserById(long id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE from user Where id=?");) {
            statement.setInt(1, (int) id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> us = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * from user");) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId((long) resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge((byte) resultSet.getInt("age"));

                us.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return us;
    }

    public void cleanUsersTable() {
        try (PreparedStatement statement = connection.prepareStatement("Delete from user");) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
