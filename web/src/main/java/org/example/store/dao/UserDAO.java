package org.example.store.dao;

import org.example.store.model.User;
import org.example.store.util.DataConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAO<User, Integer, String> {

    private static final String INSERT_USERS_SQL = "INSERT INTO users (email, password ) VALUES (?, ?);";

    private static final String SELECT_USER_BY_ID = "select id,email,password,role from users where id=?;";
    private static final String SELECT_ALL_USERS = "select * from users;";
    private static final String DELETE_USERS = "delete from users where id = ?;";
    private static final String UPDATE_USERS = "update users set email = ?,password= ?, role =? where id = ?;";
    private static final String FIND_USER = "select * from users where email=? and password=?";

    public UserDAO() {
    }

    public List<User> readAll() {

        List<User> users = new ArrayList<>();
        try (Connection connection = DataConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String role = rs.getString("role");
                users.add(new User(id, email, password, role));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

    public void create(User user) {
        try (Connection connection = DataConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public User readById(Integer id) {
        User user = null;
        try (Connection connection = DataConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String role = rs.getString("role");
                user = new User(id, name, email, role);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }

    public boolean delete(Integer id) {
        boolean rowDeleted = false;
        try (Connection connection = DataConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USERS)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    public boolean update(User user) {
        boolean rowUpdated = false;
        try (Connection connection = DataConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USERS)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());
            statement.setInt(4, user.getId());

            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    public User find(String email, String password) {
        User user = null;
        try (Connection connection = DataConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String role = rs.getString("role");
                user = new User(id, email, password, role);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}








