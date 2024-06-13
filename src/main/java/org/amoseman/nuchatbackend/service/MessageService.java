package org.amoseman.nuchatbackend.service;

import com.google.common.collect.ImmutableList;
import org.amoseman.nuchatbackend.dao.ChannelDAO;
import org.amoseman.nuchatbackend.dao.MessageDAO;
import org.amoseman.nuchatbackend.dao.exception.channel.ChannelDoesNotExistException;
import org.amoseman.nuchatbackend.dao.exception.message.MessageDoesNotExistException;
import org.amoseman.nuchatbackend.dao.exception.user.UserAuthorizationException;
import org.amoseman.nuchatbackend.pojo.channel.ChannelRecord;
import org.amoseman.nuchatbackend.pojo.message.Message;
import org.amoseman.nuchatbackend.pojo.message.MessageRecord;
import org.amoseman.nuchatbackend.pojo.message.MessageUpdate;
import org.amoseman.nuchatbackend.service.auth.UserPrincipal;

public class MessageService {
    private final MessageDAO messageDAO;
    private final ChannelDAO channelDAO;

    public MessageService(MessageDAO messageDAO, ChannelDAO channelDAO) {
        this.messageDAO = messageDAO;
        this.channelDAO = channelDAO;
    }

    // todo: implement
    public void create(UserPrincipal principal, Message message) throws UserAuthorizationException {
        if (!message.getAuthorUUID().equals(principal.getName())) {
            throw new UserAuthorizationException();
        }
        messageDAO.create(message);
    }

    public void update(UserPrincipal principal, MessageUpdate messageUpdate) throws UserAuthorizationException, MessageDoesNotExistException {
        MessageRecord current = messageDAO.get(messageUpdate.getID());
        if (!current.getAuthorUUID().equals(principal.getName())) {
            throw new UserAuthorizationException();
        }
        messageDAO.update(messageUpdate);
    }

    public void delete(UserPrincipal principal, long id) throws UserAuthorizationException, MessageDoesNotExistException, ChannelDoesNotExistException {
        MessageRecord current = messageDAO.get(id);
        ChannelRecord channel = channelDAO.get(current.getChannelID());
        if (!(channel.getAdminUUID().equals(principal.getName()) || current.getAuthorUUID().equals(principal.getName()))) {
            throw new UserAuthorizationException();
        }
        messageDAO.delete(id);
    }
    public ImmutableList<MessageRecord> getAll(UserPrincipal principal, long channelID) throws UserAuthorizationException, ChannelDoesNotExistException {
        ChannelRecord channel = channelDAO.get(channelID);
        if (channel.isClosed() && !channel.getMembers().contains(principal.getName())) {
            throw new UserAuthorizationException();
        }
        return messageDAO.getAll(channelID);
    }
}
