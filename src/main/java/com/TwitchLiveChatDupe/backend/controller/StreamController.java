package com.TwitchLiveChatDupe.backend.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TwitchLiveChatDupe.backend.service.StreamService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/streams")
@Tag(name = "Stream Controller", description = "Operations related to streaming")
public class StreamController {

    @Autowired
    private StreamService streamService;

    @PostMapping("/start")
    @Operation(summary = "Start a stream", description = "Starts a stream for the specified user ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Stream started successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid user ID"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> startStream(@RequestBody UUID userId) {
        streamService.startStream(userId);
        return ResponseEntity.ok().build();
    }
}
