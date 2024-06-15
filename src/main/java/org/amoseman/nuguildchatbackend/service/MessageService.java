package org.amoseman.nuguildchatbackend.service;

import com.google.common.collect.ImmutableList;
import org.amoseman.nuguildchatbackend.dao.ChannelDAO;
import org.amoseman.nuguildchatbackend.dao.MessageDAO;
import org.amoseman.nuguildchatbackend.dao.exception.channel.ChannelDoesNotExistException;
import org.amoseman.nuguildchatbackend.dao.exception.message.MessageDoesNotExistException;
import org.amoseman.nuguildchatbackend.dao.exception.user.UserAuthorizationException;
import org.amoseman.nuguildchatbackend.pojo.channel.ChannelRecord;
import org.amoseman.nuguildchatbackend.pojo.message.Message;
import org.amoseman.nuguildchatbackend.pojo.message.MessageRecord;
import org.amoseman.nuguildchatbackend.pojo.message.MessageUpdate;
import org.amoseman.nuguildchatbackend.pojo.message.RecentMessagesQuery;
import org.amoseman.nuguildchatbackend.service.auth.UserPrincipal;

public class MessageService {
    private final MessageDAO messageDAO;
    private final ChannelDAO channelDAO;

    public MessageService(MessageDAO messageDAO, ChannelDAO channelDAO) {
        this.messageDAO = messageDAO;
        this.channelDAO = channelDAO;
    }

    // todo: implement
    public void create(UserPrincipal principal, Message message) throws UserAuthorizationException {
        if (!message.getAuthor().equals(principal.getName())) {
            throw new UserAuthorizationException();
        }
        messageDAO.create(message);
    }

    public void update(UserPrincipal principal, MessageUpdate messageUpdate) throws UserAuthorizationException, MessageDoesNotExistException {
        MessageRecord current = messageDAO.get(messageUpdate.getID());
        if (!current.getAuthor().equals(principal.getName())) {
            throw new UserAuthorizationException();
        }
        messageDAO.update(messageUpdate);
    }

    public void delete(UserPrincipal principal, long id) throws UserAuthorizationException, MessageDoesNotExistException, ChannelDoesNotExistException {
        MessageRecord current = messageDAO.get(id);
        ChannelRecord channel = channelDAO.get(current.getChannelID());
        if (!(channel.getAdminUsername().equals(principal.getName()) || current.getAuthor().equals(principal.getName()))) {
            throw new UserAuthorizationException();
        }
        messageDAO.delete(id);
    }

    public ImmutableList<MessageRecord> getRecent(UserPrincipal principal, long channelID, RecentMessagesQuery recentMessagesQuery) throws UserAuthorizationException, ChannelDoesNotExistException {
        ChannelRecord channel = channelDAO.get(channelID);
        if (channel.isClosed() && !channel.getMembers().contains(principal.getName())) {
            throw new UserAuthorizationException();
        }
        return messageDAO.getRecent(channelID, recentMessagesQuery);
    }
}
