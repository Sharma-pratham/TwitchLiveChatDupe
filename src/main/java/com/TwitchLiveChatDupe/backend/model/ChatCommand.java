package com.TwitchLiveChatDupe.backend.model;

public class ChatCommand {

    private String from;
    private String command;
    private String targetUser;
    private String channelId;
    private String additionalData;

    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getCommand() {
        return command;
    }
    public void setCommand(String command) {
        this.command = command;
    }
    public String getTargetUser() {
        return targetUser;
    }
    public void setTargetUser(String targetUser) {
        this.targetUser = targetUser;
    }
    public String getChannelId() {
        return channelId;
    }
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
    public String getAdditionalData() {
        return additionalData;
    }
    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData;
    }

}

