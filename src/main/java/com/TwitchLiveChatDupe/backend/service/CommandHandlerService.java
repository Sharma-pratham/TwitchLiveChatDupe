package com.TwitchLiveChatDupe.backend.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.TwitchLiveChatDupe.backend.dao.ChannelDao;
import com.TwitchLiveChatDupe.backend.dao.UserDao;
import com.TwitchLiveChatDupe.backend.model.Channel;
import com.TwitchLiveChatDupe.backend.model.ChatCommand;
import com.TwitchLiveChatDupe.backend.model.User;
import com.TwitchLiveChatDupe.backend.resources.StaticResources;

/**
 * Service class for handling chat commands.
 */
@Service
public class CommandHandlerService {

    private final UserDao userDAO;
    private final ChannelDao channelDAO;

    /**
     * Constructs a CommandHandlerService with the specified UserDao and ChannelDao.
     *
     * @param userDAO the UserDao to be used by this service.
     * @param channelDAO the ChannelDao to be used by this service.
     */
    public CommandHandlerService(UserDao userDAO, ChannelDao channelDAO) {
        this.userDAO = userDAO;
        this.channelDAO = channelDAO;
    }

    /**
     * Sets the role of the target user to admin.
     *
     * @param command the chat command containing the target user.
     */
    public void setAdmin(ChatCommand command) {
        User sender = userDAO.findByUsername(command.getFrom());
        Channel channel = channelDAO.findById(UUID.fromString(command.getChannelId()));

        if (isHostOrSuperAdmin(sender, channel)) {
            User targetUser = userDAO.findByUsername(command.getTargetUser());
            targetUser.setRole(StaticResources.ADMIN);
            userDAO.save(targetUser);
        }
    }

    /**
     * Unsets the role of the target user from admin to user.
     *
     * @param command the chat command containing the target user.
     */
    public void unsetAdmin(ChatCommand command) {
        User sender = userDAO.findByUsername(command.getFrom());
        Channel channel = channelDAO.findById(UUID.fromString(command.getChannelId()));

        if (isHostOrSuperAdmin(sender, channel)) {
            User targetUser = userDAO.findByUsername(command.getTargetUser());
            targetUser.setRole(StaticResources.USER);
            userDAO.save(targetUser);
        }
    }

    /**
     * Mutes the target user.
     *
     * @param command the chat command containing the target user.
     */
    public void mute(ChatCommand command) {
        User sender = userDAO.findByUsername(command.getFrom());

        if (isAdminOrSuperAdmin(sender)) {
            User targetUser = userDAO.findByUsername(command.getTargetUser());
            targetUser.setMute(true);
            userDAO.save(targetUser);
        }
    }

    /**
     * Unmutes the target user.
     *
     * @param command the chat command containing the target user.
     */
    public void unmute(ChatCommand command) {
        User sender = userDAO.findByUsername(command.getFrom());

        if (isAdminOrSuperAdmin(sender)) {
            User targetUser = userDAO.findByUsername(command.getTargetUser());
            targetUser.setMute(false);
            userDAO.save(targetUser);
        }
    }

    /**
     * Bans the target user.
     *
     * @param command the chat command containing the target user.
     */
    public void ban(ChatCommand command) {
        User sender = userDAO.findByUsername(command.getFrom());

        if (isAdminOrSuperAdmin(sender)) {
            User targetUser = userDAO.findByUsername(command.getTargetUser());
            targetUser.setBan(true);
            userDAO.save(targetUser);
        }
    }

    /**
     * Unbans the target user.
     *
     * @param command the chat command containing the target user.
     */
    public void unban(ChatCommand command) {
        User sender = userDAO.findByUsername(command.getFrom());

        if (isAdminOrSuperAdmin(sender)) {
            User targetUser = userDAO.findByUsername(command.getTargetUser());
            targetUser.setBan(false);
            userDAO.save(targetUser);
        }
    }

    /**
     * Suspends the channel.
     *
     * @param command the chat command containing the channel to be suspended.
     */
    public void suspend(ChatCommand command) {
        User sender = userDAO.findByUsername(command.getFrom());
        Channel channel = channelDAO.findById(UUID.fromString(command.getChannelId()));

        if (isSuperAdmin(sender)) {
            channel.setSuspended(true);
            channelDAO.save(channel);
        }
    }

    /**
     * Sets the title of the channel.
     *
     * @param command the chat command containing the new title for the channel.
     */
    public void setTitle(ChatCommand command) {
        User sender = userDAO.findByUsername(command.getFrom());
        Channel channel = channelDAO.findById(UUID.fromString(command.getChannelId()));

        if (isAdminOrSuperAdmin(sender)) {
            channel.setName(command.getAdditionalData());
            channelDAO.save(channel);
        }
    }

    /**
     * Sets the description of the channel.
     *
     * @param command the chat command containing the new description for the channel.
     */
    public void setDescription(ChatCommand command) {
        User sender = userDAO.findByUsername(command.getFrom());
        Channel channel = channelDAO.findById(UUID.fromString(command.getChannelId()));

        if (isAdminOrSuperAdmin(sender)) {
            channel.setDescription(command.getAdditionalData());
            channelDAO.save(channel);
        }
    }

    /**
     * Checks if the user is a super admin.
     *
     * @param user the user to check.
     * @return true if the user is a super admin, false otherwise.
     */
    private boolean isSuperAdmin(User user) {
        return StaticResources.SUPERADMIN.equals(user.getRole());
    }

    /**
     * Checks if the user is the host of the channel.
     *
     * @param user the user to check.
     * @param channel the channel to check.
     * @return true if the user is the host of the channel, false otherwise.
     */
    private boolean isHost(User user, Channel channel) {
        return channel.getHostId().equals(user.getId());
    }

    /**
     * Checks if the user is either a host or a super admin.
     *
     * @param user the user to check.
     * @param channel the channel to check.
     * @return true if the user is either a host or a super admin, false otherwise.
     */
    private boolean isHostOrSuperAdmin(User user, Channel channel) {
        return StaticResources.SUPERADMIN.equals(user.getRole()) || isHost(user, channel);
    }

    /**
     * Checks if the user is either an admin or a super admin.
     *
     * @param user the user to check.
     * @return true if the user is either an admin or a super admin, false otherwise.
     */
    private boolean isAdminOrSuperAdmin(User user) {
        return StaticResources.ADMIN.equals(user.getRole()) || isSuperAdmin(user);
    }
}
