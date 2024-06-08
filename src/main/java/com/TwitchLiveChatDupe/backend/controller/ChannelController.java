package com.TwitchLiveChatDupe.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TwitchLiveChatDupe.backend.model.Channel;
import com.TwitchLiveChatDupe.backend.service.ChannelService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/channels")
@Tag(name = "Channel Controller", description = "Operations related to channel management")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @PostMapping("/create")
    @Operation(summary = "Create a new channel", description = "Creates a new channel with the specified details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Channel created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request data"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Channel> createChannel(@RequestBody Channel channelRequest) {
        Channel createdChannel = channelService.createChannel(channelRequest);
        return ResponseEntity.ok(createdChannel);
    }
}
