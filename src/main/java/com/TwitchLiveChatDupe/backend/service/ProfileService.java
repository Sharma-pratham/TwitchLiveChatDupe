package com.TwitchLiveChatDupe.backend.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TwitchLiveChatDupe.backend.dao.ProfileDao;
import com.TwitchLiveChatDupe.backend.model.Profile;

/**
 * Service class for managing user profiles.
 */
@Service
public class ProfileService {

    @Autowired
    private ProfileDao dao;

    /**
     * Registers a new user profile.
     *
     * @param signupRequest The profile details for registration.
     * @return The registered profile.
     */
    public Profile signup(Profile signupRequest) {
        Profile profile = dao.signup(signupRequest);
        return profile;
    }

    /**
     * Retrieves a user profile by its ID.
     *
     * @param id The ID of the profile to retrieve.
     * @return The retrieved profile.
     */
    public Profile getProfile(UUID id) {
        Profile profile = dao.getProfile(id);
        return profile;
    }

    /**
     * Updates a user profile with new details.
     *
     * @param id The ID of the profile to update.
     * @param profileDetails The new details of the profile.
     * @return The updated profile.
     */
    public Profile updateProfile(UUID id, Profile profileDetails) {
        Profile profile = dao.getProfile(id);
        // Update the profile with new details
        profile.setFullName(profileDetails.getFullName());
        profile.setUsername(profileDetails.getUsername());
        profile.setEmail(profileDetails.getEmail());
        profile.setPassword(profileDetails.getPassword());
        profile.setAvatar(profileDetails.getAvatar());
        profile.setActive(profileDetails.isActive());
        profile.setUpdatedAt(profileDetails.getUpdatedAt());
        dao.signup(profile); // Save the updated profile
        return profile;
    }

    /**
     * Adds a follower to a user profile.
     *
     * @param id The ID of the profile to follow.
     * @param followerId The ID of the follower.
     */
    public void followProfile(UUID id, UUID followerId) {
        dao.followProfile(id, followerId);
    }

    /**
     * Removes a follower from a user profile.
     *
     * @param id The ID of the profile to unfollow.
     * @param followerId The ID of the follower to remove.
     */
    public void unfollowProfile(UUID id, UUID followerId) {
        dao.unfollowProfile(id, followerId);
    }
}
