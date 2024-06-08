package com.TwitchLiveChatDupe.backend.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TwitchLiveChatDupe.backend.model.Profile;
import com.TwitchLiveChatDupe.backend.service.ProfileService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/profiles")
@Tag(name = "Profile Controller", description = "Operations related to user profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/{id}")
    @Operation(summary = "Get a profile", description = "Retrieve a user profile by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Profile retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Profile not found")
    })
    public ResponseEntity<Profile> getProfile(@PathVariable UUID id) {
        Profile profile = profileService.getProfile(id);
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a profile", description = "Update a user profile by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Profile updated successfully"),
        @ApiResponse(responseCode = "404", description = "Profile not found")
    })
    public ResponseEntity<Profile> updateProfile(@PathVariable UUID id, @RequestBody Profile profileDetails) {
        Profile updatedProfile = profileService.updateProfile(id, profileDetails);
        return ResponseEntity.ok(updatedProfile);
    }

    @PostMapping("/{id}/follow")
    @Operation(summary = "Follow a profile", description = "Follow a user profile by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Followed profile successfully"),
        @ApiResponse(responseCode = "404", description = "Profile not found")
    })
    public ResponseEntity<?> followProfile(@PathVariable UUID id, @RequestBody UUID followerId) {
        profileService.followProfile(id, followerId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/unfollow")
    @Operation(summary = "Unfollow a profile", description = "Unfollow a user profile by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Unfollowed profile successfully"),
        @ApiResponse(responseCode = "404", description = "Profile not found")
    })
    public ResponseEntity<?> unfollowProfile(@PathVariable UUID id, @RequestBody UUID followerId) {
        profileService.unfollowProfile(id, followerId);
        return ResponseEntity.ok().build();
    }
}
