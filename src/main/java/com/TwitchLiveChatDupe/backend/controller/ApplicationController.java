package com.TwitchLiveChatDupe.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.TwitchLiveChatDupe.backend.model.Profile;
import com.TwitchLiveChatDupe.backend.service.ProfileService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Application Controller", description = "Application status and user signup operations")
public class ApplicationController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/signup")
    @Operation(summary = "Signup a new user", description = "Registers a new user profile in the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User registered successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request data"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Profile signup(@RequestBody Profile signupRequest) {
        Profile profile = null;
        try {

            profile = profileService.signup(signupRequest);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return profile;
    }

    @GetMapping("/status")
    @Operation(summary = "Get application status", description = "Returns the running status of the application")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Application is running")
    })
    public String getStatus() {
        return "Application is Running";
    }
    
    
}
