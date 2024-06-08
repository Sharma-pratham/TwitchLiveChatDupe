package com.TwitchLiveChatDupe.backend.resources;

public class  StaticResources {
    
    public static String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    public static String DB_USERNAME = "postgres";
    public static String DB_PASSWORD = "pratham";

    public static String INSERT_PROFILE="INSERT INTO profiles (id, full_name, username, email, password, avatar, active, created_at, updated_at)" 
                                        + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static String GET_PROFILE_BY_ID = "SELECT * FROM profiles WHERE id = ?";
    public static String  FOLLOW_PROFILE = "INSERT INTO followers (profile_id, follower_id) VALUES (?, ?)";
    public static String UNFOLLOW_PROFILE = "DELETE FROM followers WHERE profile_id = ? AND follower_id = ?";
    
    
    public static String CREATE_CHANNEL =  "INSERT INTO channels (id, name, description, created_at, updated_at) VALUES (?, ?, ?, ?, ?) RETURNING id";
    public static String GET_CHANNEL_BY_ID = "SELECT * FROM channels WHERE id = ?";
    public static String UPDATE_CHANNEL = "UPDATE channels SET name = ?, description = ?, updated_at = ? WHERE id = ?";
    public static String DELETE_CHANNEL = "DELETE FROM channels WHERE id = ?";

    public static String INSERT_MESSAGE =  "INSERT INTO chat_messages (id, from_user, text, channel_id, timestamp) VALUES (?, ?, ?, ?, ?)";
    public static String GET_MESSAGE_BY_ID =  "SELECT * FROM chat_messages WHERE channel_id = ?";

    public static String GET_USER_BY_USERNAME =  "SELECT * FROM users WHERE username = ?";
    public static String GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    public static String UPDATE_USER =  "UPDATE users SET username = ?, role = ? WHERE id = ?";

    public static String SUPERADMIN = "SUPERADMIN";
    public static String ADMIN = "ADMIN";
    public static String USER = "USER";
}
