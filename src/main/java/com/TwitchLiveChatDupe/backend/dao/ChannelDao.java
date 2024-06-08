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

import com.TwitchLiveChatDupe.backend.model.Channel;
import com.TwitchLiveChatDupe.backend.resources.StaticResources;


/**
 * Data Access Object for managing Channel entities.
 */
@Repository
public class ChannelDao {



    /**
     * Creates a new channel in the database.
     * 
     * @param channel The channel details.
     * @return The created channel with the generated ID.
     */
    public Channel createChannel(Channel channel) {
        try (Connection connection = DriverManager.getConnection(StaticResources.DB_URL, StaticResources.DB_USERNAME, StaticResources.DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(StaticResources.CREATE_CHANNEL)) {

            UUID channelId = UUID.randomUUID();
            preparedStatement.setObject(1, channelId);
            preparedStatement.setString(2, channel.getName());
            preparedStatement.setString(3, channel.getDescription());
            preparedStatement.setTimestamp(4, Timestamp.from(Instant.now()));
            preparedStatement.setTimestamp(5, Timestamp.from(Instant.now()));

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                channel.setId(UUID.fromString(rs.getString("id")));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return channel;
    }


     /**
     * Retrieves a channel from the database by its ID.
     * 
     * @param id The ID of the channel to retrieve.
     * @return The retrieved channel, or null if not found.
     */
    public Channel findById(UUID id) {
        Channel channel = null;
        

        try (Connection connection = DriverManager.getConnection(StaticResources.DB_URL, StaticResources.DB_USERNAME, StaticResources.DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(StaticResources.GET_CHANNEL_BY_ID)) {

            preparedStatement.setObject(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                channel = new Channel();
                channel.setId(UUID.fromString(rs.getString("id")));
                channel.setName(rs.getString("name"));
                channel.setDescription(rs.getString("description"));
                channel.setCreatedAt(rs.getTimestamp("created_at"));
                channel.setUpdatedAt(rs.getTimestamp("updated_at"));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return channel;
    }


    /**
     * Updates an existing channel in the database.
     * 
     * @param id The ID of the channel to update.
     * @param channelDetails The new details of the channel.
     * @return The updated channel.
     */
    public Channel updateChannel(UUID id, Channel channelDetails) {

        try (Connection connection = DriverManager.getConnection(StaticResources.DB_URL, StaticResources.DB_USERNAME, StaticResources.DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(StaticResources.UPDATE_CHANNEL)) {

            preparedStatement.setString(1, channelDetails.getName());
            preparedStatement.setString(2, channelDetails.getDescription());
            preparedStatement.setTimestamp(3, Timestamp.from(Instant.now()));
            preparedStatement.setObject(4, id);

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return findById(id);
    }

    /**
     * Deletes a channel from the database by its ID.
     * 
     * @param id The ID of the channel to delete.
     */
    public void deleteChannel(UUID id) {

        try (Connection connection = DriverManager.getConnection(StaticResources.DB_URL, StaticResources.DB_USERNAME, StaticResources.DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(StaticResources.DELETE_CHANNEL)) {

            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }


    /**
     * Saves a channel to the database. If the channel does not have an ID, it is created.
     * Otherwise, it is updated.
     * 
     * @param channel The channel to save.
     */
    public void save(Channel channel) {
        if (channel.getId() == null) {
            createChannel(channel);
        } else {
            updateChannel(channel.getId(), channel);
        }
    }
}
