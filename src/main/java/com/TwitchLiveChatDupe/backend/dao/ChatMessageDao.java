package com.TwitchLiveChatDupe.backend.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.TwitchLiveChatDupe.backend.model.ChatMessage;
import com.TwitchLiveChatDupe.backend.resources.StaticResources;


/**
 * Data Access Object for managing ChatMessage entities.
 */
@Repository
public class ChatMessageDao {

     /**
     * Saves a chat message to the database.
     *
     * @param chatMessage The chat message to save.
     */
     public void save(ChatMessage chatMessage) {
        
        try (Connection connection = DriverManager.getConnection(StaticResources.DB_URL, StaticResources.DB_USERNAME, StaticResources.DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(StaticResources.INSERT_MESSAGE)) {

            preparedStatement.setObject(1, chatMessage.getId());
            preparedStatement.setString(2, chatMessage.getFrom());
            preparedStatement.setString(3, chatMessage.getText());
            preparedStatement.setString(4, chatMessage.getChannelId());
            preparedStatement.setTimestamp(5, chatMessage.getTimestamp());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

     /**
     * Retrieves all chat messages for a specific channel from the database.
     *
     * @param channelId The ID of the channel whose messages are to be retrieved.
     * @return A list of chat messages for the specified channel.
     */
    public List<ChatMessage> getMessagesByChannel(String channelId) {

        List<ChatMessage> messages = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(StaticResources.DB_URL, StaticResources.DB_USERNAME, StaticResources.DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(StaticResources.GET_MESSAGE_BY_ID)) {

            preparedStatement.setString(1, channelId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                ChatMessage message = new ChatMessage();
                message.setId(UUID.fromString(rs.getString("id")));
                message.setFrom(rs.getString("from_user"));
                message.setText(rs.getString("text"));
                message.setChannelId(rs.getString("channel_id"));
                message.setTimestamp(rs.getTimestamp("timestamp"));
                messages.add(message);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return messages;
    }
}
