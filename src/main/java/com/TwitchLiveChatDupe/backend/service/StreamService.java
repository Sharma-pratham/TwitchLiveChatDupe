package com.TwitchLiveChatDupe.backend.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TwitchLiveChatDupe.backend.dao.ProfileDao;
import com.TwitchLiveChatDupe.backend.model.Profile;

/**
 * Service class for managing streams.
 */
@Service
public class StreamService {

    @Autowired
    private ProfileDao dao;

    @Autowired
    private NotificationService notificationService;

    /**
     * Starts a stream for the user with the specified ID.
     *
     * @param userId The ID of the user who is starting the stream.
     */
    public void startStream(UUID userId) {
        Profile profile = dao.getProfile(userId);

        // Logic to start the stream
        System.out.println("Stream started for user: " + profile.getUsername());

        // Notify followers
        profile.getFollowers().forEach(follower -> {
            notificationService.sendNotification(follower.getId(), "User " + profile.getUsername() + " has started streaming!");
        });

        // Optionally send email notifications
        profile.getFollowers().forEach(follower -> {
            notificationService.sendEmail(follower.getId(), "Stream Started", "User " + profile.getUsername() + " has started streaming. Join now!");
        });
    }
}
