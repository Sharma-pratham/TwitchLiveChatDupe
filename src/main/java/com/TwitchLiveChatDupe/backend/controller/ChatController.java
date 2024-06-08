package com.TwitchLiveChatDupe.backend.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.TwitchLiveChatDupe.backend.model.ChatMessage;
import com.TwitchLiveChatDupe.backend.service.ChatMessageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@Tag(name = "Channel Controller", description = "Operations related to channel management")
public class ChatController {

    private  SimpMessagingTemplate messagingTemplate;
    private  ChatMessageService chatMessageService;

    @MessageMapping("/send")
    @Operation(summary = "Create a new channel", description = "Creates a new channel with the specified details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Channel created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request data"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public void sendMessage(ChatMessage message) {
        if (isValidMessage(message)) {
            ChatMessage savedMessage = chatMessageService.saveMessage(message);
            logMessage(savedMessage);

            messagingTemplate.convertAndSend("/topic/messages", savedMessage);
        } else {
            throw new IllegalArgumentException("Invalid message content");
        }
    }

    private boolean isValidMessage(ChatMessage message) {
        return message.getText() != null && !message.getText().trim().isEmpty();
    }

    private void logMessage(ChatMessage message) {
        System.out.println("Received message from " + message.getFrom() + ": " + message.getText());
    }
}
