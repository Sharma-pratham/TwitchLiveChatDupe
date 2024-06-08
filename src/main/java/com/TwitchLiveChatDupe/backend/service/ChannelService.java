package com.TwitchLiveChatDupe.backend.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TwitchLiveChatDupe.backend.dao.ChannelDao;
import com.TwitchLiveChatDupe.backend.model.Channel;


/**
 * Service class for managing channels.
 */
@Service
public class ChannelService {

    @Autowired
    private  ChannelDao dao;

    /**
     * Creates a new channel.
     *
     * @param channelRequest The details of the channel to be created.
     * @return The created channel.
     */
    public Channel createChannel(Channel channelRequest) {
        return dao.createChannel(channelRequest);
    }


    /**
     * Retrieves a channel by its ID.
     *
     * @param id The ID of the channel to retrieve.
     * @return The retrieved channel.
     */
    public Channel getChannel(UUID id) {
        return dao.findById(id);
    }

    /**
     * Updates a channel with new details.
     *
     * @param id The ID of the channel to update.
     * @param channelDetails The new details of the channel.
     * @return The updated channel.
     */
    public Channel updateChannel(UUID id, Channel channelDetails) {
        return dao.updateChannel(id, channelDetails);
    }

    /**
     * Deletes a channel by its ID.
     *
     * @param id The ID of the channel to delete.
     */
    public void deleteChannel(UUID id) {
        dao.deleteChannel(id);
    }
}
