package com.TwitchLiveChatDupe.backend.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.TwitchLiveChatDupe.backend.model.Profile;
import com.TwitchLiveChatDupe.backend.resources.StaticResources;


/**
 * Data Access Object for managing Profile entities.
 */
@Repository
public class ProfileDao {
    
    /**
     * Registers a new user profile in the database.
     *
     * @param profile The profile details to be registered.
     * @return The registered profile with generated ID and additional details.
     */
    public Profile signup(Profile profile){
        Connection connection;

        try {

            connection = DriverManager.getConnection(StaticResources.DB_URL, StaticResources.DB_USERNAME, StaticResources.DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(StaticResources.INSERT_PROFILE);

            preparedStatement.setObject(1, UUID.randomUUID());
            preparedStatement.setString(2, profile.getFullName());
            preparedStatement.setString(3, profile.getUsername());
            preparedStatement.setString(4, profile.getEmail());
            preparedStatement.setString(5, profile.getPassword());
            preparedStatement.setString(6, profile.getAvatar());
            preparedStatement.setBoolean(7, true);
            preparedStatement.setTimestamp(8, Timestamp.from(Instant.now()));
            preparedStatement.setTimestamp(9, Timestamp.from(Instant.now()));


            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                profile.setId(UUID.fromString(rs.getString("id")));
                profile.setPassword((rs.getString("password")));
                profile.setActive(rs.getBoolean("active"));
                profile.setCreatedAt(rs.getTimestamp("created_at"));
                profile.setUpdatedAt(rs.getTimestamp("updated_at"));
            }


        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return profile;
    }

    /**
     * Retrieves a user profile from the database by its ID.
     *
     * @param id The ID of the profile to retrieve.
     * @return The retrieved profile, or null if not found.
     */
    public Profile getProfile(UUID id) {
        Profile profile = null;
        

        try (Connection connection = DriverManager.getConnection(StaticResources.DB_URL, StaticResources.DB_USERNAME, StaticResources.DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(StaticResources.GET_PROFILE_BY_ID)) {

            preparedStatement.setObject(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                profile = new Profile();
                profile.setId(UUID.fromString(rs.getString("id")));
                profile.setFullName(rs.getString("full_name"));
                profile.setUsername(rs.getString("username"));
                profile.setEmail(rs.getString("email"));
                profile.setPassword(rs.getString("password"));
                profile.setAvatar(rs.getString("avatar"));
                profile.setActive(rs.getBoolean("active"));
                profile.setCreatedAt(rs.getTimestamp("created_at"));
                profile.setUpdatedAt(rs.getTimestamp("updated_at"));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return profile;
    }

    /**
     * Adds a follower to a user's profile in the database.
     *
     * @param id The ID of the profile to follow.
     * @param followerId The ID of the follower.
     */
    public void followProfile(UUID id, UUID followerId) {
        try (Connection connection = DriverManager.getConnection(StaticResources.DB_URL, StaticResources.DB_USERNAME, StaticResources.DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(StaticResources.FOLLOW_PROFILE)) {

            preparedStatement.setObject(1, id);
            preparedStatement.setObject(2, followerId);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    /**
     * Removes a follower from a user's profile in the database.
     *
     * @param id The ID of the profile to unfollow.
     * @param followerId The ID of the follower to remove.
     */
    public void unfollowProfile(UUID id, UUID followerId) {
        
        try (Connection connection = DriverManager.getConnection(StaticResources.DB_URL, StaticResources.DB_USERNAME, StaticResources.DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(StaticResources.UNFOLLOW_PROFILE)) {

            preparedStatement.setObject(1, id);
            preparedStatement.setObject(2, followerId);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}
