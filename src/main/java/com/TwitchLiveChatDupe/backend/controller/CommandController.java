package com.TwitchLiveChatDupe.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TwitchLiveChatDupe.backend.model.ChatCommand;
import com.TwitchLiveChatDupe.backend.service.CommandHandlerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/commands")
@Tag(name = "Command Controller", description = "Operations related to handling chat commands")
public class CommandController {

    @Autowired
    private CommandHandlerService commandHandlerService;

    @PostMapping("/setAdmin")
    @Operation(summary = "Set admin role", description = "Sets the admin role to a user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Role set successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public void setAdmin(@RequestBody ChatCommand command) {
        commandHandlerService.setAdmin(command);
    }

    @PostMapping("/unsetAdmin")
    @Operation(summary = "Unset admin role", description = "Removes the admin role from a user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Role removed successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public void unsetAdmin(@RequestBody ChatCommand command) {
        commandHandlerService.unsetAdmin(command);
    }

    @PostMapping("/mute")
    @Operation(summary = "Mute a user", description = "Mutes a user in the channel")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User muted successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public void mute(@RequestBody ChatCommand command) {
        commandHandlerService.mute(command);
    }

    @PostMapping("/unmute")
    @Operation(summary = "Unmute a user", description = "Unmutes a user in the channel")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User unmuted successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public void unmute(@RequestBody ChatCommand command) {
        commandHandlerService.unmute(command);
    }

    @PostMapping("/ban")
    @Operation(summary = "Ban a user", description = "Bans a user from the channel")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User banned successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public void ban(@RequestBody ChatCommand command) {
        commandHandlerService.ban(command);
    }

    @PostMapping("/unban")
    @Operation(summary = "Unban a user", description = "Unbans a user in the channel")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User unbanned successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public void unban(@RequestBody ChatCommand command) {
        commandHandlerService.unban(command);
    }

    @PostMapping("/suspend")
    @Operation(summary = "Suspend a channel", description = "Suspends a channel")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Channel suspended successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public void suspend(@RequestBody ChatCommand command) {
        commandHandlerService.suspend(command);
    }

    @PostMapping("/setTitle")
    @Operation(summary = "Set channel title", description = "Sets the title of a channel")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Title set successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public void setTitle(@RequestBody ChatCommand command) {
        commandHandlerService.setTitle(command);
    }

    @PostMapping("/setDescription")
    @Operation(summary = "Set channel description", description = "Sets the description of a channel")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Description set successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public void setDescription(@RequestBody ChatCommand command) {
        commandHandlerService.setDescription(command);
    }
}

