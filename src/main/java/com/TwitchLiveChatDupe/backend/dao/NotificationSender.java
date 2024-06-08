package com.TwitchLiveChatDupe.backend.dao;

import org.springframework.stereotype.Component;



/**
 * Component responsible for sending notifications and emails.
 */
@Component
public class NotificationSender {


    /**
     * Sends a push notification to the specified recipient.
     *
     * @param recipient The recipient of the notification.
     * @param message The message to be sent.
     */
    public void send(String recipient, String message) {
        // Logic to send push notification
        System.out.println("Sending push notification to " + recipient + ": " + message);
    }

    /**
     * Sends an email to the specified recipient.
     *
     * @param recipient The recipient of the email.
     * @param subject The subject of the email.
     * @param body The body of the email.
     */
    public void sendEmail(String recipient, String subject, String body) {
        // Logic to send email
        System.out.println("Sending email to " + recipient + ": " + subject + " - " + body);
    }
}
