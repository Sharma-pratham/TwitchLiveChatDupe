package com.TwitchLiveChatDupe.backend.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.TwitchLiveChatDupe.backend.model.User;
import com.TwitchLiveChatDupe.backend.resources.StaticResources;


/**
 * Data Access Object for managing User entities.
 */
@Repository
public class UserDao {
    

    /**
     * Finds a user by their username.
     *
     * @param username The username of the user to find.
     * @return The found user, or null if not found.
     */
    public User findByUsername(String username) {
        User user = null;

        try (Connection connection = DriverManager.getConnection(StaticResources.DB_URL, StaticResources.DB_USERNAME, StaticResources.DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(StaticResources.GET_USER_BY_USERNAME)) {

            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(UUID.fromString(rs.getString("id")));
                user.setUsername(rs.getString("username"));
                user.setRole(rs.getString("role"));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return user;
    }
    
    /**
     * Finds a user by their ID.
     *
     * @param id The ID of the user to find.
     * @return The found user, or null if not found.
     */
    public User findById(UUID id) {
        
        User user = null;

        try (Connection connection = DriverManager.getConnection(StaticResources.DB_URL, StaticResources.DB_USERNAME, StaticResources.DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(StaticResources.GET_USER_BY_ID)) {

            preparedStatement.setObject(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(UUID.fromString(rs.getString("id")));
                user.setUsername(rs.getString("username"));
                user.setRole(rs.getString("role"));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return user;
    }

    /**
     * Saves a user to the database. Updates an existing user if the user ID exists.
     *
     * @param user The user to save.
     */
    public void save(User user) {
        
        try (Connection connection = DriverManager.getConnection(StaticResources.DB_URL, StaticResources.DB_USERNAME, StaticResources.DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(StaticResources.UPDATE_USER)) {

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getRole());
            preparedStatement.setObject(3, user.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
