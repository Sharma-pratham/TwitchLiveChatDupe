package com.TwitchLiveChatDupe.backend.model;

import java.util.UUID;


/**
 * 
 * this class is for the unauth  and authorised users that join the chat, Profie model will be used just to manage the users on the platform and to avoid overloading that table.
 */
public class User {
    
private UUID id;
private String username;
private String role;
private boolean ban;
private boolean mute;

public UUID getId() {
    return id;
}
public void setId(UUID id) {
    this.id = id;
}
public String getUsername() {
    return username;
}
public void setUsername(String username) {
    this.username = username;
}
public String getRole() {
    return role;
}
public void setRole(String role) {
    this.role = role;
}

    public boolean isBan() {
        return ban;
    }

    public void setBan(boolean ban) {
        this.ban = ban;
    }

    public boolean isMute() {
        return mute;
    }

    public void setMute(boolean mute) {
        this.mute = mute;
    }

}
