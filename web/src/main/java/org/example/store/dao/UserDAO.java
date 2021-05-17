package org.example.store.dao;

import org.apache.log4j.Logger;
import org.example.store.model.User;
import org.example.store.utils.DataConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO implements DAO<User, Integer> {
    private static final String INSERT_USERS_SQL = "INSERT INTO users (email, password ) VALUES (?, ?)";
    private static final String INSERT_ADMIN_SQL = "INSERT INTO users (email, password ) VALUES (?, ?)";
    private static final String FIND_USER_BY_EMAIL_AND_PASSWORD = "select * from users where email=? and password=?";
    private static final String FIND_USER_BY_EMAIL = "select * from users where email=?";
    private static final String SELECT_USER_BY_ID = "select id,email,password,role from users where id=?";
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String DELETE_USERS = "delete from users where id = ?";
    private static final String UPDATE_USERS = "update users set email = ?,password= ?, role =? where id = ?";
    static Logger log = Logger.getLogger(UserDAO.class.getName());

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
                users.add(User.newBuilder()
                        .setId(id)
                        .setEmail(email)
                        .setPassword(password)
                        .setRole(role)
                        .build()
                );
//                users.add(new User(id, email, password, role));
            }
        } catch (SQLException e) {
            log.error("Exception while trying to get all users",e);
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
            log.error("Exception while trying to create user",e);
        }
    }

    public void createWithRole(User user) {
        try (Connection connection = DataConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ADMIN_SQL)) {

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(2, user.getRole());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Exception while trying to create users with role",e);
        }
    }

    public Optional<User> findById(Integer id) {
        try (Connection connection = DataConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(User.newBuilder()
                        .setId(id)
                        .setEmail(rs.getString("email"))
                        .setPassword(rs.getString("password"))
                        .setRole(rs.getString("role"))
                        .build());
            }
        } catch (SQLException e) {
            log.error("Exception while trying to find by id",e);
        }
        return Optional.empty();
    }

    public boolean delete(Integer id) {
        boolean rowDeleted = false;
        try (Connection connection = DataConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USERS)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error("Exception while trying to delete user",e);
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
            log.error("Exception while trying to update user",e);
        }
        return rowUpdated;
    }

    public Optional<User> findByEmailAndPassword(String email, String password) {

        try (Connection connection = DataConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL_AND_PASSWORD)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();

            log.info("log info");
            if (rs.next()) {
                return Optional.of(User.newBuilder()
                        .setId(rs.getInt("id"))
                        .setEmail(email)
                        .setPassword(password)
                        .setRole(rs.getString("role"))
                        .build());
            }
        } catch (SQLException e) {
            log.error("Exception while trying to checked user",e);
        }
        return Optional.empty();
    }
    public Optional<User> findByEmail(String email) {

        try (Connection connection = DataConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return Optional.of(User.newBuilder()
                        .setId(rs.getInt("id"))
                        .setEmail(email)
                        .setPassword(rs.getString("password"))
                        .setRole(rs.getString("role"))
                        .build());
            }
        } catch (SQLException e) {
            log.error("Exception while trying to checked email",e);
        }
        return Optional.empty();
    }

}








