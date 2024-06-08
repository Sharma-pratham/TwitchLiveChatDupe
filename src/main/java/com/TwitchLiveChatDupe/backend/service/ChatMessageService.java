package com.TwitchLiveChatDupe.backend.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TwitchLiveChatDupe.backend.dao.ChatMessageDao;
import com.TwitchLiveChatDupe.backend.model.ChatMessage;

/**
 * Service class for managing chat messages.
 */
@Service
public class ChatMessageService {

    @Autowired
    private  ChatMessageDao chatMessageDAO;

    /**
     * Saves a chat message to the database.
     *
     * @param message The chat message to save.
     * @return The saved chat message with generated ID and timestamp.
     */
    public ChatMessage saveMessage(ChatMessage message) {
        message.setId(UUID.randomUUID());
        message.setTimestamp(Timestamp.from(Instant.now()));
        chatMessageDAO.save(message);
        return message;
    }


    /**
     * Retrieves all chat messages for a specific channel from the database.
     *
     * @param channelId The ID of the channel whose messages are to be retrieved.
     * @return A list of chat messages for the specified channel.
     */
    public List<ChatMessage> getMessagesByChannel(String channelId) {
        return chatMessageDAO.getMessagesByChannel(channelId);
    }
}

