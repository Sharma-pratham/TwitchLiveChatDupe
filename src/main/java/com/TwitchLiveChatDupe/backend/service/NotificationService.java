package com.TwitchLiveChatDupe.backend.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TwitchLiveChatDupe.backend.dao.NotificationSender;
import com.TwitchLiveChatDupe.backend.dao.ProfileDao;
import com.TwitchLiveChatDupe.backend.model.Profile;

/**
 * Service class for sending notifications and emails to users.
 */
@Service
public class NotificationService {

    @Autowired
    private ProfileDao dao;

    @Autowired
    private NotificationSender notificationSender;

    /**
     * Sends a notification message to the user with the specified ID.
     *
     * @param userId The ID of the user to send the notification to.
     * @param message The notification message to send.
     * @throws RuntimeException if the user profile is not found.
     */
    public void sendNotification(UUID userId, String message) {
        Profile profile = dao.getProfile(userId);
        if (profile == null) {
            throw new RuntimeException("Profile not found");
        }
        notificationSender.send(profile.getEmail(), message);
    }

    /**
     * Sends an email to the user with the specified ID.
     *
     * @param userId The ID of the user to send the email to.
     * @param subject The subject of the email.
     * @param body The body of the email.
     * @throws RuntimeException if the user profile is not found.
     */
    public void sendEmail(UUID userId, String subject, String body) {
        Profile profile = dao.getProfile(userId);
        if (profile == null) {
            throw new RuntimeException("Profile not found");
        }
        notificationSender.sendEmail(profile.getEmail(), subject, body);
    }
}
