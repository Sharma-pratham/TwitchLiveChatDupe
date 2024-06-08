
package com.TwitchLiveChatDupe.backend.model;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;


public class Profile {

    

    private UUID id;

    private String fullName;


    private String username;


    private String email;


    private String password;


    private String avatar;


    private boolean active;


    private Timestamp createdAt;


    private Timestamp updatedAt;

    private Set<Profile> followers;

    private Set<Profile> following;

    // Getters and Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp timestamp) {
        this.createdAt = timestamp;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp timestamp) {
        this.updatedAt = timestamp;
    }

    public Set<Profile> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<Profile> followers) {
        this.followers = followers;
    }

    public Set<Profile> getFollowing() {
        return following;
    }

    public void setFollowing(Set<Profile> following) {
        this.following = following;
    }
}
